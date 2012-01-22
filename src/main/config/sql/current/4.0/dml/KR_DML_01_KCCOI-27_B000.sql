INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','View Restricted Notes in Coi Disclosure','View Coi Restricted Notes','KC-COIDISCLOSURE',sys_guid(),KRIM_PERM_ID_BS_S.NEXTVAL,
  (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'View Document Section'),'1')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM= 'COI Administrator'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Coi Restricted Notes'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM= 'KC Superuser'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM= 'View Coi Restricted Notes'), 'Y')
/
--  Creates a system parameter to define the default sort behavior of protocol attachments
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,VAL,PARM_DESC_TXT,PARM_TYP_CD,EVAL_OPRTR_CD,APPL_ID,OBJ_ID,VER_NBR) 
VALUES ('KC-COIDISCLOSURE','Document','coiAttachmentDefaultSort','LAUP','Default sort for coi disclosure attachments','CONFG','A','KC',sys_guid(),'1')
/
