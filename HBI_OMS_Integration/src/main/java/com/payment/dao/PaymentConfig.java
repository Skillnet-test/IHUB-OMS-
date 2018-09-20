package com.payment.dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component

public class PaymentConfig implements Serializable
{

    /**
     * @author Vishal_Mohite
     */
    private static final long serialVersionUID = -2815635777742907488L;

    private String companyCode;

    private String orderChannel;

    private String pspId;

    private String userId;

    private String pswd;

    private String passPhrase;

    public String getCompanyCode()
    {
        return companyCode;
    }

    public void setCompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public String getOrderChannel()
    {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel)
    {
        this.orderChannel = orderChannel;
    }

    public String getPspId()
    {
        return pspId;
    }

    public void setPspId(String pspId)
    {
        this.pspId = pspId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPswd()
    {
        return pswd;
    }

    public void setPswd(String pswd)
    {
        this.pswd = pswd;
    }

    public String getPassPhrase()
    {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase)
    {
        this.passPhrase = passPhrase;
    }

    @Override
    public String toString()
    {
        return "PaymentConfig [companyCode=" + companyCode + ", orderChannel=" + orderChannel + ", pspId=" + pspId
                + ", userId=" + userId + ", pswd=" + pswd + ", passPhrase=" + passPhrase + "]";
    }

}
