package com.payment.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.PaymentMethodEnum;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader.InvoicePaymentMethods;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader.InvoicePaymentMethods.InvoicePaymentMethod;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader.InvoiceShipTos.InvoiceShipTo;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader.InvoiceShipTos.InvoiceShipTo.OrderShipTo.OrderMessages.OrderMessage;
import com.oms.order.pickout.formatter.schema.PickOutMessage;
import com.oms.order.pickout.formatter.schema.PickOutMessage.PickHeader;
import com.oms.order.pickout.formatter.schema.PickOutMessage.PickHeader.OrderPayments.OrderPayment;
import com.oms.order.pickout.formatter.schema.PickOutMessage.PickHeader.PickDetails;
import com.oms.order.pickout.formatter.schema.PickOutMessage.PickHeader.PickDetails.PickDetail;
import com.oms.order.pickout.formatter.schema.PickOutMessage.PickHeader.PickHeaderMsgs.PickHeaderMsg;
import com.payment.constant.PaymentConstantIfc;
import com.payment.data.PaymentData;
import com.payment.formatter.PaymentResponse;
import com.payment.service.PaymentService;

@Component
public class PaymentManager
{

    private static final Logger logger = Logger.getLogger(PaymentManager.class);

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    PaymentService paymentService;

    @Autowired
    CompanyCodeDao companyCodes;

    public void paymentSettlement()
    {
        List<PaymentData> paymentDataList = null;
        try
        {
            paymentDataList = paymentDao.getPaymentDatabyStatus();
        }
        catch (OMSException oe)
        {
            logger.error(oe.getMessage());
        }
        PaymentResponse paymentResponse = null;

        for (PaymentData paymentData : paymentDataList)
        {
            try
            {
                paymentResponse = paymentService.processPayment(paymentData);
                paymentDao.updateResponse(paymentResponse);
            }
            catch (OMSException oe)
            {
                int errorCode = oe.getCode();

                if (errorCode == OMSErrorCodes.CONNECT_ERROR.getCode())
                {
                    logger.error(OMSErrorCodes.CONNECT_ERROR.getDescription());
                }
                else if (errorCode == OMSErrorCodes.RESPONSE_ERROR.getCode()
                        || errorCode == OMSErrorCodes.REQUEST_SHASIGN_GENERATE_ERROR.getCode())
                {
                    logger.error(oe.getMessage());
                    updateFailedPayment(paymentData);
                }
            }
            catch (Exception e)
            {
                logger.error("Unknown Exception Occurred ", e);
                updateFailedPayment(paymentData);
            }
        }
    }

    public PaymentData parsePaymentData(OMSOrderResponse orderFromOMS) throws OMSException
    {

        PaymentData paymentData = new PaymentData();
        PickOutMessage pickOutMessage = orderFromOMS.getPickOutMessage();
        PickHeader pickHeader = null;
        String totalAmount = null;

        if (pickOutMessage != null)
        {
            pickHeader = pickOutMessage.getPickHeader();
            if (pickHeader != null)
            {
                String orderNbr = pickHeader.getOrderNbr();

                String payid = getPaymentId(pickHeader);

                if (StringUtils.isNotBlank(payid))
                {
                    paymentData.setPayId(payid);
                }

                String companyCode = pickHeader.getCompany();
                if (StringUtils.isNotBlank(orderNbr))
                {
                    paymentData.setOrderId(orderNbr);
                }
                String orderChannel = pickHeader.getOrderHeader().getOrderChannel();

                if (StringUtils.isNotBlank(orderChannel))
                {
                    paymentData.setOrderChannel(orderChannel);
                }
                if (StringUtils.isNotBlank(companyCode))
                {
                    paymentData.setCompanyCode(companyCode);
                    paymentData.setCurrency(companyCodes.getCurrencyCode(companyCode));
                    paymentData.setPspId(companyCodes.getPspId(companyCode, orderChannel));
                    paymentData.setUserId(companyCodes.getUserId(companyCode, orderChannel));
                    paymentData.setPswd(companyCodes.getPswd(companyCode, orderChannel));
                }

                totalAmount = getSettlementTotalAmount(pickHeader);

            }

        }
        paymentData.setOperation(PaymentConstantIfc.SAL);
        paymentData.setAmount(totalAmount);

        return paymentData;

    }

    public String getPaymentId(PickHeader pickHeader)
    {
        String payId = null;
        List<PickHeaderMsg> pickHeaderMsg = pickHeader.getPickHeaderMsgs().getPickHeaderMsg();

        for (PickHeaderMsg message : pickHeaderMsg)
        {
            if (message != null && message.getMsg().startsWith(PaymentConstantIfc.AUTH_CODE_START_SEQUENCE))
            {
                payId = message.getMsg().substring(PaymentConstantIfc.AUTH_CODE_START_SEQUENCE.length());
                logger.info("Setting PAYID :" + payId + "for order number:" + pickHeader.getOrderNbr());
            }
        }
        if (StringUtils.isBlank(payId))
        {
            logger.error("PickHeaderMsg does not contains any message that has PAYID");
        }

        return payId;
    }

    public String getRefundPaymentId(InvoiceHeader invoiceHeader)
    {
        String payId = null;
        String message = null;
        List<InvoiceShipTo> invoiceShipTos = invoiceHeader.getInvoiceShipTos().getInvoiceShipTo();

        for (InvoiceShipTo InvoiceShipTo : invoiceShipTos)
        {
            List<OrderMessage> messages = InvoiceShipTo.getOrderShipTo().getOrderMessages().getOrderMessage();
            for (OrderMessage msg : messages)
            {
                message = msg.getOrderMessageText();
                if (message != null && message.startsWith(PaymentConstantIfc.AUTH_CODE_START_SEQUENCE))
                {
                    payId = message.substring(PaymentConstantIfc.AUTH_CODE_START_SEQUENCE.length());
                    logger.info(
                            "Setting PAYID :" + payId + "for refund order number:" + invoiceHeader.getIhdOrderNbr());
                }
            }
        }
        if (StringUtils.isBlank(payId))
        {
            logger.error("InvoiceHeaderMsg does not contains any message that has PAYID");
        }

        return payId;
    }

    public String getPayId(String message)
    {
        String payId = null;
        if (message != null && message.startsWith(PaymentConstantIfc.AUTH_CODE_START_SEQUENCE))
        {
            payId = message.substring(PaymentConstantIfc.AUTH_CODE_START_SEQUENCE.length());
            logger.info("Setting PAYID :" + payId);
        }
        return payId;
    }

    public PaymentData parseRefundData(OMSOrderResponse omsInvoiceOutResponse) throws OMSException
    {

        String companyId = null;
        String refundAmount = null;
        PaymentData paymentData = new PaymentData();

        InvoiceOutMessage invoiceOutMessage = omsInvoiceOutResponse.getInvoiceOutMessage();

        if (invoiceOutMessage != null)
        {
            InvoiceHeader invoiceHeader = invoiceOutMessage.getInvoiceHeader();

            if (invoiceHeader != null)
            {
                String orderNbr = invoiceHeader.getIhdOrderNbr();
                companyId = invoiceHeader.getIhdCompany();

                if (StringUtils.isNotBlank(orderNbr))
                {
                    paymentData.setOrderId(orderNbr);
                }
                String refundPayid = getRefundPaymentId(invoiceHeader);

                if (StringUtils.isNotBlank(refundPayid))
                {
                    paymentData.setPayId(refundPayid);
                }
                String orderChannel = invoiceHeader.getOrderHeader().getOhdOrderChannel();

                if (StringUtils.isNotBlank(orderChannel))
                {
                    paymentData.setOrderChannel(orderChannel);
                }

                if (StringUtils.isNotBlank(companyId))
                {
                    paymentData.setCompanyCode(companyId);
                    paymentData.setCurrency(companyCodes.getCurrencyCode(companyId));
                    paymentData.setPspId(companyCodes.getPspId(companyId, orderChannel));
                    paymentData.setUserId(companyCodes.getUserId(companyId, orderChannel));
                    paymentData.setPswd(companyCodes.getPswd(companyId, orderChannel));
                }

                refundAmount = getRefundTotalAmount(invoiceHeader);

            }
        }
        paymentData.setOperation(PaymentConstantIfc.RFD);
        paymentData.setAmount(String.valueOf(refundAmount));

        return paymentData;
    }

    public String getSettlementTotalAmount(PickHeader pickHeader)
    {
        BigDecimal freightamt = BigDecimal.valueOf(0.0);
        BigDecimal sellingPriceExtended = BigDecimal.valueOf(0.0);
        String settlementAmount;
        String freightAmountString = null;

        freightAmountString = pickHeader.getFreightAmt();

        if (StringUtils.isNotBlank(freightAmountString))
        {
            freightamt = freightamt.add(new BigDecimal(freightAmountString));
        }
        PickDetails pickDetails = pickHeader.getPickDetails();

        if (pickDetails != null)
        {
            List<PickDetail> pickDetailList = pickDetails.getPickDetail();

            for (PickDetail pickDetail : pickDetailList)
            {
                if (StringUtils.isNotBlank(pickDetail.getSellingPriceExtended()))
                {
                    sellingPriceExtended = sellingPriceExtended
                            .add(new BigDecimal(pickDetail.getSellingPriceExtended()));
                }

            }
        }
        settlementAmount = String.valueOf(sellingPriceExtended.add(freightamt));
        return settlementAmount;

    }

    public String getRefundTotalAmount(InvoiceHeader invoiceHeader)
    {
        BigDecimal freightamt = BigDecimal.valueOf(0.0);
        BigDecimal sellingPriceExtended = BigDecimal.valueOf(0.0);
        String refundAmount = null;

        InvoicePaymentMethods invoicePaymentMethods = invoiceHeader.getInvoicePaymentMethods();

        if (invoicePaymentMethods != null)
        {
            List<InvoicePaymentMethod> invoicePaymentMethodList = invoicePaymentMethods.getInvoicePaymentMethod();

            for (InvoicePaymentMethod invoicePaymentMethod : invoicePaymentMethodList)
            {
                if (StringUtils.isNotBlank(invoicePaymentMethod.getIpmFreightAmt()))
                {
                    freightamt = new BigDecimal(invoicePaymentMethod.getIpmFreightAmt());

                }
                if (StringUtils.isNotBlank(invoicePaymentMethod.getIpmMerchandiseAmt()))
                {
                    sellingPriceExtended = new BigDecimal(invoicePaymentMethod.getIpmMerchandiseAmt());
                }

            }

            refundAmount = String.valueOf(sellingPriceExtended.add(freightamt));

        }

        return refundAmount;

    }

    public void insertPaymentData(PaymentData paymentData) throws OMSException
    {
        paymentDao.insertPaymentData(paymentData);
    }

    public void updateFailedPayment(PaymentData paymentData)
    {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setResponse(PaymentConstantIfc.PAYMENT_SETTLEMENT_RESPONSE_STATUS_FAILED);
        paymentResponse.setNcresponse(PaymentConstantIfc.PAYMENT_SETTLEMENT_FAILED_STATUS);
        paymentResponse.setPaymentId(paymentData.getPaymentId());
        paymentResponse.setPaymentDate(paymentData.getPaymentDate());
        paymentDao.updateResponse(paymentResponse);
    }

    public void parsePaymentResponse(OMSOrderResponse orderFromOMS) throws OMSException
    {
        boolean orderFound = false;
        String paymentIntegration = null;

        if (StringUtils.isNotBlank(orderFromOMS.getStatus()) && !StringUtils.equalsIgnoreCase(orderFromOMS.getStatus(),
                OrderConstantIfc.INVOICE_OUT_RESPONSE_STATUS_EOF))
        {
            PaymentData paymentData = parsePaymentData(orderFromOMS);
            orderFound = paymentDao.serchForOrderID(paymentData.getOrderId());
            if (orderFound)
            {
                paymentData.setScoStatus(PaymentConstantIfc.SCO_GREEN_CATEGORY);
            }
            if (StringUtils.isNotBlank(paymentData.getCompanyCode()))
            {

                paymentIntegration = companyCodes.getPaymentIntegrationCompany(paymentData.getCompanyCode());
            }

            if (checkPayType(orderFromOMS)
                    && !(StringUtils.equalsIgnoreCase(paymentIntegration, PaymentConstantIfc.ADYEN)))
            {
                insertPaymentData(paymentData);
            }
        }
        else
        {
            logger.info("Pick Out Queue has no message to read" + orderFromOMS.getStatus());
        }
        logger.info("OMS Pick OUT Ended");

    }

    private boolean checkPayType(OMSOrderResponse orderFromOMS)
    {
        boolean isPayTypeCRDT = false;
        List<OrderPayment> orderPaymentList = orderFromOMS.getPickOutMessage().getPickHeader().getOrderPayments()
                .getOrderPayment();
        if (orderPaymentList != null || CollectionUtils.isEmpty(orderPaymentList))
        {
            for (int i = 0; i < orderPaymentList.size(); i++)
            {
                OrderPayment orderPayment = orderPaymentList.get(i);
                int payType = Integer.parseInt(orderPayment.getPayType());
                if (payType == PaymentMethodEnum.AMEX_CARD_PAYMENT.getPayMethodValue()
                        || payType == PaymentMethodEnum.VISA_CARD_PAYMENT.getPayMethodValue()
                        || payType == PaymentMethodEnum.CREDIT_CARD_PAYMENT.getPayMethodValue()
                        || payType == PaymentMethodEnum.DISCOVER_CARD_PAYMENT.getPayMethodValue()
                        || payType == PaymentMethodEnum.MAESTRO_CARD_PAYMENT.getPayMethodValue()
                        || payType == PaymentMethodEnum.DEBIT_CARD_PAYMENT.getPayMethodValue()
                        || payType == PaymentMethodEnum.JCB_CARD_PAYMENT.getPayMethodValue())
                {
                    isPayTypeCRDT = true;
                }
                else
                    isPayTypeCRDT = false;
            }
        }
        return isPayTypeCRDT;
    }

}
