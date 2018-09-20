package com.oms.order.custhistin.formatter;

import com.oms.order.common.formatter.OMSOrderRequest;

/**
 * @author Jigar
 *
 */
public class CustHistInRequest extends OMSOrderRequest
{
    
    private String company;
    
    private String orderNumber;
    
    private String sendDetail;
    
	public CustHistInRequest()
    {
        // TODO Auto-generated constructor stub
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getSendDetail()
    {
        return sendDetail;
    }

    public void setSendDetail(String sendDetail)
    {
        this.sendDetail = sendDetail;
    }

}
