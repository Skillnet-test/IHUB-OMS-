package com.payment.dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component

public class ComanyMappingData implements Serializable
{

    /**
     * @author Vishal_Mohite
     */
    private static final long serialVersionUID = -2815635777742907488L;

    private String companyCode;

    private String companyName;

    private String currencyCode;

    private String companyDesc;

    private String warehouseNumber;

    private String pspId;

    private String userId;

    private String pswd;

    private String passPhrase;

    public String getPassPhrase()
    {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase)
    {
        this.passPhrase = passPhrase;
    }

    public String getCompanyCode()
    {
        return companyCode;
    }

    public void setCompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCompanyDesc()
    {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc)
    {
        this.companyDesc = companyDesc;
    }

    public String getWarehouseNumber()
    {
        return warehouseNumber;
    }

    public void setWarehouseNumber(String warehouseNumber)
    {
        this.warehouseNumber = warehouseNumber;
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

    @Override
    public String toString()
    {
        return "ComanyMappingData [companyCode=" + companyCode + ", companyName=" + companyName + ", currencyCode="
                + currencyCode + ", companyDesc=" + companyDesc + ", warehouseNumber=" + warehouseNumber + ", pspId="
                + pspId + ", userId=" + userId + ", pswd=" + pswd + ", passPhrase=" + passPhrase + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyCode == null) ? 0 : companyCode.hashCode());
        result = prime * result + ((companyDesc == null) ? 0 : companyDesc.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
        result = prime * result + ((passPhrase == null) ? 0 : passPhrase.hashCode());
        result = prime * result + ((pspId == null) ? 0 : pspId.hashCode());
        result = prime * result + ((pswd == null) ? 0 : pswd.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((warehouseNumber == null) ? 0 : warehouseNumber.hashCode());
        return result;
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
        ComanyMappingData other = (ComanyMappingData)obj;
        if (companyCode == null)
        {
            if (other.companyCode != null)
                return false;
        }
        else if (!companyCode.equals(other.companyCode))
            return false;
        if (companyDesc == null)
        {
            if (other.companyDesc != null)
                return false;
        }
        else if (!companyDesc.equals(other.companyDesc))
            return false;
        if (companyName == null)
        {
            if (other.companyName != null)
                return false;
        }
        else if (!companyName.equals(other.companyName))
            return false;
        if (currencyCode == null)
        {
            if (other.currencyCode != null)
                return false;
        }
        else if (!currencyCode.equals(other.currencyCode))
            return false;
        if (passPhrase == null)
        {
            if (other.passPhrase != null)
                return false;
        }
        else if (!passPhrase.equals(other.passPhrase))
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
        if (userId == null)
        {
            if (other.userId != null)
                return false;
        }
        else if (!userId.equals(other.userId))
            return false;
        if (warehouseNumber == null)
        {
            if (other.warehouseNumber != null)
                return false;
        }
        else if (!warehouseNumber.equals(other.warehouseNumber))
            return false;
        return true;
    }

}
