CREATE SEQUENCE ERROREVENT_SEQUENCE START WITH 1 MAXVALUE 9999 INCREMENT BY 1 CYCLE NOCACHE;

CREATE TABLE ERROR_EVENT
(
EVENT_ID NUMBER DEFAULT ERROREVENT_SEQUENCE.NEXTVAL,
EVENT_DATE VARCHAR2(10),
REQUEST_TYPE VARCHAR2(100),
CONTENT_TYPE VARCHAR2(100),
PAYLOAD CLOB,
ERROR_MESSAGE VARCHAR2(1000),
PROCESS_FLAG INT,
CREATED_DATE TIMESTAMP,
MODIFIED_DATE TIMESTAMP
);