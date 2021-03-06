TRUNCATE TABLE VALID_PROTO_SUB_TYPE_QUAL DROP STORAGE
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'Modification/Amendment/Revisions/Significant New Finding'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'Annual Scheduled by IRB'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'Contingent/Conditional Approval/Deferred Approval/ Non-Approval'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'Protocol-related COI Report'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'Self report for Noncompliance'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'Unanticipated Problems'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_PROTO_SUB_TYPE_QUAL (VALID_PROTO_SUB_TYPE_QUAL_ID,SUBMISSION_TYPE_CODE,SUBMISSION_TYPE_QUAL_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_VALID_SUBM_REVW_TYPE_QUAL.NEXTVAL,(SELECT SUBMISSION_TYPE_CODE FROM SUBMISSION_TYPE WHERE DESCRIPTION = 'FYI'),(SELECT SUBMISSION_TYPE_QUAL_CODE FROM SUBMISSION_TYPE_QUALIFIER WHERE DESCRIPTION = 'DSMB Report'),'admin',SYSDATE,SYS_GUID(),1)
/
