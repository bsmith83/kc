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
package org.kuali.kra.institutionalproposal.notification;

import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * Defines the service to fill in module role qualifier information for Institutional Proposal.
 */
public interface InstitutionalProposalNotificationRoleQualifierService extends KcNotificationRoleQualifierService {

    /**
     * Returns the Institutional Proposal.
     * 
     * @return the Institutional Proposal
     */
    InstitutionalProposal getInstitutionalProposal();
    
    /**
     * Sets the Institutional Proposal.
     *
     * @param institutionalProposal the Institutional Proposal to set
     */
    void setInstitutionalProposal(InstitutionalProposal institutionalProposal);
    
}