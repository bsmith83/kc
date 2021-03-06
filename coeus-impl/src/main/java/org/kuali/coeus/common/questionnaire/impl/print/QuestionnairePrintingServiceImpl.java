/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.questionnaire.impl.print;

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrint;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("questionnairePrintingService")
public class QuestionnairePrintingServiceImpl implements QuestionnairePrintingService {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    @Autowired
    @Qualifier("printingService")
    private PrintingService printingService;
    @Autowired
    @Qualifier("questionnairePrint")
    private QuestionnairePrint questionnairePrint;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    public AttachmentDataSource printQuestionnaire(
            KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters)
            throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = getQuestionnairePrint();
        if (printable != null) {
            printable.setPrintableBusinessObject(printableBusinessObject);
            printable.setReportParameters(reportParameters);
            source = getPrintingService().print(printable);
            source.setName("Questionnaire-" + reportParameters.get("documentNumber") + Constants.PDF_FILE_EXTENSION);
            source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return source;
    }


    public AttachmentDataSource printQuestionnaireAnswer(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = getQuestionnairePrint();
        if (printable != null) {
            printable.setPrintableBusinessObject(printableBusinessObject);
            printable.setReportParameters(reportParameters);
            source = getPrintingService().print(printable);
            source.setName("QuestionnaireAnswer" + reportParameters.get("questionnaireId") + Constants.PDF_FILE_EXTENSION);
            source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return source;
    }

    
    private Questionnaire getQuestionnaire(Long questionnaireRefId) {
        Map pkMap = new HashMap();
        pkMap.put("id", questionnaireRefId);
        return (Questionnaire)businessObjectService.findByPrimaryKey(Questionnaire.class, pkMap);
        
    }

    @Override
    public List<Printable> getQuestionnairePrintable(KcPersistableBusinessObjectBase printableBusinessObject,
                                                     List<QuestionnairePrintOption> questionnairesToPrints) {
        List<Printable> printables = new ArrayList<Printable>();
        for (QuestionnairePrintOption printOption : questionnairesToPrints) {
            if (printOption.isSelected()) {
                //   AbstractPrint printable = getQuestionnairePrint();
                AbstractPrint printable =  new QuestionnairePrint();
                printable.setXmlStream(getQuestionnairePrint().getXmlStream());
                Map<String, Object> reportParameters = new HashMap<String, Object>();
                Questionnaire questionnaire = getQuestionnaire(printOption.getQuestionnaireId());
                reportParameters.put("questionnaireSeqId", questionnaire.getQuestionnaireSeqIdAsInteger());
                reportParameters.put("id", questionnaire.getId());
                reportParameters.put("template", questionnaire.getTemplate());
                //  will be used by amendquestionnaire
                reportParameters.put("moduleSubItemCode", printOption.getSubItemCode());
                if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
                    reportParameters.put(PROTOCOL_NUMBER, printOption.getItemKey());
                    reportParameters.put(SUBMISSION_NUMBER, printOption.getSubItemKey());
                }

                if (printable != null) {
                    printable.setPrintableBusinessObject(getProtocolPrintable(printOption));
                    printable.setReportParameters(reportParameters);
                    printables.add(printable);
                }
            }
        }
        return printables;
    }

    /*
     * get the appropriate protocol as printable.
     * need further work for requestion submission questionnaire printables
     * which should be retrieved from protocolsubmission ?
     */
    private Protocol getProtocolPrintable(QuestionnairePrintOption printOption) {
        if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
            Map keyValues = new HashMap();
            keyValues.put("protocolNumber", printOption.getItemKey());
            keyValues.put("submissionNumber", printOption.getSubItemKey());
            return (Protocol) ((List<ProtocolSubmission>) businessObjectService.findMatchingOrderBy(ProtocolSubmission.class, keyValues,
                    "submissionId", false)).get(0).getProtocol();
        }
        else {
            Map keyValues = new HashMap();
            keyValues.put("protocolNumber", printOption.getItemKey());
            keyValues.put("sequenceNumber", printOption.getSubItemKey());
            return ((List<Protocol>) businessObjectService.findMatching(Protocol.class, keyValues)).get(0);
        }

    }

    /**
     * @return the printingService
     */
    public PrintingService getPrintingService() {
        return printingService;
    }

    /**
     * @param printingService the printingService to set
     */
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    public QuestionnairePrint getQuestionnairePrint() {
        return questionnairePrint;
    }

    public void setQuestionnairePrint(QuestionnairePrint questionnairePrint) {
        this.questionnairePrint = questionnairePrint;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
