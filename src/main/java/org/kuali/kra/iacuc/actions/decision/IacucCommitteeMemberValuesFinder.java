/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.decision;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.Committee;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.iacuc.actions.IacucActionsKeyValuesBase;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttendance;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;


/**
 * 
 */
public class IacucCommitteeMemberValuesFinder extends IacucActionsKeyValuesBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1547047028487421985L;

    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        Protocol protocol = getProtocol();
        if (protocol != null) {
            ProtocolSubmission submission = getCurrentSubmission(protocol);
            if (submission != null) {
                String committeeId = submission.getCommitteeId();
                Committee committee = getCommitteeService().getCommitteeById(committeeId);
                if (committee != null) {
                    List<CommitteeMembership> members = committee.getCommitteeMemberships();
                    for (CommitteeMembership member : members) {
                        if (member.isActive() && isReviewerAttendingMeeting(member)) {
                            keyValues.add(new ConcreteKeyValue(member.getCommitteeMembershipId().toString(), member.getPersonName()));
                        }
                    }
                }
            }
        }
        
        return keyValues;
    }

    private ProtocolSubmission getCurrentSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.IN_AGENDA) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
    }

    private Protocol getProtocol() {
        KualiForm form = KNSGlobalVariables.getKualiForm();
        if (form != null && form instanceof ProtocolForm) {
            return ((ProtocolForm) form).getProtocolDocument().getProtocol();
        }
        return null;
    }
    
    private boolean isReviewerAttendingMeeting(CommitteeMembership member) {
        Protocol prot = getProtocol();
        boolean retVal = false;
        if (prot != null) {
            List<CommitteeScheduleAttendance> attendees = prot.getProtocolSubmission().getCommitteeSchedule().getCommitteeScheduleAttendances();
            for (CommitteeScheduleAttendance attendee : attendees) {
                if (attendee.isCommitteeMember(member)) {
                    return true;
                }
            }
        }
        return retVal;
        
    }
}