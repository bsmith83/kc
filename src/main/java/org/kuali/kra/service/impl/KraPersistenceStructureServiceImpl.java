/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.FieldDescriptor;
import org.kuali.core.exceptions.ClassNotPersistableException;
import org.kuali.core.service.impl.PersistenceStructureServiceImpl;
import org.kuali.kra.service.KraPersistenceStructureService;

public class KraPersistenceStructureServiceImpl extends PersistenceStructureServiceImpl implements KraPersistenceStructureService { 

    public Map<String, String> getDBColumnToObjectAttributeMap(Class clazz) throws ClassNotPersistableException {
        Map<String, String> fieldMap = new HashMap<String, String>();
        if(isPersistable(clazz)) {
            ClassDescriptor classDescriptor = getClassDescriptor(clazz);
            FieldDescriptor fieldDescriptors[] = classDescriptor.getFieldDescriptions();
            for(FieldDescriptor fieldDescriptor : fieldDescriptors) {
                fieldMap.put(fieldDescriptor.getColumnName(), fieldDescriptor.getAttributeName());
            }
            return fieldMap;
        } 
        
        throw new ClassNotPersistableException(clazz.getName() + " is not Persistable");
    }

    public Map<String, String> getPersistableAttributesColumnMap(Class clazz) throws ClassNotPersistableException {
        Map<String, String> fieldMap = new HashMap<String, String>();
        if(isPersistable(clazz)) {
            ClassDescriptor classDescriptor = getClassDescriptor(clazz);
            FieldDescriptor fieldDescriptors[] = classDescriptor.getFieldDescriptions();
            for(FieldDescriptor fieldDescriptor : fieldDescriptors) {
                fieldMap.put(fieldDescriptor.getAttributeName(), fieldDescriptor.getColumnName());
            }
            return fieldMap;
        } 
        
        throw new ClassNotPersistableException(clazz.getName() + " is not Persistable");
     }

    protected Map<String, FieldDescriptor> getPersistableAttributesMap(Class clazz)  throws ClassNotPersistableException {
       Map<String, FieldDescriptor> fieldMap = new HashMap<String, FieldDescriptor>();
       if(isPersistable(clazz)) {
           ClassDescriptor classDescriptor = getClassDescriptor(clazz);
           FieldDescriptor fieldDescriptors[] = classDescriptor.getFieldDescriptions();
           for(FieldDescriptor fieldDescriptor : fieldDescriptors) {
               fieldMap.put(fieldDescriptor.getAttributeName(), fieldDescriptor);
           }
           return fieldMap;
       } 
       
       throw new ClassNotPersistableException(clazz.getName() + " is not Persistable");
    }

}
