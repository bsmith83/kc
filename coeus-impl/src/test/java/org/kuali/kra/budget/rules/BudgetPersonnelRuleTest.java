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
package org.kuali.kra.budget.rules;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRule;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tests the BudgetPersonnelRule class.
 */
public class BudgetPersonnelRuleTest {

    private Mockery context;
    
    @Before
    public void setupJMock() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }
    
    @Before
    public void setupErrorMap() {
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    /**
     * Test instantiating with null BusinessObjectService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBusinessObjectService() {
        new BudgetPersonnelRule(null, context.mock(ParameterService.class), context.mock(BudgetService.class));
    }
    
    /**
     * Test instantiating with null ConfigurationService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullKualiConfigurationService() {
        new BudgetPersonnelRule(context.mock(BusinessObjectService.class), null, context.mock(BudgetService.class));
    }
    
    /**
     * Test instantiating with null BudgetService.
     */
    @Test(expected = NullPointerException.class)
    public void testNullBudgetService() {
        new BudgetPersonnelRule(context.mock(BusinessObjectService.class), context.mock(ParameterService.class), null);
    }
    
    /**
     * Tests calling processCheckForJobCodeChange with a null document.
     */
    @Test(expected = NullPointerException.class)
    public void testProcessCheckForJobCodeChangeNullDoc() {
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(ParameterService.class), context.mock(BudgetService.class));
        rule.processCheckForJobCodeChange(null, 1);
    }
    
    /**
     * Tests calling processCheckForJobCodeChange with a invalid period number.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testProcessCheckForJobCodeChangeInvalidPeriod() {
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(ParameterService.class), context.mock(BudgetService.class));
        rule.processCheckForJobCodeChange(new ProposalDevelopmentBudgetExt(), 0);
    }
    
    /**
     * This method checks that a validation error occurs when a person's job code is null but has details.
     */
    @Test
    public void testJobCodeNullDetails() {
        final BudgetService budgetService = context.mock(BudgetService.class);
        
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(ParameterService.class), budgetService);
        
        context.checking(new Expectations() {
            {   
                oneOf(budgetService).getApplicableCostElements(1L, "1");
                will(returnValue(Collections.emptyList()));
            }});
        
        Budget budget = getBudget();
        budget.getBudgetPerson(0).setJobCode(null);
        
        rule.processCheckForJobCodeChange(budget, 1);
        
        context.assertIsSatisfied();
        Assert.assertEquals(1, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.budgetPersons[0].jobCode"));
    }
    
    /**
     * This method checks that validation errors do not occur when a
     * person's job code is null and does not have details.
     */
    @Test
    public void testJobCodeNullNoDetails() {
        final BudgetService budgetService = context.mock(BudgetService.class);
        
        BudgetPersonnelRule rule = new BudgetPersonnelRule(context.mock(BusinessObjectService.class),
            context.mock(ParameterService.class), budgetService);
        
        context.checking(new Expectations() {
            {   
                oneOf(budgetService).getApplicableCostElements(1L, "1");
                will(returnValue(Collections.emptyList()));
            }});
        
        Budget budget = getBudget();
        budget.getBudgetPerson(0).setJobCode("Foo");
        
        rule.processCheckForJobCodeChange(budget, 1);
        
        context.assertIsSatisfied();
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorMessages().size());
    }
    
    /**
     * Gets a budget doc with the minimum fields set to execute the current set of tests.
     * @return the BudgetDocument
     */
    private Budget getBudget() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
        
        List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        BudgetPeriod period = new BudgetPeriod();
        
        List<BudgetLineItem> lineItems = new ArrayList<BudgetLineItem>();
        BudgetLineItem lineItem = budget.getNewBudgetLineItem();
        lineItem.setLineItemSequence(1);
        
        List<BudgetPersonnelDetails> lineItemDetails = new ArrayList<BudgetPersonnelDetails>();
        BudgetPersonnelDetails details = lineItem.getNewBudgetPersonnelLineItem();
        details.setLineItemSequence(1);
        details.setPersonSequenceNumber(1);
        lineItemDetails.add(details);
        
        lineItem.setBudgetPersonnelDetailsList(lineItemDetails);
        
        lineItems.add(lineItem);
        period.setBudgetLineItems(lineItems);
        
        periods.add(period);
        
        List<BudgetPerson> persons = new ArrayList<BudgetPerson>();
        BudgetPerson person = new BudgetPerson() {
            private String jc;
            @Override
            public void setJobCode(String jobCode) {
                jc = jobCode;
            }
            @Override
            public String getJobCode() {
                return jc;
            }
        };
        
        person.setPersonSequenceNumber(1);
        person.setBudgetId(1L);
        
        persons.add(person);
        
        budget.setBudgetPeriods(periods);
        budget.setBudgetPersons(persons);
        budget.setBudgetVersionNumber(1);
        return budget;
    }
}
