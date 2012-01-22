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
package org.kuali.kra.service.impl;

import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;

public class ResearchDocumentServiceImpl implements ResearchDocumentService {
    
    private RouteHeaderService routeHeaderService;
    
    public String getDocHandlerUrl(String routeHeaderId) {
        DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(routeHeaderId);
        //return routeHeader.getDocumentType().getResolvedDocumentHandlerUrl();
        return routeHeader.getDocumentType().getDocHandlerUrl();    
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }
    
}
