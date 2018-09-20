package com.oms.order.invoiceout.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.PaymentMethodEnum;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.invoiceout.formatter.InvoiceOutFormatter;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader;
import com.oms.order.invoiceout.formatter.schema.InvoiceOutMessage.InvoiceHeader.InvoicePaymentMethods.InvoicePaymentMethod;
import com.payment.dao.PaymentManager;
import com.payment.data.PaymentData;

import oracle.retail.stores.domain.transaction.OrderTransactionIfc;
import oracle.retail.stores.domain.transaction.SaleReturnTransactionIfc;
import oracle.retail.stores.domain.transaction.TransactionConstantsIfc;

@Component
public class InvoiceOutManager
{
    private static final Logger logger = Logger.getLogger(InvoiceOutManager.class);

    @Autowired
    PaymentManager paymentManager;

    @Autowired
    InvoiceOutFormatter invoiceOutFormatter;

    @Autowired
    InvoiceOutDAO invoiceOutDao;

    public void persistOMSInvoiceOut(OMSOrderResponse omsInvoiceOutResponse) throws OMSException
    {
        int transactionType = invoiceOutFormatter.getTransactionType(omsInvoiceOutResponse);

        if (transactionType == TransactionConstantsIfc.TYPE_SALE)
        {
            OrderTransactionIfc orderTransaction = invoiceOutFormatter
                    .formatInvoicOutResponseOrderTransaction(omsInvoiceOutResponse);
            
            if (orderTransaction != null && orderTransaction.getOrderStatus() != null
                    && orderTransaction.getOrderStatus().getInitiatingChannel() != OrderConstantIfc.ORDER_CHANNEL_OIS)
            {
                invoiceOutDao.persistOMSInvoiceOut(orderTransaction);
            }

            OrderTransactionIfc completedOrderTransaction = invoiceOutFormatter
                    .getCompletedOrderTransaction(orderTransaction);
            invoiceOutDao.persistOMSInvoiceOut(completedOrderTransaction);
        }
        else if (transactionType == TransactionConstantsIfc.TYPE_RETURN)
        {     
            if (checkPayType(omsInvoiceOutResponse))
            {
                PaymentData paymentData = paymentManager.parseRefundData(omsInvoiceOutResponse);
                paymentManager.insertPaymentData(paymentData);
            }
            
            SaleReturnTransactionIfc orderReturnTransaction = invoiceOutFormatter
                    .formatInvoicOutResponseToSaleReturnTransaction(omsInvoiceOutResponse);
            
            invoiceOutDao.persistOMSInvoiceOut(orderReturnTransaction);
        }

        else
        {
            logger.info("Unknown Transaction type found in Order Invoice Out.");
        }

    }
    
    private boolean checkPayType(OMSOrderResponse omsInvoiceOutResponse)
    {
        boolean isPayTypeCRDT = false;
        InvoiceHeader invoiceHeader = omsInvoiceOutResponse.getInvoiceOutMessage().getInvoiceHeader();
        List<InvoicePaymentMethod> omsInvoicePaymentMethodList=null;
        if(invoiceHeader.getInvoicePaymentMethods()!=null)
        {
            omsInvoicePaymentMethodList = invoiceHeader.getInvoicePaymentMethods().getInvoicePaymentMethod();
        }

        if (omsInvoicePaymentMethodList != null || CollectionUtils.isEmpty(omsInvoicePaymentMethodList))
        {
            for (int i = 0; i < omsInvoicePaymentMethodList.size(); i++)
            {
                InvoicePaymentMethod invoicePayment = omsInvoicePaymentMethodList.get(i);
                int payType = Integer.parseInt(invoicePayment.getIpmPayType());
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
            } 
        }
        return isPayTypeCRDT;
    }
}
