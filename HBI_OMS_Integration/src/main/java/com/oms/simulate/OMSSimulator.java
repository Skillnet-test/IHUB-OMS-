package com.oms.simulate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.oms.order.common.OrderConstantIfc;

import oracle.retail.stores.common.utility.StringUtils;

@Service
@PropertySource("classpath:simulatediHubResponse.properties")
public class OMSSimulator implements OrderConstantIfc
{
    /**
     * @author Vishal M
     */

    @Value("${pick_out_response}")
    public String pickout;

    @Value("${invoice_out_response}")
    public String invoicout;

    @Value("${custhistory_out_response}")
    public String custhisotryout;

    @Value("${Adyen_refund_invoice_out_response}")
    public String adyenRefund;

    @Value("${Adyen_Refund_settlement_response}")
    public String adyenRefundPayment;

    @Value("${Ingenico_payment_settlement_response}")
    public String ingenicoPayment;

    @Value("${Ingenico_fraud_check_response}")
    public String ingenicoFraudCheck;

    @Value("${Ingenico_refund_invoice_out_response}")
    public String ingenicoRefund;

    @Value("${simrequestType}")
    public String simrequestType;

    public ApplicationContext appContext;

    String responseMessage = null;

    private static final Logger logger = Logger.getLogger(OMSSimulator.class);

    /*
     * OMS Simulator to simulate the OMS web service response.
     * @type- 1.pick_out_response 2.invoic_out_response 3.order_out_response
     * 4.custhistory_out_response
     */

    public String getSimulatedResponse(String type)
    {
        Resource resourceType = getResponseType(type);
        try
        {

            StringBuffer buf = new StringBuffer();
            InputStream is = resourceType.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null)
            {
                buf.append(line);

            }
            responseMessage = buf.toString();

            br.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.info("Unable to read from file");

        }
        return responseMessage;

    }

    public String getSimResponse(String requestType)
    {
        String responseStr = null;
        String postmanResponseStr = null;

        if (StringUtils.equalsIgnoreCase(requestType, OrderConstantIfc.REQUEST_TYPE_INVOICE_OUT)
                && StringUtils.isNotBlank(simrequestType))
        {
            // this to overwrite the invoice out response,
            requestType = simrequestType;
        }
        responseStr = getSimulatedResponse(requestType);
        System.out.println("Request Type:" + requestType);
        postmanResponseStr = responseStr.trim();
        logger.info("RESPONSE : \n " + postmanResponseStr);

        return postmanResponseStr;

    }

    public Resource getResponseType(String type)
    {
        Resource resourceType = null;

        appContext = new ClassPathXmlApplicationContext(new String[] {});
        switch (type)
        {
            case REQUEST_TYPE_PICK_OUT:

                resourceType = appContext.getResource(pickout.trim());
                break;

            case REQUEST_TYPE_INVOICE_OUT:
                resourceType = appContext.getResource(invoicout.trim());
                break;

            case REQUEST_TYPE_CUSTHISTORY_OUT:
                resourceType = appContext.getResource(custhisotryout.trim());
                break;

            case REQUEST_TYPE_INVOICE_OUT_INGENICO_REFUND:
                resourceType = appContext.getResource(ingenicoRefund.trim());
                break;

            case REQUEST_TYPE_INVOICE_OUT_ADYEN_REFUND:
                resourceType = appContext.getResource(adyenRefund.trim());
                break;

            case REQUEST_TYPE_INGENICO_PAYMENT_SETTLEMENT:
                resourceType = appContext.getResource(ingenicoPayment.trim());
                break;

            case REQUEST_TYPE_INGENICO_FRAUD_CHECK:
                resourceType = appContext.getResource(ingenicoFraudCheck.trim());
                break;
            case REQUEST_TYPE_ADYEN_REFUND_SETTLEMENT:
                resourceType = appContext.getResource(adyenRefundPayment.trim());
                break;
        }
        return resourceType;

    }
}
