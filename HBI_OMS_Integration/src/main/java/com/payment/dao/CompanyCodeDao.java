package com.payment.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.payment.constant.PaymentSqlConstantIfc;

@Component
public class CompanyCodeDao implements ApplicationListener<ApplicationReadyEvent>, PaymentSqlConstantIfc
{
    /**
     * @author Vishal M
     */

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */

    private Map<String, ComanyMappingData> companyCodeMap = null;

    private ComanyMappingData companyData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ComanyMappingData comapany;

    private List<PaymentConfig> integratorList = null;

    private Map<String, List<PaymentConfig>> paymentIntegratorMap;

    private static final Logger logger = Logger.getLogger(CompanyCodeDao.class.getName());

    @Transactional(readOnly = true)

    /*
     * onApplicationEvent() will be called as soon as application is up and
     * before any scheduler start running. method will load the country codes
     * from DB at startup to be used by application.
     */
    @Override

    public void onApplicationEvent(final ApplicationReadyEvent event)
    {

        loadCompanyDetails();

        loadPaymentIntegratorDetails();

    }

    public void loadPaymentIntegratorDetails()
    {
        List<PaymentConfig> paymentProvider = null;

        paymentIntegratorMap = new HashMap<>();

        paymentProvider = jdbcTemplate.query(SQL_READ_PAYMENT_CONFIG, new BeanPropertyRowMapper(PaymentConfig.class));

        for (final PaymentConfig paymentDetail : paymentProvider)
        {

            if (!paymentIntegratorMap.isEmpty() && paymentIntegratorMap.containsKey(paymentDetail.getCompanyCode()))
            {
                paymentIntegratorMap.get(paymentDetail.getCompanyCode()).add(paymentDetail);

            }
            else
            {
                List<PaymentConfig> list = new ArrayList<PaymentConfig>();
                list.add(paymentDetail);
                paymentIntegratorMap.put(paymentDetail.getCompanyCode(), list);

            }

        }
        logger.info("Payment provider details loaded successfully at Starup");
    }

    public void loadCompanyDetails()
    {
        List<ComanyMappingData> companyCodes = null;

        companyCodes = jdbcTemplate.query(SQL_READ_ALL_COMPANY_DETAILS,
                new BeanPropertyRowMapper(ComanyMappingData.class));

        companyCodeMap = new HashMap<String, ComanyMappingData>(companyCodes.size());

        for (final ComanyMappingData company : companyCodes)
        {

            companyCodeMap.put(company.getCompanyCode(), company);
        }
        logger.info("Comapny details loaded successfully at Starup");

    }

    public String getWareHouseNumber(String companyCode) throws OMSException
    {
        String warehouseNumber = null;
        companyData = companyCodeMap.get(companyCode);
        if (companyData == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }
        warehouseNumber = companyData.getWarehouseNumber();
        return warehouseNumber;
    }

    public String getCurrencyCode(String companyCode) throws OMSException
    {
        String currencycode = null;
        companyData = companyCodeMap.get(companyCode);
        if (companyData == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }
        currencycode = companyData.getCurrencyCode();

        return currencycode;
    }

    public String getPaymentIntegrationCompany(String companyCode) throws OMSException
    {
        String paymentIntegration;
        companyData = companyCodeMap.get(companyCode);
        if (companyData == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }
        paymentIntegration = companyData.getCompanyName();
        return paymentIntegration;
    }

    public String getPspId(String companyCode, String orderChannel) throws OMSException
    {
        String pspId = null;
        integratorList = paymentIntegratorMap.get(companyCode);

        if (integratorList == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }
        for (PaymentConfig PaymentIntegrator : integratorList)
        {
            if (PaymentIntegrator.getOrderChannel().equals(orderChannel))
            {
                pspId = PaymentIntegrator.getPspId();
            }
        }
        return pspId;
    }

    public String getUserId(String companyCode, String orderChannel) throws OMSException
    {
        String userId = null;
        integratorList = paymentIntegratorMap.get(companyCode);

        if (integratorList == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }
        for (PaymentConfig PaymentIntegrator : integratorList)
        {
            if (PaymentIntegrator.getOrderChannel().equals(orderChannel))
            {
                userId = PaymentIntegrator.getUserId();
            }
        }
        return userId;

    }

    public String getPswd(String companyCode, String orderChannel) throws OMSException
    {
        String pswd = null;
        integratorList = paymentIntegratorMap.get(companyCode);

        if (integratorList == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }
        for (PaymentConfig PaymentIntegrator : integratorList)
        {
            if (PaymentIntegrator.getOrderChannel().equals(orderChannel))
            {
                pswd = PaymentIntegrator.getPswd();
            }
        }
        return pswd;

    }

    public String getPassPhrase(String companyCode, String pspId) throws OMSException
    {
        String passPhrase = null;
        integratorList = paymentIntegratorMap.get(companyCode);

        if (integratorList == null)
        {
            throw new OMSException(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode(),
                    OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
        }

        for (PaymentConfig PaymentIntegrator : integratorList)
        {
            if (PaymentIntegrator.getPspId().equals(pspId))
            {
                passPhrase = PaymentIntegrator.getPassPhrase();
            }
        }
        return passPhrase;

    }

    public Map<String, ComanyMappingData> getCompanyCodeMap()
    {
        return companyCodeMap;
    }

    public void setCompanyCodeMap(Map<String, ComanyMappingData> companyCodeMap)
    {
        this.companyCodeMap = companyCodeMap;
    }

}