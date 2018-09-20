package com.oms.exception;

public class OMSException extends Exception
{

    private static final long serialVersionUID = 1L;

    private int code;

    public OMSException()
    {
        super();
    }

    public OMSException(String message)
    {
        super(message);
    }

    public OMSException(int code, String message)
    {
        super(message);
        this.setCode(code);
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

}
