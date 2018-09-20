package com.payment.formatter;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.formatter.OMSOrderRequest;
import com.oms.order.connector.OrderConnector;
import com.payment.constant.PaymentConstantIfc;
import com.payment.data.PaymentData;

@Component
public class AdyenPaymentFormatter
{
    private static final Logger logger = Logger.getLogger(AdyenPaymentFormatter.class);

    private static final String REFERENCE = "reference";

    private static final String ORIGINAL_REFERENCE = "originalReference";

    private static final String MODIFICATION_AMOUNT = "modificationAmount";

    private static final String CURRENCY = "currency";

    private static final String VALUE = "value";

    private static final String MERCHANT_ACCOUNT = "merchantAccount";

    @Autowired
    OrderConnector omsOrderConnector;

    @Value("${adyen.refund.url}")
    private String adyenRefundEndPoint;

    @Value("${adyen.authorization.value}")
    private String adyenAuthorizationValue;

    @Value("${adyen.contentType}")
    private String adyenContentType;

    private static final String PAY_SUCCESS = "1";

    public PaymentResponse translateResponse(String response, PaymentData paymentData) throws OMSException
    {
        PaymentResponse paymentResponse = new PaymentResponse();
        try
        {
            paymentResponse.setResponse(response);
            paymentResponse.setNcresponse(PAY_SUCCESS);
            paymentResponse.setPaymentId(paymentData.getPaymentId());
            paymentResponse.setPaymentDate(paymentData.getPaymentDate());
        }
        catch (Exception ex)
        {
            logger.info(ex);
            throw new OMSException(OMSErrorCodes.RESPONSE_ERROR.getCode(),
                    OMSErrorCodes.RESPONSE_ERROR.getDescription());
        }
        return paymentResponse;

    }

    public OMSOrderRequest prepareRequest(PaymentData paymentData)
    {
        JSONObject requestObject = new JSONObject();

        requestObject.put(MERCHANT_ACCOUNT, paymentData.getPspId());

        JSONObject modificationRequest = new JSONObject();

        BigDecimal settlementAmount = new BigDecimal(paymentData.getAmount());
        settlementAmount = settlementAmount
                .multiply(BigDecimal.valueOf(PaymentConstantIfc.PAYMENT_SETTLEMENT_AMOUNT_MULTIPLYER)).abs()
                .setScale(0, BigDecimal.ROUND_DOWN);

        modificationRequest.put(VALUE, settlementAmount);
        modificationRequest.put(CURRENCY, paymentData.getCurrency());

        requestObject.put(MODIFICATION_AMOUNT, modificationRequest);
        requestObject.put(ORIGINAL_REFERENCE, paymentData.getPayId());
            requestObject.put(REFERENCE, paymentData.getOrderId());
    
            OMSOrderRequest omsOrderRequest = new OMSOrderRequest();
        omsOrderRequest.setEndpoint(adyenRefundEndPoint);
        omsOrderRequest.setAuthorizationValue(adyenAuthorizationValue);
        omsOrderRequest.setContentTypeValue(adyenContentType);

        omsOrderRequest.setRequest(requestObject.toString());

        return omsOrderRequest;

    }

}
