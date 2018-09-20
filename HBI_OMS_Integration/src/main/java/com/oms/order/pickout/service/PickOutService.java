package com.oms.order.pickout.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderRequest;
import com.oms.order.connector.OrderConnector;
import com.oms.order.pickout.formatter.PickOutFormatter;

/**
 * @author Jigar
 */
@Service
public class PickOutService
{
    @Autowired
    OrderConnector omsOrderConnector;

    @Autowired
    PickOutFormatter pickOutFormatter;

    @Value("${oms.getfromQueue.pickout.queuename}")
    private String omsPickOutQueueName;

    @Value("${oms.getfromQueue.url}")
    private String omsGetFromQueueEndPoint;

    @Value("${oms.getfromQueue.authorization.value}")
    private String omsAuthorizationValue;

    @Value("${oms.getfromQueue.contentType}")
    private String omsgetfromQueueContentType;

    public String getOrderFromOMS() throws OMSException
    {
        String responseStr = "";
        OMSOrderRequest omsOrderRequest = new OMSOrderRequest();
        omsOrderRequest.setEndpoint(omsGetFromQueueEndPoint);
        omsOrderRequest.setAuthorizationValue(omsAuthorizationValue);
        omsOrderRequest.setContentTypeValue(omsgetfromQueueContentType);
        omsOrderRequest.setRequestType(OrderConstantIfc.REQUEST_TYPE_PICK_OUT);

        JSONObject invoiceOutJSONObjectRequest = new JSONObject();
        invoiceOutJSONObjectRequest.put(OrderConstantIfc.INVOICE_OUT_JSON_QUEUE_NAME_KEY, omsPickOutQueueName);

        omsOrderRequest.setRequest(invoiceOutJSONObjectRequest.toString());

        responseStr = (String)omsOrderConnector.processRequest(omsOrderRequest);

        return responseStr;
    }

}
