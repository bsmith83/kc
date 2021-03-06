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
package org.kuali.coeus.common.budget.framework.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.datadictionary.KNSDocumentEntry;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.web.ui.ExtraButton;

import javax.persistence.MappedSuperclass;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BudgetParentDocument<T extends BudgetParent> extends KcTransactionalDocumentBase implements BudgetDocumentTypeChecker, Permissionable {

    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }
    
    public void updateDocumentDescriptions(List<? extends Budget> budgets) {
        for (Budget budgetVersion : budgets) {
            if (budgetVersion.isNameUpdatable() && !StringUtils.isBlank(budgetVersion.getName())) {
                budgetVersion.setNameUpdatable(false);
            }
        }
    }

    /**
     * This method gets the next budget version number for this proposal. We can't use documentNextVersionNumber because that
     * requires a save and we don't want to save the proposal development document before forwarding to the budget document.
     * 
     * @return Integer
     */
    public Integer getNextBudgetVersionNumber() {
        List<? extends Budget> versions = getBudgetParent().getBudgets();
        if (versions.isEmpty()) {
            return 1;
        }
        Collections.sort(versions, new Comparator<Budget>(){
			@Override
			public int compare(Budget o1, Budget o2) {
				return new CompareToBuilder().append(o1.getBudgetVersionNumber(), o2.getBudgetVersionNumber()).toComparison() * -1;
			}
        });
        Budget lastVersion = versions.get(0);
        return lastVersion.getBudgetVersionNumber() + 1;
    }

    public Budget getBudgetDocumentVersion(int selectedLine) {
        return getBudgetParent().getBudgets().get(selectedLine);
    }

    public void updateBudgetDescriptions(List<? extends AbstractBudget> budgetVersions) {
        for (AbstractBudget budgetVersion : budgetVersions) {
            if (budgetVersion.isNameUpdatable() && !StringUtils.isBlank(budgetVersion.getName())) {
                budgetVersion.setNameUpdatable(false);
            }
        }
    }
    
    public abstract Permissionable getBudgetPermissionable();

    public abstract boolean isComplete();

    public abstract String getTaskGroupName();

    public abstract Task getParentAuthZTask(String taskName);

    public abstract ExtraButton configureReturnToParentTopButton();

    public abstract T getBudgetParent();

}
