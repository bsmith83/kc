DELIMITER /
CREATE TABLE COI_DISCLOSURE_ATTACHMENT
   (COIA_COI_DISCLOSURE_ID DECIMAL(12,0) NOT NULL, 
    COI_DISCLOSURE_ID_FK DECIMAL(12,0) NOT NULL,
    COI_DISCLOSURE_NUMBER VARCHAR(20) NOT NULL,
    DOCUMENT_ID DECIMAL(4,0) NOT NULL,
    FILE_ID DECIMAL(22,0) NOT NULL,
    DESCRIPTION VARCHAR(200) NOT NULL, 
    CONTACT_NAME VARCHAR(30),
    EMAIL_ADDRESS VARCHAR(60),
    PHONE_NUMBER VARCHAR(20),
    COMMENTS VARCHAR(300),
    DOCUMENT_STATUS_CODE VARCHAR(3),
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(10) NOT NULL,
    SEQUENCE_NUMBER DECIMAL(4,0),
    PROJECT_ID VARCHAR(12),
    ENTITY_SEQUENCE_NUMBER DECIMAL(6,0),
    ORIGINAL_COI_DISCLOSURE_ID  DECIMAL(12,0),
    ENTITY_NUMBER VARCHAR(10)
    ) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
DELIMITER ;
