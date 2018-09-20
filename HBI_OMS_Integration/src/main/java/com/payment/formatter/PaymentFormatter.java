package com.payment.formatter;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.payment.constant.PaymentConstantIfc;
import com.payment.dao.CompanyCodeDao;
import com.payment.data.PaymentData;
import com.payment.ncresponse.Ncresponse;

@Component
public class PaymentFormatter implements PaymentConstantIfc
{
    protected static final Logger logger = Logger.getLogger(PaymentFormatter.class);
    
    @Autowired
    CompanyCodeDao companyCodes;

    public PaymentRequest prepareRequest(PaymentData paymentData) throws OMSException
    {
        PaymentRequest paymentRequest = new PaymentRequest();
        BigDecimal settlementAmount = new BigDecimal(paymentData.getAmount());
        settlementAmount = settlementAmount.multiply(BigDecimal.valueOf(PAYMENT_SETTLEMENT_AMOUNT_MULTIPLYER)).abs()
                .setScale(0, BigDecimal.ROUND_DOWN);
        paymentRequest.setAmount(String.valueOf(settlementAmount));
        paymentRequest.setCurrency(paymentData.getCurrency());
        paymentRequest.setOperation(paymentData.getOperation());
        paymentRequest.setOrderid(paymentData.getOrderId());
        paymentRequest.setPayid(paymentData.getPayId());
        paymentRequest.setPspid(paymentData.getPspId());
        paymentRequest.setUser(paymentData.getUserId());
        paymentRequest.setPassword(paymentData.getPswd());

        String sHAPassKey = companyCodes.getPassPhrase(paymentData.getCompanyCode(),paymentData.getPspId());
        StringBuilder sHASignString = new StringBuilder("AMOUNT=").append(String.valueOf(settlementAmount))
                .append(sHAPassKey).append("CURRENCY=").append(paymentData.getCurrency()).append(sHAPassKey)
                .append("OPERATION=").append(paymentData.getOperation()).append(sHAPassKey).append("ORDERID=")
                .append(paymentData.getOrderId()).append(sHAPassKey).append("PAYID=").append(paymentData.getPayId())
                .append(sHAPassKey).append("PSPID=").append(paymentData.getPspId()).append(sHAPassKey).append("PSWD=")
                .append(paymentData.getPswd()).append(sHAPassKey).append("USERID=").append(paymentData.getUserId())
                .append(sHAPassKey);

        paymentRequest.setSHASign(generateSHA1(sHASignString.toString()));

        return paymentRequest;
    }

    public PaymentResponse translateResponse(String response, PaymentData paymentData) throws OMSException
    {
        PaymentResponse paymentResponse = new PaymentResponse();
        try
        {
            paymentResponse.setResponse(response);
            System.out.println(paymentResponse.getResponse());

            JAXBContext jContext = JAXBContext.newInstance(CONTEXT);
            StringReader reader = new StringReader(paymentResponse.getResponse());
            Ncresponse ncresponse = (Ncresponse)jContext.createUnmarshaller().unmarshal(reader);
            System.out.println("NCStatus :" + ncresponse.getNCSTATUS());
            paymentResponse.setNcresponse(ncresponse.getNCSTATUS());
            paymentResponse.setPaymentId(paymentData.getPaymentId());
            paymentResponse.setPaymentDate(paymentData.getPaymentDate());
            paymentResponse.setScoCategory(paymentData.getScoStatus());
        }
        catch (Exception ex)
        {
            logger.error(ex);
            throw new OMSException(OMSErrorCodes.RESPONSE_ERROR.getCode(),
                    OMSErrorCodes.RESPONSE_ERROR.getDescription());
        }
        return paymentResponse;
    }

    public PaymentResponse translateFraudResponse(String response, PaymentData paymentData) throws OMSException
    {
        PaymentResponse paymentResponse = new PaymentResponse();
        try
        {
            paymentResponse.setResponse(response);
            JAXBContext jContext = JAXBContext.newInstance(CONTEXT);
            StringReader reader = new StringReader(paymentResponse.getResponse());
            Ncresponse ncresponse = (Ncresponse)jContext.createUnmarshaller().unmarshal(reader);
            paymentResponse.setNcresponse(FLAG_NOT_PROCESSED);
            paymentResponse.setScoCategory(ncresponse.getSCOCATEGORY());
            paymentResponse.setPaymentId(paymentData.getPaymentId());
            paymentResponse.setPaymentDate(paymentData.getPaymentDate());
            paymentData.setScoStatus(ncresponse.getSCOCATEGORY());

        }
        catch (Exception ex)
        {
            logger.error("Unable to perform fraud check: " + ex);
            throw new OMSException(OMSErrorCodes.RESPONSE_ERROR.getCode(),
                    OMSErrorCodes.RESPONSE_ERROR.getDescription());
        }
        return paymentResponse;
    }

    public static String generateSHA1(String sHASignString) throws OMSException
    {
        String sha1 = null;
        try
        {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(sHASignString.getBytes("UTF-8"), 0, sHASignString.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException e)
        {
            logger.error(e);
            throw new OMSException(OMSErrorCodes.REQUEST_SHASIGN_GENERATE_ERROR.getCode(),
                    OMSErrorCodes.REQUEST_SHASIGN_GENERATE_ERROR.getDescription());
        }
        return sha1;
    }

}
