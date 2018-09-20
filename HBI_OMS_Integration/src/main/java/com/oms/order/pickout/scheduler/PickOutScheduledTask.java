/**
 * 
 */
package com.oms.order.pickout.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.common.utility.ErrorEventHandlingService;
import com.oms.order.custhistin.service.CustHistInService;
import com.oms.order.pickout.formatter.PickOutFormatter;
import com.oms.order.pickout.service.PickOutService;
import com.payment.dao.PaymentManager;

/**
 * @author Jigar
 */
@Component
//@ConditionalOnProperty(name = "oms.pickout.scheduler.enable", havingValue = "true", matchIfMissing = true)
public class PickOutScheduledTask
{
    protected static final Logger logger = Logger.getLogger(PickOutScheduledTask.class);

    @Autowired
    PickOutService pickOutOrderService;

    @Autowired
    CustHistInService custHistInOrderService;

    @Autowired
    PaymentManager paymentManager;

    @Autowired
    PickOutFormatter pickOutFormatter;

    @Autowired
    ErrorEventHandlingService errorEvent;

    @Scheduled(cron = "${pickout.cron.expression}")

    public void getOMSPickOutMessage()
    {
        String responseStr = null;
        try
        {
            logger.info("PickOutScheduledTask Initialised");

            responseStr = pickOutOrderService.getOrderFromOMS();
            OMSOrderResponse orderFromOMS = pickOutFormatter.formatPickOutToResponseObject(responseStr);
            paymentManager.parsePaymentResponse(orderFromOMS);

        }
        catch (OMSException oe)
        {
            logger.info(oe);
            int errorCode = oe.getCode();
            if (errorCode == OMSErrorCodes.JSON_ERROR.getCode()
                    || errorCode == OMSErrorCodes.PAYMENT_INSERT_ERROR.getCode()
                    || errorCode == OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode())
            {
                errorEvent.saveErrorResponse(responseStr, OrderConstantIfc.REQUEST_TYPE_PICK_OUT, oe.getMessage());
            }
        }
    }

}
