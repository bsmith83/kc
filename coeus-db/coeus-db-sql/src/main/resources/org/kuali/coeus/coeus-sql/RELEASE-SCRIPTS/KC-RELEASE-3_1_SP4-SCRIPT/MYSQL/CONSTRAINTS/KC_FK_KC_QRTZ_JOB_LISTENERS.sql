ALTER TABLE KC_QRTZ_JOB_LISTENERS 
ADD CONSTRAINT FK_KC_QRTZ_JOB_LISTENERS FOREIGN KEY (JOB_NAME,JOB_GROUP)
        REFERENCES KC_QRTZ_JOB_DETAILS(JOB_NAME,JOB_GROUP);