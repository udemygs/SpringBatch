DROP TABLE TRANSACTIONS IF EXISTS;
 
CREATE TABLE TRANSACTIONS (
    ID INTEGER,
    ACCOUNTNUMBER VARCHAR(20),  
    TRXAMOUNT DECIMAL(19,2),  
    DESCRIPTION VARCHAR(100),
    CUSTOMERID VARCHAR(50),
    TRXDATETIME TIMESTAMP
) ;