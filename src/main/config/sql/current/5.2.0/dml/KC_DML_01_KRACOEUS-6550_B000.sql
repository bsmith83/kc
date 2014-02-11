INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, 8, '845','Triggered on saving a Financial Entity',
	'New Financial Entity Created',
	'A new financial entity has been created for:<br/>Person Name: {USER_FULLNAME}<br/>Department: {UNIT}<br/>Entity Name: {FE_ENTITY_NAME}<br/>Thank you',
	'N', 'Y', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 8 and ACTION_CODE = '845'), 'KC-COIDISCLOSURE:COI Administrator', 
	null, 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, 8, '846','Triggered on modifying a Financial Entity',
	'Financial Entity Updated',
	'An existing financial entity has been updated for:<br/>Person Name: {USER_FULLNAME}<br/>Department: {UNIT}<br/>Entity Name: {FE_ENTITY_NAME}<br/>Thank you',
	'N', 'Y', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 8 and ACTION_CODE = '846'), 'KC-COIDISCLOSURE:COI Administrator', 
	null, 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, 8, '805','Triggered on completing the assigned reviewers review',
	'Assiged Review Complete',
	'The disclosure status has been changed to "Assigned Review Complete" for:<br/>Disclosure Number: {DISCLOSURE_NUMBER}<br/>Disclosure Status: {DISCLOSURE_STATUS}<br/>Disclosure By: {DISCLOSURE_REPORTER}<br/>Thank you',
	'N', 'Y', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 8 and ACTION_CODE = '805'), 'KC-COIDISCLOSURE:COI Administrator', 
	null, 'admin', SYSDATE, 1, SYS_GUID())
/