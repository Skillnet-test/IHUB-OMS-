
CREATE SEQUENCE PAYMENT_SEQUENCE START WITH 1 MAXVALUE 9999 INCREMENT BY 1 CYCLE NOCACHE;

CREATE TABLE PAYMENT
(
PAYMENT_ID number default PAYMENT_SEQUENCE.nextval,
PAYMENT_DATE VARCHAR2(10),
COMPANY_CODE VARCHAR(20),
AMOUNT VARCHAR2(20),
CURRENCY VARCHAR2(20),
OPERATION VARCHAR2(20),
ORDERID VARCHAR2(20),
PAYID VARCHAR2(20),
PSPID VARCHAR2(20),
PSWD  VARCHAR2(50),
USERID VARCHAR2(20),
ID_STATUS INT,
SCO_STATUS VARCHAR2(10),
RESPONSe VARCHAR2(1000),
CREATED_DATE timestamp,
MODIFIED_DATE timestamp
);