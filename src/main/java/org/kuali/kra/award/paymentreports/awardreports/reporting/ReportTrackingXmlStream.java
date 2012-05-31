/*
 * Copyright 2005-2010 The Kuali Foundation
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

package org.kuali.kra.award.paymentreports.awardreports.reporting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import noNamespace.AwardReportingRequirementDocument;
import noNamespace.AwardReportingRequirementDocument.AwardReportingRequirement;
import noNamespace.ReportingRequirement;
import noNamespace.ReportingRequirementDetail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.web.struts.action.ReportTrackingLookupForm;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.DocumentService;

public class ReportTrackingXmlStream implements XmlStream {

    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;

   // private static final Log LOG = LogFactory.getLog(QuestionnaireXmlStream.class);

    /**
     * This method generates XML committee report. It uses data passed in
     * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     *
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
    ReportTracking reporTracking=(ReportTracking)printableBusinessObject;
   int currRowCount = reporTracking.getCurrRowCount();
    AwardReportingRequirementDocument awardReportRequirementDoc=AwardReportingRequirementDocument.Factory.newInstance();
    try {    	
		awardReportRequirementDoc.setAwardReportingRequirement(getAwardReporting(reporTracking,reportParameters));
	} catch (PrintingException e) {
		e.printStackTrace();
	}
       Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        Map<String, XmlObject> map = new HashMap<String,XmlObject>();
          map.put("AwardReportTracking", awardReportRequirementDoc);
          return map;
        
    }




    /**
     *
     * This method is to get the AwardReporting data for print.
     * @param document
     * @param params
     * @return
     * @throws PrintingException
     */
   // @SuppressWarnings("unchecked")
    public AwardReportingRequirement getAwardReporting(KraPersistableBusinessObjectBase
    	printableBusinessObject, Map<String, Object> htData) throws PrintingException{
    	ReportTracking reportTracking =
    	(ReportTracking) printableBusinessObject;
    	AwardReportingRequirement awardReporting =
    		AwardReportingRequirement.Factory.newInstance();
      //awardReporting.setCurrentDate(dateTimeService.getCurrentDate().toString());
    	setReportingRequirements(awardReporting, reportTracking);
    	   return awardReporting;
    }
    private void setReportingRequirements(
    AwardReportingRequirement awardReporting,
    	ReportTracking reportTracking){
    List<ReportingRequirement>reportReqList =
    new ArrayList<ReportingRequirement>();
    ReportingRequirement reportingRequirement =
    ReportingRequirement.Factory.newInstance();
    reportingRequirement.setPrincipleInvestigatorName(
    	reportTracking.getPiName());
    reportingRequirement.setReportClass(reportTracking.getReportClass().getDescription());
      reportingRequirement.setFrequency(reportTracking.getFrequency().getDescription());
    reportingRequirement.setFrequencyBase(
    reportTracking.getFrequencyBase().getDescription());
    if(reportTracking.getBaseDate()!=null){
   reportingRequirement.setBaseDate(reportTracking.getBaseDate().toString());}
    reportingRequirement.setCopyOSP(reportTracking.getOspDistributionCode());
    setReportingRequirementsDetail(awardReporting, reportTracking,reportingRequirement);
      reportReqList.add(reportingRequirement);
    awardReporting.setReportingReqsArray(
   reportReqList.toArray(new ReportingRequirement[0]));
    
    }
    private void setReportingRequirementsDetail(
   AwardReportingRequirement awardReporting, ReportTracking reportTracking,ReportingRequirement reportingRequirement) {
       	List<ReportingRequirementDetail>reportReqDetailList =
    		new ArrayList<ReportingRequirementDetail>();
    	ReportingRequirementDetail reportReqDetails =
    		ReportingRequirementDetail.Factory.newInstance();
    	 	reportReqDetails.setAwardNo(reportTracking.getAwardNumber());
    	reportReqDetails.setUnitNo(reportTracking.
    	getLeadUnit().getUnitNumber());
    	reportReqDetails.setUnitName(reportTracking.
    		getLeadUnit().getUnitName());
    	reportReqDetails.setStatus(reportTracking.getStatusCode());
    	if (reportTracking.getDueDate() != null) {
   	reportReqDetails.setDueDate(reportTracking.getDueDate().toString());
   	}
    	reportReqDetails.setContact(reportTracking.getAuthorPersonName());
    	reportReqDetails.setAddress(reportTracking.getAuthorPersonName());
    	reportReqDetails.setCopies(reportTracking.getItemCount());
    	reportReqDetails.setOverdueNo(reportTracking.getOverdue());
    	if (reportTracking.getActivityDate() != null) {
    	reportTracking.getActivityDate().toString();
    	}
    	reportReqDetails.setComments(reportTracking.getComments());
    	reportReqDetails.setPersonName(reportTracking.getPreparerName());
    	reportReqDetailList.add(reportReqDetails);
    	reportingRequirement.setReportingReqDetailsArray(
    reportReqDetailList.toArray(new ReportingRequirementDetail[0]));

    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}


