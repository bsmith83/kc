TRUNCATE TABLE BUDGET_PERIOD_TYPE DROP STORAGE
/
INSERT INTO BUDGET_PERIOD_TYPE (BUDGET_PERIOD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Academic','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO BUDGET_PERIOD_TYPE (BUDGET_PERIOD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Calendar','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO BUDGET_PERIOD_TYPE (BUDGET_PERIOD_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Summer','admin',SYSDATE,SYS_GUID(),1)
/
