ALTER TABLE ARG_VALUE_LOOKUP 
ADD CONSTRAINT UQ_ARG_VALUE_LOOKUP 
UNIQUE (ARGUMENT_NAME, VALUE);


ALTER TABLE PERSON_TRAINING 
ADD CONSTRAINT UQ_PERSON_TRAINING 
UNIQUE (PERSON_ID, TRAINING_NUMBER);

ALTER TABLE PROTOCOL_ATTACHMENT_PERSONNEL
    ADD CONSTRAINT UQ_PA_PERSONNEL 
    UNIQUE (PROTOCOL_ID, TYPE_CD, VERSION_NUMBER, DOCUMENT_ID);

ALTER TABLE PROTOCOL_ATTACHMENT_STATUS
    ADD CONSTRAINT UQ_PA_STATUS 
    UNIQUE (DESCRIPTION);

ALTER TABLE PROTOCOL_FUNDING_SOURCE 
ADD CONSTRAINT UQ_PROTOCOL_FUNDING_SOURCE 
UNIQUE (PROTOCOL_ID, FUNDING_SOURCE_TYPE_CODE, FUNDING_SOURCE);

ALTER TABLE PROTOCOL_LOCATION
ADD CONSTRAINT UQ_PROTOCOL_LOCATION
UNIQUE (PROTOCOL_ID, PROTOCOL_ORG_TYPE_CODE, ORGANIZATION_ID);

ALTER TABLE PROTOCOL_PERSON_ROLE_MAPPING 
ADD CONSTRAINT UQ_PERSON_MAPPING 
UNIQUE (SOURCE_ROLE_ID, TARGET_ROLE_ID); 

ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT UQ_PROTOCOL_RISK_LEVELS 
UNIQUE (PROTOCOL_NUMBER, SEQUENCE_NUMBER, RISK_LEVEL_CODE);

ALTER TABLE PROTOCOL_UNITS
ADD CONSTRAINT UQ_PROTOCOL_UNITS
UNIQUE (PROTOCOL_PERSON_ID, UNIT_NUMBER, PERSON_ID);

ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT UQ_PROTOCOL_VULNERABLE_SUB
UNIQUE (PROTOCOL_ID, VULNERABLE_SUBJECT_TYPE_CODE);

ALTER TABLE QUESTION_TYPES 
ADD CONSTRAINT UQ_QUESTION_TYPE
UNIQUE (QUESTION_TYPE_NAME);

ALTER TABLE SPONSOR_TERM 
ADD CONSTRAINT U_SPONSOR_TERM 
UNIQUE (SPONSOR_TERM_CODE,SPONSOR_TERM_TYPE_CODE);

ALTER TABLE VALID_BASIS_METHOD_PMT 
ADD CONSTRAINT UQ_VALID_BASIS_METHOD_PMT 
UNIQUE (BASIS_OF_PAYMENT_CODE, METHOD_OF_PAYMENT_CODE);

ALTER TABLE VALID_CLASS_REPORT_FREQ 
ADD CONSTRAINT UQ_VALID_CLASS_REPORT_FREQ 
UNIQUE (REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE);

ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT UQ_VALID_FREQUENCY_BASE 
UNIQUE (FREQUENCY_CODE, FREQUENCY_BASE_CODE);

