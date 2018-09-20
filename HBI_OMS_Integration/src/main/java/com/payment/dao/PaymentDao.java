package com.payment.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.payment.constant.PaymentSqlConstantIfc;
import com.payment.data.PaymentData;
import com.payment.formatter.PaymentResponse;

@Repository
public class PaymentDao implements PaymentSqlConstantIfc
{
    private static final Logger logger = Logger.getLogger(PaymentDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PaymentData> getPaymentDatabyStatus() throws OMSException
    {
        List<PaymentData> paymentList = null;
        paymentList = jdbcTemplate.query(SQL_READ_ALL_PAYMENT, new BeanPropertyRowMapper(PaymentData.class));
        if (paymentList.isEmpty())
        {
            throw new OMSException(OMSErrorCodes.NO_RECORDS_FOUND.getCode(),
                    OMSErrorCodes.NO_RECORDS_FOUND.getDescription());
        }
        return paymentList;
    }

    @Transactional(readOnly = true)
    public void updateResponse(PaymentResponse paymentResponse)
    {
        jdbcTemplate.update(SQL_UPDATE_PAYMENT,
                new Object[] { paymentResponse.getResponse(), paymentResponse.getNcresponse(),
                        paymentResponse.getScoCategory(), paymentResponse.getPaymentId(),
                        paymentResponse.getPaymentDate() });
    }

    public void insertPaymentData(PaymentData paymentData) throws OMSException
    {
        try
        {
            jdbcTemplate.update(SQL_INSERT_PAYMENT,
                    new Object[] { paymentData.getCompanyCode(), paymentData.getAmount(), paymentData.getCurrency(),
                            paymentData.getOperation(), paymentData.getOrderId(), paymentData.getPayId(),
                            paymentData.getPspId(), paymentData.getPswd(), paymentData.getUserId(),
                            paymentData.getScoStatus() });
        }
        catch (Exception ex)
        {
            logger.error(ex);
            throw new OMSException(OMSErrorCodes.PAYMENT_INSERT_ERROR.getCode(),
                    OMSErrorCodes.PAYMENT_INSERT_ERROR.getDescription());
        }
    }

    @Transactional(readOnly = true)
    public boolean serchForOrderID(String orderID)
    {
        List<PaymentData> paymentList = null;

        paymentList = jdbcTemplate.query(SQL_FIND_ORDER_NUMBER, new Object[] { orderID },
                new BeanPropertyRowMapper(PaymentData.class));

        if (paymentList.isEmpty())
        {
            logger.info("Matching record not found in DB for order" + orderID);
            return false;
        }
        else
        {
            logger.info("Matching record found in DB for order" + orderID);
            return true;
        }
    }

}
