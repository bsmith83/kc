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
package org.kuali.kra.personmasschange.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProposalLogPersonMassChangeService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Proposal Logs.
 * 
 * Person roles that might be replaced are: Principal Investigator.
 */
public class ProposalLogPersonMassChangeServiceImpl implements ProposalLogPersonMassChangeService {
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;

    @Override
    public List<ProposalLog> getProposalLogChangeCandidates(PersonMassChange personMassChange) {
        Set<ProposalLog> proposalLogChangeCandidates = new HashSet<ProposalLog>();
        
        List<ProposalLog> proposalLogs = new ArrayList<ProposalLog>();
        if (personMassChange.getProposalLogPersonMassChange().requiresChange()) {
            proposalLogs.addAll(getBusinessObjectService().findAll(ProposalLog.class));
        }

        for (ProposalLog proposalLog : proposalLogs) {
            if (isProposalLogChangeCandidate(personMassChange, proposalLog)) {
                proposalLogChangeCandidates.add(proposalLog);
            }
        }
        
        return new ArrayList<ProposalLog>(proposalLogChangeCandidates);
    }
    
    private boolean isProposalLogChangeCandidate(PersonMassChange personMassChange, ProposalLog proposalLog) {
        return isPersonIdMassChange(personMassChange, proposalLog.getPiId()) || isRolodexIdMassChange(personMassChange, proposalLog.getRolodexId());
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<ProposalLog> proposalLogChangeCandidates) {
        for (ProposalLog proposalLogChangeCandidate : proposalLogChangeCandidates) {
            if (personMassChange.getReplacerPersonId() != null) {
                KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                proposalLogChangeCandidate.setPiId(kcPerson.getPersonId());
                proposalLogChangeCandidate.setRolodexId(null);
                proposalLogChangeCandidate.setPiName(kcPerson.getFullName());
            } else if (personMassChange.getReplacerRolodexId() != null) {
                Rolodex rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                proposalLogChangeCandidate.setPiId(null);
                proposalLogChangeCandidate.setRolodexId(rolodex.getRolodexId());
                proposalLogChangeCandidate.setPiName(null);
            }

            getBusinessObjectService().save(proposalLogChangeCandidate);
        }
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, String personId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && replaceePersonId.equals(personId);
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, Integer rolodexId) {
        Integer replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && replaceeRolodexId.equals(rolodexId);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public RolodexService getRolodexService() {
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

}