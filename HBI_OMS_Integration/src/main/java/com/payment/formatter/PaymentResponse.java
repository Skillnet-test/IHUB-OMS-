package com.payment.formatter;

public class PaymentResponse
{

    private String response;
    
    private int paymentId;
    
    private String ncresponse;
    
    private String scoCategory;
    
    private String paymentDate;

    public String getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public int getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(int paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getNcresponse()
    {
        return ncresponse;
    }

    public void setNcresponse(String ncresponse)
    {
        this.ncresponse = ncresponse;
    }
    
    public String getScoCategory()
    {
        return scoCategory;
    }

    public void setScoCategory(String scoCategory)
    {
        this.scoCategory = scoCategory;
    }


}
