-- View for Coeus compatibility 

CREATE OR REPLACE VIEW OSP$SPECIAL_REVIEW_USAGE AS SELECT 
    SPECIAL_REVIEW_CODE, 
    MODULE_CODE,
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM SPECIAL_REVIEW_USAGE;