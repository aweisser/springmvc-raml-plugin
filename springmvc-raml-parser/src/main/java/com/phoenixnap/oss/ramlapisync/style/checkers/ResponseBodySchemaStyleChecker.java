/*
 * Copyright 2002-2016 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.phoenixnap.oss.ramlapisync.style.checkers;

import com.phoenixnap.oss.ramlapisync.naming.SchemaHelper;
import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.style.RamlStyleCheckerAdapter;
import com.phoenixnap.oss.ramlapisync.style.StyleIssue;
import com.phoenixnap.oss.ramlapisync.verification.IssueLocation;
import org.raml.model.ActionType;
import org.raml.model.MimeType;
import org.raml.model.Raml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Style checker that will check for existance of valid schemas in request bodies
 * 
 * @author kurtpa
 * @since 0.5.2
 *
 */
public class ResponseBodySchemaStyleChecker extends RamlStyleCheckerAdapter {

	/**
	 * Class Logger
	 */
	protected static final Logger logger = LoggerFactory.getLogger(ResponseBodySchemaStyleChecker.class);
	
	public static String DESCRIPTION = "Action %s should define response body schema";
	
	private Set<String> actionsToEnforce = new LinkedHashSet<String>();

	public ResponseBodySchemaStyleChecker(String actionTypesToCheck) {
		String[] tokens = StringUtils.delimitedListToStringArray(actionTypesToCheck, ",", " ");
		for (String token : tokens) {
			actionsToEnforce.add(token);
		}
	}

	@Override
	public Set<StyleIssue> checkActionStyle(ActionType key, RamlAction value,
			IssueLocation location, Raml raml) {
		logger.debug("Checking Action: " + key);
		Set<StyleIssue> issues = new LinkedHashSet<>();
		
		//Do we have a check for this verb?
		if (actionsToEnforce.contains(key.name())) {			
			boolean schemaFound = false;
			// Now the response
			if (value.getResponses() != null && !value.getResponses().isEmpty()) {
				if (value.getResponses().containsKey("200") && value.getResponses().get("200").getBody() != null) {
					Map<String, MimeType> successResponse = value.getResponses().get("200").getBody();
					if (SchemaHelper.containsBodySchema(successResponse, raml, true)) {
						schemaFound = true;
					}
				}
				if (value.getResponses().containsKey("201") && value.getResponses().get("201").getBody() != null) {
					Map<String, MimeType> createdResponse = value.getResponses().get("201").getBody();
					if (SchemaHelper.containsBodySchema(createdResponse, raml, true)) {
						schemaFound = true;
					}
				}
			}
			
			if (!schemaFound) {
				issues.add(new StyleIssue(location, String.format(DESCRIPTION, key), value.getResource(), value));
			}
		}
			
		return issues;
	}
	
	
	
}
