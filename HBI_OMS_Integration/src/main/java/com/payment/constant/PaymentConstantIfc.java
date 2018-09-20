package com.payment.constant;

public interface PaymentConstantIfc
{

    public static final String AMOUNT = "AMOUNT";

    public static final String CURRENCY = "CURRENCY";

    public static final String OPERATION = "OPERATION";

    public static final String ORDERID = "ORDERID";

    public static final String PAYID = "PAYID";

    public static final String PSPID = "PSPID";

    public static final String PSWD = "PSWD";

    public static final String USERID = "USERID";

    public static final String SAL = "SAL";

    public static final String RFD = "RFD";

    public static final String CONTEXT = "com.payment.ncresponse";

    public static final String INGENICO = "Ingenico";

    public static final String ADYEN = "Adyen";

    public static final String FLAG_NOT_PROCESSED = "-1";

    public static final String SCO_GREEN_CATEGORY = "G";

    public static final String SCO_ORANGE_CATEGORY = "O";

    public static final String AUTH_CODE_START_SEQUENCE = "AUTH-";

    public static final String ORDER_MESSAGE_PRINT = "P";

    public static final String PAYMENT_SETTLEMENT_RESPONSE_STATUS_FAILED = "FAILED";

    public static final String PAYMENT_SETTLEMENT_FAILED_STATUS = "-2";
    
    public static final int PAYMENT_SETTLEMENT_AMOUNT_MULTIPLYER = 100;
    
    public static final String SHASIGN = "SHASIGN";
}
