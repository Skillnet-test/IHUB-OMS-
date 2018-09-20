package com.payment.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderRequest;
import com.oms.order.connector.OrderConnector;
import com.payment.constant.PaymentConstantIfc;
import com.payment.dao.CompanyCodeDao;
import com.payment.data.PaymentData;
import com.payment.formatter.AdyenPaymentFormatter;
import com.payment.formatter.PaymentFormatter;
import com.payment.formatter.PaymentRequest;
import com.payment.formatter.PaymentResponse;

import okhttp3.FormBody;

@Component
public class PaymentService implements PaymentConstantIfc
{

    private static final Logger logger = Logger.getLogger(PaymentService.class);

    @Autowired
    OrderConnector omsOrderConnector;

    @Autowired
    PaymentFormatter paymentFormatter;

    @Autowired
    AdyenPaymentFormatter adyenPaymentFormatter;

    @Value("${ingenico.maintenancedirect.url}")
    private String ingenicoMaintenancedirectUrl;

    @Value("${ingenico.contentType}")
    private String ingenicoContentType;

    @Value("${ingenico.querydirect.url}")
    private String ingenicoQuerydirectUrl;

    @Autowired
    CompanyCodeDao companyCodes;

    @Value("${oms.ingenico.fraudcheck:true}")
    public boolean fraudcheck;

    public PaymentResponse processFraudCheck(PaymentRequest paymentRequest, PaymentData paymentData) throws OMSException
    {

        PaymentResponse paymentResponse = null;

        if (fraudcheck)
        {
            FormBody formBody = new FormBody.Builder().add(PAYID, paymentRequest.getPayid())
                    .add(PSPID, paymentRequest.getPspid()).add(PSWD, paymentRequest.getPassword())
                    .add(USERID, paymentRequest.getUser()).build();

            OMSOrderRequest omsOrderRequest = new OMSOrderRequest();
            omsOrderRequest.setEndpoint(ingenicoQuerydirectUrl);
            omsOrderRequest.setContentTypeValue(ingenicoContentType);
            omsOrderRequest.setFormBody(formBody);
            omsOrderRequest.setRequestType(OrderConstantIfc.REQUEST_TYPE_INGENICO_FRAUD_CHECK);
            String response = (String)omsOrderConnector.processRequest(omsOrderRequest);
            paymentResponse = paymentFormatter.translateFraudResponse(response, paymentData);
        }
        else
        {
            paymentResponse = new PaymentResponse();

            paymentResponse.setNcresponse(FLAG_NOT_PROCESSED);
            paymentResponse.setPaymentId(paymentData.getPaymentId());
            paymentResponse.setPaymentDate(paymentData.getPaymentDate());
            paymentResponse.setScoCategory(PaymentConstantIfc.SCO_GREEN_CATEGORY);
            paymentData.setScoStatus(PaymentConstantIfc.SCO_GREEN_CATEGORY);

        }

        return paymentResponse;
    }

    public PaymentResponse processPayment(PaymentData paymentData) throws OMSException
    {
        PaymentResponse paymentResponse = null;
        String paymentIntegration = null;

        if (StringUtils.isNotBlank(paymentData.getCompanyCode()))
        {

            paymentIntegration = companyCodes.getPaymentIntegrationCompany(paymentData.getCompanyCode());
        }

        if (StringUtils.equalsIgnoreCase(paymentIntegration, PaymentConstantIfc.INGENICO))
        {
            paymentResponse = sendRequestToIngenico(paymentData);
        }
        else if (StringUtils.equalsIgnoreCase(paymentIntegration, ADYEN))
        {
            paymentResponse = sendRequestToAdyen(paymentData);
        }
        else
        {
            logger.info("Unable to map company code with payment integration company name \n");
        }

        return paymentResponse;

    }

    private PaymentResponse sendRequestToIngenico(PaymentData paymentData) throws OMSException
    {

        String response = null;
        PaymentResponse ingenicoPayResponse = null;

        PaymentRequest paymentRequest = paymentFormatter.prepareRequest(paymentData);

        if (StringUtils.equalsIgnoreCase(paymentRequest.getOperation(), SAL))
        {
            if (StringUtils.isBlank(paymentData.getScoStatus())
                    || StringUtils.equalsIgnoreCase(paymentData.getScoStatus(), PaymentConstantIfc.SCO_ORANGE_CATEGORY))
            {
                ingenicoPayResponse = processFraudCheck(paymentRequest, paymentData);

            }
            else
            {

                logger.info("Fraud Check not required for order: " + paymentData.getOrderId() + "   Score_Categoyr: "
                        + paymentData.getScoStatus());
            }
        }

        if (StringUtils.equalsIgnoreCase(paymentRequest.getOperation(), RFD)
                || ((StringUtils.equalsIgnoreCase(paymentData.getScoStatus(), SCO_GREEN_CATEGORY))
                        && (StringUtils.equalsIgnoreCase(paymentRequest.getOperation(), SAL))))
        {
            FormBody formBody = new FormBody.Builder().add(AMOUNT, paymentRequest.getAmount())
                    .add(CURRENCY, paymentRequest.getCurrency()).add(OPERATION, paymentRequest.getOperation())
                    .add(ORDERID, paymentRequest.getOrderid()).add(PAYID, paymentRequest.getPayid())
                    .add(PSPID, paymentRequest.getPspid()).add(PSWD, paymentRequest.getPassword())
                    .add(USERID, paymentRequest.getUser()).add(SHASIGN, paymentRequest.getSHASign()).build();

            OMSOrderRequest omsOrderRequest = new OMSOrderRequest();
            omsOrderRequest.setEndpoint(ingenicoMaintenancedirectUrl);
            omsOrderRequest.setContentTypeValue(ingenicoContentType);
            omsOrderRequest.setFormBody(formBody);
            omsOrderRequest.setRequestType(OrderConstantIfc.REQUEST_TYPE_INGENICO_PAYMENT_SETTLEMENT);
            paymentData.setScoStatus(PaymentConstantIfc.SCO_GREEN_CATEGORY);
            response = (String)omsOrderConnector.processRequest(omsOrderRequest);
            System.out.println(response);
            ingenicoPayResponse = paymentFormatter.translateResponse(response, paymentData);
        }
        return ingenicoPayResponse;

    }

    private PaymentResponse sendRequestToAdyen(PaymentData paymentData) throws OMSException
    {
        String response = null;
        PaymentResponse adyenPayResponse = null;
        OMSOrderRequest omsOrderRequest = adyenPaymentFormatter.prepareRequest(paymentData);
        omsOrderRequest.setRequestType(OrderConstantIfc.REQUEST_TYPE_ADYEN_REFUND_SETTLEMENT);
        response = (String)omsOrderConnector.processRequest(omsOrderRequest);
        adyenPayResponse = adyenPaymentFormatter.translateResponse(response, paymentData);

        return adyenPayResponse;
    }

}
