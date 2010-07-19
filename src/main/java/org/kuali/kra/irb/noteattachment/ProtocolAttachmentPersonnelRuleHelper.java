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
package org.kuali.kra.irb.noteattachment;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.rules.ErrorReporter;

/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
 */
class ProtocolAttachmentPersonnelRuleHelper {
    
    private final ProtocolAttachmentService attachmentService;
    private final ErrorReporter errorReporter = new ErrorReporter();
    private String propertyPrefix;

    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    ProtocolAttachmentPersonnelRuleHelper() {
        this(KraServiceLocator.getService(ProtocolAttachmentService.class));
    }
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param aPropertyPrefix the prefix (ex: attachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolAttachmentPersonnelRuleHelper(final String aPropertyPrefix) {
        this();
        this.resetPropertyPrefix(aPropertyPrefix);
    }
    
    /**
     * Creates helper deferring the setting of the prefix to later and setting used services.
     * @param attachmentService the Attachment Service
     * @throws IllegalArgumentException if the attachmentService is null
     */
    ProtocolAttachmentPersonnelRuleHelper(final ProtocolAttachmentService attachmentService) {
        if (attachmentService == null) {
            throw new IllegalArgumentException("the attachmentService is null");
        }
        
        this.attachmentService = attachmentService;
    }
    
    /**
     * Resets the property prefix.
     * @param aPropertyPrefix the prefix (ex: attachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    void resetPropertyPrefix(final String aPropertyPrefix) {
        if (aPropertyPrefix == null) {
            throw new IllegalArgumentException("propertyPrefix is null");
        }
        
        this.propertyPrefix = aPropertyPrefix;
    }
    
    /**
     * Checks that a type/person combination does not already exist for a document. Creates a hard error.
     * @param attachmentPersonnel the attachment.
     * @param protocol the Protocol
     * @return true is valid.
     */
    boolean duplicateTypePerson(final ProtocolAttachmentPersonnel attachmentPersonnel, final Protocol protocol) {
        boolean isValid = true;
        
        if (attachmentPersonnel.getType() != null && attachmentPersonnel.getPerson() != null) {
            for (ProtocolAttachmentPersonnel attachment : protocol.getAttachmentPersonnels()) {
                if (attachment.getType().equals(attachmentPersonnel.getType()) 
                        && attachment.getPerson().getProtocolPersonId().equals(attachmentPersonnel.getPerson().getProtocolPersonId())) {
                    this.errorReporter.reportError(this.propertyPrefix + "." + TypedAttachment.PropertyName.TYPE_CODE,
                        KeyConstants.ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE);
                    isValid = false;
                    continue;
                }
            }    
        }
        
        return isValid;
    }
    
    /**
     * Checks that a person is available for an attachment. Creates a hard error.
     * @param attachmentPersonnel the attachment.
     * @param protocol the Protocol
     * @return true is valid.
     */
    boolean availablePerson(final ProtocolAttachmentPersonnel attachmentPersonnel, final Protocol protocol) {
        
        if (attachmentPersonnel.getPerson() == null || attachmentPersonnel.getPerson().getProtocolPersonId() == null) {
            return true;
        }
        
        boolean personAvilable = false;
        for (ProtocolPerson person : protocol.getProtocolPersons()) {
            if (attachmentPersonnel.getPerson().getProtocolPersonId().equals(person.getProtocolPersonId())) {
                personAvilable = true;
            }
        }
        
        if (!personAvilable) {
            final ProtocolPerson person = this.attachmentService.getPerson(attachmentPersonnel.getPerson().getProtocolPersonId());
            this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentPersonnel.PropertyName.PERSON_ID,
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_INVALID_PERSON, (person != null) ? person.getPersonName() : "");
            return false;
        }
        
        return true;
    }
    
    /**
     * Validates that the selected person exists in the system (is valid). Creates a hard error.
     * @param attachment the attachment.
     * @return true if valid.
     */
    boolean validPerson(final ProtocolAttachmentPersonnel attachment) {
        //This assumes that the person object has been refreshed from the DB
        //and if not found the refresh action set the person to null.
        
        if (attachment.getPerson() == null) {
            this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentPersonnel.PropertyName.PERSON_ID,
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_PERSON);
            return false;
        }
        
        return true;
    }
}
