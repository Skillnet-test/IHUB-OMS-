package com.payment.data;

import java.io.Serializable;

public class PaymentData implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int paymentId;

    private String amount;

    private String currency;

    private String operation;

    private String orderId;

    private String payId;

    private String pspId;

    private String pswd;

    private String userId;

    private int idStatus;

    private String companyCode;

    private String paymentDate;

    private String scostatus;

    private String orderChannel;

    public String getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public String getCompanyCode()
    {
        return companyCode;
    }

    public void setCompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public int getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(int paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getPayId()
    {
        return payId;
    }

    public void setPayId(String payId)
    {
        this.payId = payId;
    }

    public String getPspId()
    {
        return pspId;
    }

    public void setPspId(String pspId)
    {
        this.pspId = pspId;
    }

    public String getPswd()
    {
        return pswd;
    }

    public void setPswd(String pswd)
    {
        this.pswd = pswd;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public int getIdStatus()
    {
        return idStatus;
    }

    public void setIdStatus(int idStatus)
    {
        this.idStatus = idStatus;
    }

    public String getScoStatus()
    {
        return scostatus;
    }

    public void setScoStatus(String scostatus)
    {
        this.scostatus = scostatus;
    }

    public String getOrderChannel()
    {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel)
    {
        this.orderChannel = orderChannel;
    }

    @Override
    public String toString()
    {
        return "PaymentData [paymentId=" + paymentId + ", amount=" + amount + ", currency=" + currency + ", operation="
                + operation + ", orderId=" + orderId + ", payId=" + payId + ", pspId=" + pspId + ", pswd=" + pswd
                + ", userId=" + userId + ", idStatus=" + idStatus + ", companyCode=" + companyCode + ", paymentDate="
                + paymentDate + ", scostatus=" + scostatus + ", orderChannel=" + orderChannel + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentData other = (PaymentData)obj;
        if (amount == null)
        {
            if (other.amount != null)
                return false;
        }
        else if (!amount.equals(other.amount))
            return false;
        if (companyCode == null)
        {
            if (other.companyCode != null)
                return false;
        }
        else if (!companyCode.equals(other.companyCode))
            return false;
        if (currency == null)
        {
            if (other.currency != null)
                return false;
        }
        else if (!currency.equals(other.currency))
            return false;
        if (idStatus != other.idStatus)
            return false;
        if (operation == null)
        {
            if (other.operation != null)
                return false;
        }
        else if (!operation.equals(other.operation))
            return false;
        if (orderChannel == null)
        {
            if (other.orderChannel != null)
                return false;
        }
        else if (!orderChannel.equals(other.orderChannel))
            return false;
        if (orderId == null)
        {
            if (other.orderId != null)
                return false;
        }
        else if (!orderId.equals(other.orderId))
            return false;
        if (payId == null)
        {
            if (other.payId != null)
                return false;
        }
        else if (!payId.equals(other.payId))
            return false;
        if (paymentDate == null)
        {
            if (other.paymentDate != null)
                return false;
        }
        else if (!paymentDate.equals(other.paymentDate))
            return false;
        if (paymentId != other.paymentId)
            return false;
        if (pspId == null)
        {
            if (other.pspId != null)
                return false;
        }
        else if (!pspId.equals(other.pspId))
            return false;
        if (pswd == null)
        {
            if (other.pswd != null)
                return false;
        }
        else if (!pswd.equals(other.pswd))
            return false;
        if (scostatus == null)
        {
            if (other.scostatus != null)
                return false;
        }
        else if (!scostatus.equals(other.scostatus))
            return false;
        if (userId == null)
        {
            if (other.userId != null)
                return false;
        }
        else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
