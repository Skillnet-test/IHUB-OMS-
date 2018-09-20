/**
 * 
 */
package com.oms.order.pickout.formatter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;
import com.oms.order.common.formatter.OMSOrderResponse;
import com.oms.order.pickout.formatter.schema.PickOutMessage;


/**
 * @author Jigar
 */
@Component
public class PickOutFormatter
{

    private static final Logger logger = Logger.getLogger(PickOutFormatter.class);

    public OMSOrderResponse formatPickOutToResponseObject(String invoiceOutResponseStr) throws OMSException
    {
        OMSOrderResponse pickOutResponse = new OMSOrderResponse();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            if (StringUtils.isNotEmpty(invoiceOutResponseStr))
            {
                JSONObject invoiceOutJSONObject = new JSONObject(invoiceOutResponseStr);

                String pickOutResponseStatus = invoiceOutJSONObject
                        .getString(OrderConstantIfc.INVOICE_OUT_JSON_STATUS_KEY);
                pickOutResponse.setStatus(pickOutResponseStatus);

                if (pickOutResponseStatus.equalsIgnoreCase(OrderConstantIfc.INVOICE_OUT_RESPONSE_STATUS_OK))
                {
                    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                    PickOutMessage pickOutMessage = mapper.readValue(
                            XML.toJSONObject(StringEscapeUtils.unescapeJava(invoiceOutResponseStr))
                                    .get(OrderConstantIfc.INVOICE_OUT_JSON_MESSAGE_KEY).toString(),
                            PickOutMessage.class);
                    pickOutResponse.setPickOutMessage(pickOutMessage);
                }
            }
        }
        catch (Exception e)
        {
            pickOutResponse.setStatus(OrderConstantIfc.INVOICE_OUT_RESPONSE_STATUS_FAILED);
            logger.error("Exception caught while reading Pick Out Queue " + e);
            throw new OMSException(OMSErrorCodes.JSON_ERROR.getCode(), OMSErrorCodes.JSON_ERROR.getDescription());
        }
        return pickOutResponse;
    }

}
