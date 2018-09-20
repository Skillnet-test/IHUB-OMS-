package com.oms.exception;

public enum OMSErrorCodes
{
    DEFAULT(0, "Some error has occured"), 
    SUCCESS(1, "Success"), 
    DATABASE(2,"A database error has occured."),
    CONNECT_ERROR(3,"Network connection error has occured"),
    NO_ORDER_PROCESS(4, "No order to process"),
    JSON_ERROR(5, "Error occured in parsing JSON response object"),
    NO_RECORDS_FOUND(6, "No records found"),
    RESPONSE_ERROR(7, "Error occurred while parsing response"),
    REQUEST_PARSE_ERROR(8, "Error occurred while parsing/creating request object"),
    COMPANY_CODE_NOT_FOUND(9,"Company Code Not Found"),
    PAYMENT_INSERT_ERROR(10, "Insert failed for payment"),
    REQUEST_SHASIGN_GENERATE_ERROR(11, "Error Occurred while generating the SHASign key");


    private final int code;

    private final String description;

    private OMSErrorCodes(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public int getCode()
    {
        return code;
    }

    @Override
    public String toString()
    {
        return code + ": " + description;
    }
}

