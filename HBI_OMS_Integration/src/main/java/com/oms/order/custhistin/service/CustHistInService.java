/**
 * 
 */
package com.oms.order.custhistin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.connector.OrderConnector;
import com.oms.order.custhistin.formatter.CustHistInFormatter;
import com.oms.order.custhistin.formatter.CustHistInRequest;
import com.oms.order.custhistin.formatter.CustHistInResponse;

/**
 * @author Jigar
 */
@Service
public class CustHistInService
{

    @Autowired
    OrderConnector omsOrderConnector;

    @Autowired
    CustHistInFormatter omsCustHistFormatter;

    @Value("${oms.cwMessageIn.url}")
    private String omscwMessageEndPoint;

    @Value("${oms.cwMessageIn.authorization.value}")
    private String omsAuthorizationValue;

    @Value("${oms.cwMessageIn.contentType}")
    private String omscwMessageInContentType;

    public CustHistInResponse getOrderFromOMS(String orderID) throws OMSException
    {
    	CustHistInResponse omsCustHistResponse = null;
        CustHistInRequest omsCustHistRequest = new CustHistInRequest();
        omsCustHistRequest.setEndpoint(omscwMessageEndPoint);
        omsCustHistRequest.setAuthorizationValue(omsAuthorizationValue);
        omsCustHistRequest.setContentTypeValue(omscwMessageInContentType);
        omsCustHistRequest.setRequestType(OrderConstantIfc.REQUEST_TYPE_CUSTHISTORY_OUT);

        omsCustHistRequest.setOrderNumber(orderID);

        String request = omsCustHistFormatter.prepareRequest(omsCustHistRequest);
        omsCustHistRequest.setRequest(request);
        String response = (String)omsOrderConnector.processRequest(omsCustHistRequest);
        omsCustHistResponse = omsCustHistFormatter.translateResponse(response);

        return omsCustHistResponse;

    }

}
