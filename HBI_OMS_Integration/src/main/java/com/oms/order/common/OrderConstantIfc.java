package com.oms.order.common;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderConstantIfc
{

    public static final String INVOICE_OUT_JSON_QUEUE_NAME_KEY = "queueName";

    public static final String INVOICE_OUT_JSON_STATUS_KEY = "status";

    public static final String INVOICE_OUT_JSON_MESSAGE_KEY = "Message";

    public static final String INVOICE_OUT_RESPONSE_STATUS_OK = "OK";

    public static final String INVOICE_OUT_RESPONSE_STATUS_EOF = "EOF";

    public static final String INVOICE_OUT_RESPONSE_STATUS_FAILED = "FAILED";

    public static final String REQUEST_AUTHORIZATION_TOKEN_NAME = "authorization";

    public static final String REQUEST_CONTENT_TYPE_TOKEN_NAME = "content-type";

    public static final int ORDER_STATUS_UNDEFINED = -1;

    public static final int ORDER_STATUS_NEW = 0;

    public static final int ORDER_STATUS_PRINTED = 1;

    public static final int ORDER_STATUS_PARTIAL = 2;

    public static final int ORDER_STATUS_FILLED = 3;

    public static final int ORDER_STATUS_CANCELED = 4;

    public static final int ORDER_STATUS_COMPLETED = 5;

    public static final int ORDER_STATUS_VOIDED = 6;

    public static final int ORDER_STATUS_SUSPENDED_CANCELED = 7;

    public static final int ORDER_TYPE_SPECIAL = 1;

    public static final int ORDER_TYPE_ON_HAND = 2;

    public static final int ORDER_TYPE_WEB = 3;

    public static final int ORDER_TYPE_UNDEFINED = -1;

    public static final int ORDER_CHANNEL_MOBILE_APP = 0;

    public static final int ORDER_CHANNEL_CALL_CENTER = 1;

    public static final int ORDER_CHANNEL_OIS = 2;

    public static final int ORDER_CHANNEL_WEB = 3;

    public static final int ORDER_CHANNEL_NOT_APPLICABLE = -1;

    public static final String RFL_REASON_CODE = "999";

    public static final String RFL_REASON_CODE_NAME = "REWARD DISCOUNT";

    public static final String DATE_FORMAT_YYYY_MM_DD = "YYYY-MM-DD";

    public static final String INVOICE_TYPE_SALES = "I";

    public static final String INVOICE_TYPE_RETURN = "C";

    public static final String ORDER_ITEM_DEPT = "0";

    public static final String ORDER_ITEM_MERCH_HRCHY_GROUP = "0";

    public static final String REQUEST_TYPE_INVOICE_OUT = "INVOICEOUT";

    public static final String REQUEST_TYPE_PICK_OUT = "PICKOUT";

    public static final String REQUEST_TYPE_CUSTHISTORY_OUT = "CUSTHISTORY OUT";

    public static final String REQUEST_TYPE_INVOICE_OUT_INGENICO_REFUND = "INGENICO REFUND";

    public static final String REQUEST_TYPE_INVOICE_OUT_ADYEN_REFUND = "ADYEN REFUND";

    public static final String REQUEST_TYPE_INGENICO_PAYMENT_SETTLEMENT = "INGENICO PAYMENT";

    public static final String REQUEST_TYPE_INGENICO_FRAUD_CHECK = "INGENICO FRAUD CHECK";

    public static final String REQUEST_TYPE_ADYEN_REFUND_SETTLEMENT = "ADYEN REFUND PAYMENT";

    public static final String APPLICATION_MODE = "DEV";

    public static final String ORDER_MSG_STRING_TOKENIZER = "|";

    public static final int ORDER_MSG_RFL_ID_FIELD = 4;

    public static final int ORDER_MSG_RFL_AMOUNT_FIELD = 3;

    public static final int ORDER_MSG_MAX_FIELD_SIZE = 5;
    
    public static final int ERROR_EVENT_SUCCESS = 1;
    
    public static final int ERROR_EVENT_FAILED = 2;
    
    public static final String WEB_ORDER_RETURN_REASON_CODE="77";
    
    public static final String WEB_ORDER_RETURN_REASON_CODE_NAME="Web Order";

}
