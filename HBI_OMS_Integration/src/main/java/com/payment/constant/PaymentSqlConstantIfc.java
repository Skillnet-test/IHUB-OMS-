package com.payment.constant;

public interface PaymentSqlConstantIfc
{

    public static final String SQL_READ_ALL_PAYMENT = "SELECT * FROM PAYMENT WHERE ID_STATUS = -1 ORDER BY CREATED_DATE ASC";

    public static final String SQL_UPDATE_PAYMENT = "UPDATE PAYMENT SET RESPONSE = ? , ID_STATUS = ? , MODIFIED_DATE = CURRENT_TIMESTAMP, SCO_STATUS = ? WHERE PAYMENT_ID = ? AND PAYMENT_DATE=?";

    public static final String SQL_INSERT_PAYMENT = "INSERT INTO PAYMENT(PAYMENT_DATE, COMPANY_CODE, AMOUNT , CURRENCY, OPERATION, ORDERID, PAYID, PSPID, PSWD, USERID, ID_STATUS,CREATED_DATE, MODIFIED_DATE, SCO_STATUS) values "
            + "(to_char(sysdate, 'dd-MON-yy'), ?,?,?,?,?,?,?,?,?,-1, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, ?)";

    public static final String SQL_READ_ALL_COMPANY_DETAILS = "SELECT * FROM COMPANY_DETAILS";
    
    public static final String SQL_FIND_ORDER_NUMBER = "SELECT * FROM PAYMENT WHERE ORDERID = ? AND SCO_STATUS = 'G' ";
    
    public static final String SQL_READ_PAYMENT_CONFIG = "SELECT * FROM PAYMENT_CONFIG";

}
