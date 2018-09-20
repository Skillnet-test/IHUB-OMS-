package com.oms.order.invoiceout.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.common.utility.ErrorEventHandlingService;
import com.oms.order.invoiceout.service.InvoiceOutService;

/**
 * Handles requests for the Employee JDBC Service.
 */
@RestController
public class InvoiceOutController
{

    private static final Logger logger = Logger.getLogger(InvoiceOutController.class);

    @Autowired
    InvoiceOutService invoiceOutService;

    @Autowired
    ErrorEventHandlingService errorEvent;

    @RequestMapping(value = "/oms/getInvoiceOutQueue", method = RequestMethod.GET)
    public String getOMSInvoiceOutQueue()
    {
        String response = "";
        String responseStr = null;
        logger.info("Get OMS Invoice Out Queue.");
        try
        {
            responseStr = invoiceOutService.getOrderInvoiceOutFromOMS();
            OMSOrderResponse omsInvoiceOutResponse = invoiceOutService.formatOrderInvoiceOut(responseStr);
            response = invoiceOutService.persistOrderInvoiceOutToDB(omsInvoiceOutResponse);
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

        return response;
    }

}
