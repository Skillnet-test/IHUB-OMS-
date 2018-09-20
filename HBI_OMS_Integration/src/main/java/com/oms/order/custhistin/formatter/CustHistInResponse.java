/**
 * 
 */
package com.oms.order.custhistin.formatter;

/**
 * @author Jigar
 */
public class CustHistInResponse
{

    protected String status;
    
    private String creditCardAuthNumber;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCreditCardAuthNumber()
    {
        return creditCardAuthNumber;
    }

    public void setCreditCardAuthNumber(String creditCardAuthNumber)
    {
        this.creditCardAuthNumber = creditCardAuthNumber;
    }

}
