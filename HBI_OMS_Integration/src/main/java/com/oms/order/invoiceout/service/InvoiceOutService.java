/**
 * 
 */
package com.oms.order.invoiceout.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderRequest;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.connector.OrderConnector;
import com.oms.order.invoiceout.dao.InvoiceOutManager;
import com.oms.order.invoiceout.formatter.InvoiceOutFormatter;

/**
 * @author Jigar
 */
@Service
public class InvoiceOutService
{

    private static final Logger logger = Logger.getLogger(InvoiceOutService.class);

    @Autowired
    OrderConnector omsOrderConnector;

    @Autowired
    InvoiceOutFormatter invoiceOutFormatter;

    @Autowired
    InvoiceOutManager invoiceOutManager;

    @Value("${oms.getfromQueue.invoiceout.queuename}")
    private String omsInvoiceOutQueueName;

    @Value("${oms.getfromQueue.url}")
    private String omsGetFromQueueEndPoint;

    @Value("${oms.getfromQueue.authorization.value}")
    private String omsAuthorizationValue;

    @Value("${oms.getfromQueue.contentType}")
    private String omsgetfromQueueContentType;

    /*
     * Get Order Invoice Out From OMS
     */
    public String getOrderInvoiceOutFromOMS() throws OMSException
    {
        String responseStr = "";
        OMSOrderRequest omsOrderRequest = new OMSOrderRequest();
        omsOrderRequest.setEndpoint(omsGetFromQueueEndPoint);
        omsOrderRequest.setAuthorizationValue(omsAuthorizationValue);
        omsOrderRequest.setContentTypeValue(omsgetfromQueueContentType);
        omsOrderRequest.setRequestType(OrderConstantIfc.REQUEST_TYPE_INVOICE_OUT);

        JSONObject invoiceOutJSONObjectRequest = new JSONObject();
        invoiceOutJSONObjectRequest.put(OrderConstantIfc.INVOICE_OUT_JSON_QUEUE_NAME_KEY, omsInvoiceOutQueueName);

        omsOrderRequest.setRequest(invoiceOutJSONObjectRequest.toString());

        responseStr = (String)omsOrderConnector.processRequest(omsOrderRequest);

        return responseStr;
    }

    /*
     * Formatting the Order Invoice Out Request
     */
    public OMSOrderResponse formatOrderInvoiceOut(String responseStr) throws OMSException
    {
        OMSOrderResponse omsInvoiceOutResponse = null;
        if (StringUtils.isNotEmpty(responseStr))
            omsInvoiceOutResponse = invoiceOutFormatter.formatInvoiceOutToResponseObject(responseStr);
        return omsInvoiceOutResponse;
    }

    /*
     * Persisting Order Invoice Out To Database.
     */
    public String persistOrderInvoiceOutToDB(OMSOrderResponse omsInvoiceOutResponse) throws OMSException
    {
        if (omsInvoiceOutResponse == null)
        {
            logger.error("Order Invoice Out Response found to be null!");
            return "Failed!";
        }

        if (StringUtils.isNotEmpty(omsInvoiceOutResponse.getStatus())
                && omsInvoiceOutResponse.getStatus().equalsIgnoreCase(OrderConstantIfc.INVOICE_OUT_RESPONSE_STATUS_EOF))
        {
            // return "No Order Found to Process!";
            throw new OMSException(OMSErrorCodes.NO_ORDER_PROCESS.getCode(),
                    OMSErrorCodes.NO_ORDER_PROCESS.getDescription());
        }
        else
            invoiceOutManager.persistOMSInvoiceOut(omsInvoiceOutResponse);

        return omsInvoiceOutResponse.getStatus();
    }

}
