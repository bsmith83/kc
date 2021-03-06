DELIMITER /
CREATE TABLE IACUC_PROTOCOL_PROJECT_TYPE ( 
    PROJECT_TYPE_CODE VARCHAR(3) NOT NULL, 
    DESCRIPTION VARCHAR(200) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


ALTER TABLE IACUC_PROTOCOL_PROJECT_TYPE 
ADD CONSTRAINT PK_IACUC_PROTOCOL_PROJECT_TYPE 
PRIMARY KEY (PROJECT_TYPE_CODE) 
/

DELIMITER ;
