DELIMITER /

insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) values ('KC','KC-GEN','A','US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE','CONFG','1','US citizen or non citizen','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) values ('KC','KC-GEN','A','PERMANENT_RESIDENT_OF_US_TYPE_CODE','CONFG','2','Permanent resident of us','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) values ('KC','KC-GEN','A','NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE','CONFG','3','Non us citizen with temporary visa','A',UUID())
/
commit
/

DELIMITER ;