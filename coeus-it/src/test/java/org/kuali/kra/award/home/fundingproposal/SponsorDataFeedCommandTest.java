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
package org.kuali.kra.award.home.fundingproposal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SponsorDataFeedCommandTest extends BaseDataFeedCommandTest {
    
    private ProposalDataFeedCommandBase command;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        initializeProposal();
    }
    
    @Test
    public void testSponsorFeed() {
        command = new SponsorDataFeedCommand(award, proposal, FundingProposalMergeType.NEWAWARD);
        command.performDataFeed();
        Assert.assertEquals(proposal.getSponsorCode(), award.getSponsorCode());
        Assert.assertEquals(proposal.getSponsor().getSponsorCode(), award.getSponsor().getSponsorCode());
        Assert.assertEquals(proposal.getSponsor().getSponsorName(), award.getSponsor().getSponsorName());
        Assert.assertEquals(proposal.getPrimeSponsorCode(), award.getPrimeSponsorCode());
        Assert.assertEquals(proposal.getPrimeSponsor().getSponsorName(), award.getPrimeSponsor().getSponsorName());
    }
    
    @Test
    public void testReplaceSponsorFeed() {
        command = new SponsorDataFeedCommand(award, proposal, FundingProposalMergeType.REPLACE);
        command.performDataFeed();
        Assert.assertNotSame(proposal.getSponsorCode(), award.getSponsorCode());
        Assert.assertEquals(proposal.getPrimeSponsorCode(), award.getPrimeSponsorCode());
        Assert.assertEquals(proposal.getPrimeSponsor().getSponsorName(), award.getPrimeSponsor().getSponsorName());        
    }
    
    private void initializeProposal() {
        proposal.setSponsorCode("000107");
        proposal.setPrimeSponsorCode("000107");
        proposal.refreshReferenceObject("sponsor");
        proposal.refreshReferenceObject("primeSponsor");
        proposal.setCfdaNumber("abc.123");
        proposal.setNsfCode("abc");
    }
}
