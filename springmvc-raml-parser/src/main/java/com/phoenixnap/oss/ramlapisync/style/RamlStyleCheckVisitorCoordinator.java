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
package com.phoenixnap.oss.ramlapisync.style;

import com.phoenixnap.oss.ramlapisync.naming.Pair;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactoryOfFactories;
import com.phoenixnap.oss.ramlapisync.verification.*;
import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

import java.util.*;
import java.util.Map.Entry;

/**
 * Provides a Vistor pattern approach to Style Checks. Iterates through the model and invokes callbacks on specific checkers.
 * 
 * @author Kurt Paris
 * @since 0.0.2
 *
 */
public class RamlStyleCheckVisitorCoordinator implements RamlChecker {
	
	/**
	 * Boolean flag to enable style checking of code too. Since RAML and code should be in sync this could be kept off to improve performance
	 */
	private boolean ignoreCodeStyle = true;
	
	private Set<Issue> warnings = new LinkedHashSet<>();
	
	private List<RamlStyleChecker> checkers;
	
	public RamlStyleCheckVisitorCoordinator (List<RamlStyleChecker> styleChecks) {		
		checkers = new ArrayList<>();
		checkers.addAll(styleChecks);	
	}
	
	/**
	 * Performs a specific check across two Raml Models. 
	 * 
	 * @param published The Raml as published in the contract
	 * @param implemented The Raml as generated from the implementation
	 * @return A pair containing a list of Warnings and an empty list of Errors (as first and second respectively)
	 */
	public Pair<Set<Issue>, Set<Issue>> check (Raml published, Raml implemented) {
		
		checkChildren(published.getResources(), published, IssueLocation.CONTRACT);
		if (!ignoreCodeStyle && implemented != null) {
			checkChildren(implemented.getResources(), implemented, IssueLocation.SOURCE);
		}
		
		return new Pair<Set<Issue>, Set<Issue>>(warnings, Collections.emptySet());		
	}



	private void checkChildren(Map<String, Resource> resources, Raml raml, IssueLocation location) {
		if (resources != null) {
			for (Entry<String, Resource> entry : resources.entrySet()) {
				Resource resource = entry.getValue();
				for (RamlStyleChecker checker : checkers) {
					warnings.addAll(checker.checkResourceStyle(entry.getKey(), resource, location, raml));
				}
				
				Map<String, UriParameter> uriParameters = resource.getUriParameters();
				if(uriParameters != null) {
					for (Entry<String, UriParameter> uriParameter : uriParameters.entrySet()) {
						for (RamlStyleChecker checker : checkers) {
							warnings.addAll(checker.checkParameterStyle(uriParameter.getKey(), uriParameter.getValue()));
						}
					}
				}
				
				Map<ActionType, Action> actions = resource.getActions();
				if (actions != null) {
					for (Entry<ActionType, Action> actionEntry : actions.entrySet()) {
						for (RamlStyleChecker checker : checkers) {
							warnings.addAll(checker.checkActionStyle(actionEntry.getKey(), RamlModelFactoryOfFactories.createRamlModelFactory().createRamlAction(actionEntry.getValue()), location, raml));
						}
						
						/*
						 * If we have query parameters in this call check it 
						 */
						Map<String, QueryParameter> queryParameters = actionEntry.getValue().getQueryParameters();
						if(queryParameters != null) {
							for (Entry<String, QueryParameter> queryParam : queryParameters.entrySet()) {
								for (RamlStyleChecker checker : checkers) {
									warnings.addAll(checker.checkParameterStyle(queryParam.getKey(), queryParam.getValue()));
								}
							}
						}
						
						
					}
				}
				checkChildren(resource.getResources(), raml, location);
			}
		}
	}
	
	
	
	protected final void addIssue(IssueLocation location, String description, String ramlLocation) {
		warnings.add(new Issue(IssueSeverity.WARNING, location, IssueType.STYLE, description, ramlLocation));
	}

}
