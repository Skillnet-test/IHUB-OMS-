package com.oms.order.invoiceout.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.common.utility.ErrorEventHandlingService;
import com.oms.order.invoiceout.service.InvoiceOutService;

/**
 * @author Jigar
 */
@Component
//@ConditionalOnProperty(name = "oms.invoiceout.scheduler.enable", havingValue = "true", matchIfMissing = true)
public class InvoiceOutScheduledTask
{
    protected static final Logger logger = Logger.getLogger(InvoiceOutScheduledTask.class);

    @Autowired
    InvoiceOutService invoiceOutOrderService;

    @Autowired
    ErrorEventHandlingService errorEvent;

    public InvoiceOutScheduledTask()
    {
        logger.info("InvoiceOutScheduledTask Initialised");
    }

    @Scheduled(cron = "${invoiceout.cron.expression}")
    public void getOMSInvoiceOut()
    {
        String responseStr = null;
        logger.info("START-Reading message from OMS INVOIC_OUT_OUT Queue");
        try
        {
            responseStr = invoiceOutOrderService.getOrderInvoiceOutFromOMS();
            OMSOrderResponse omsInvoiceOutResponse = invoiceOutOrderService.formatOrderInvoiceOut(responseStr);
            invoiceOutOrderService.persistOrderInvoiceOutToDB(omsInvoiceOutResponse);
        }
        catch (OMSException e)
        {
            logger.error(e);
            int errorCode = e.getCode();
            if (errorCode == OMSErrorCodes.JSON_ERROR.getCode()
                    || errorCode == OMSErrorCodes.PAYMENT_INSERT_ERROR.getCode()
                    || errorCode == OMSErrorCodes.DATABASE.getCode()
                    || errorCode == OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode())
            {
                errorEvent.saveErrorResponse(responseStr, OrderConstantIfc.REQUEST_TYPE_INVOICE_OUT, e.getMessage());
            }
        }
        logger.info("END-Reading message from OMS INVOIC_OUT_OUT Queue");

    }

}
