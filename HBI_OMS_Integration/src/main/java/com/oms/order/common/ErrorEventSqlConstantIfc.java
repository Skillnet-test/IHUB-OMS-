package com.oms.order.common;

public interface ErrorEventSqlConstantIfc
{
    
    public static final String SQL_INSERT_ERROR_EVENT = "INSERT INTO ERROR_EVENT(REQUEST_TYPE, EVENT_DATE, CONTENT_TYPE, PAYLOAD, ERROR_MESSAGE, PROCESS_FLAG, CREATED_DATE, MODIFIED_DATE) values "
            + "( ?, to_char(sysdate, 'dd-MON-yy'), ?,?,?,0, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";

    public static final String SQL_SELECT_ERROR_EVENT = "SELECT * FROM ERROR_EVENT WHERE PROCESS_FLAG=0 ORDER BY CREATED_DATE ASC";
    
    public static final String SQL_UPDATE_ERROR_EVENT = "UPDATE ERROR_EVENT SET PROCESS_FLAG = ? WHERE EVENT_ID = ? AND EVENT_DATE=?";

}
