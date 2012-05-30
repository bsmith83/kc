CREATE TABLE BUDGET_PERSON_SALARY_DETAILS
  (BUDGET_PERSON_SALARY_DETAIL_ID NUMBER(12,0),
	PERSON_SEQUENCE_NUMBER NUMBER(3,0),
	BUDGET_ID NUMBER(12,0),
	BUDGET_PERIOD NUMBER(3,0),
	BASE_SALARY NUMBER(12,2),
	UPDATE_TIMESTAMP DATE NOT NULL,
	UPDATE_USER VARCHAR2(60 BYTE) NOT NULL,
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
	OBJ_ID VARCHAR2(36 BYTE) NOT NULL,
	PERSON_ID VARCHAR2(40 BYTE)
  )
/
ALTER TABLE BUDGET_PERSON_SALARY_DETAILS
ADD CONSTRAINT BUDGET_PERSON_SALARY_DETAILSP1
PRIMARY KEY (BUDGET_PERSON_SALARY_DETAIL_ID)
/
