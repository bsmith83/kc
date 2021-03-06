/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.proposal.framework.mail.MailType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * This class tests KraPersistableBusinessObjectBase.
 */
public class KraPersistableBusinessObjectBaseTest extends KcIntegrationTestBase {

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }

    @Test public void testBeforeInsertQuickstart() throws Exception {
        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("quickstart", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeUpdateQuickstart() throws Exception {
        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("quickstart", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeInsertUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeUpdateUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
    }

    private void updateAsserts(String udpateUser, KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase) {
        assertEquals(udpateUser, kraPersistableBusinessObjectBase.getUpdateUser());
        Timestamp updateTimestamp = kraPersistableBusinessObjectBase.getUpdateTimestamp();
        assertNotNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());

        Date currentDate = new Date(System.currentTimeMillis());
        long diff = updateTimestamp.getTime() - currentDate.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

}
