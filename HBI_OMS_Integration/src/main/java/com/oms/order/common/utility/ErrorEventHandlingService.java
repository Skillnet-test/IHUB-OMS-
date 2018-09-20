package com.oms.order.common.utility;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.error.dao.ErrorEventDao;
import com.oms.order.error.data.ErrorEventData;
import com.oms.order.invoiceout.service.InvoiceOutService;
import com.oms.order.pickout.formatter.PickOutFormatter;
import com.payment.dao.PaymentManager;

import oracle.retail.stores.common.utility.StringUtils;

@Service
public class ErrorEventHandlingService
{
    protected static final Logger logger = Logger.getLogger(ErrorEventHandlingService.class);

    @Autowired
    ErrorEventDao eventDao;

    @Autowired
    PickOutFormatter pickOutFormatter;

    @Autowired
    PaymentManager paymentManager;

    @Autowired
    InvoiceOutService invoiceOutOrderService;

    @Value("${oms.getfromQueue.invoiceout.queuename}")
    private String omsInvoiceOutQueueName;

    @Value("${oms.getfromQueue.contentType}")
    private String omsgetfromQueueContentType;

    @Value("${oms.getfromQueue.pickout.queuename}")
    private String omsPickOutQueueName;

    public void saveErrorResponse(String responseStr, String requestType, String errorMsg)
    {
        ErrorEventData eventData = new ErrorEventData();
        eventData.setContentType(omsgetfromQueueContentType);
        eventData.setErrorMessage(errorMsg);
        eventData.setPayload(responseStr);
        if (StringUtils.equals(requestType, OrderConstantIfc.REQUEST_TYPE_PICK_OUT))
        {
            eventData.setRequestType(omsPickOutQueueName);
        }
        else if (StringUtils.equals(requestType, OrderConstantIfc.REQUEST_TYPE_INVOICE_OUT))
        {
            eventData.setRequestType(omsInvoiceOutQueueName);
        }
        eventDao.insertErrorEventData(eventData);
    }

    public void processErrorEvent() throws OMSException
    {

        List<ErrorEventData> eventDataList = eventDao.getErrorEventByProcessingFlag();

        for (ErrorEventData eventData : eventDataList)
        {
            try
            {
                if (StringUtils.equals(omsPickOutQueueName, eventData.getRequestType()))
                {
                    OMSOrderResponse orderFromOMS = pickOutFormatter
                            .formatPickOutToResponseObject(eventData.getPayload());
                    paymentManager.parsePaymentResponse(orderFromOMS);
                }
                else if (StringUtils.equals(omsInvoiceOutQueueName, eventData.getRequestType()))
                {
                    OMSOrderResponse omsInvoiceOutResponse = invoiceOutOrderService
                            .formatOrderInvoiceOut(eventData.getPayload());
                    invoiceOutOrderService.persistOrderInvoiceOutToDB(omsInvoiceOutResponse);
                }
                eventData.setProcessFlag(OrderConstantIfc.ERROR_EVENT_SUCCESS);
                eventDao.updateErrorEvent(eventData);
            }
            catch (OMSException e)
            {
                logger.info("Still not able to process error event " + e);
                eventData.setProcessFlag(OrderConstantIfc.ERROR_EVENT_FAILED);
                eventDao.updateErrorEvent(eventData);
            }
        }
    }
}
