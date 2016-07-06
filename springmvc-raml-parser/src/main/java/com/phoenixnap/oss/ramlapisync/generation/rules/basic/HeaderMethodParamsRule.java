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
package com.phoenixnap.oss.ramlapisync.generation.rules.basic;

import com.phoenixnap.oss.ramlapisync.data.ApiActionMetadata;
import com.phoenixnap.oss.ramlapisync.generation.CodeModelHelper;
import com.phoenixnap.oss.ramlapisync.generation.rules.Rule;
import com.phoenixnap.oss.ramlapisync.naming.NamingHelper;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JVar;
import org.raml.model.parameter.Header;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.phoenixnap.oss.ramlapisync.generation.CodeModelHelper.findFirstClassBySimpleName;
import static org.springframework.util.StringUtils.*;

/**
 * Generates method parameters for an endpoint defined by headers in ApiMappingMetadata.
 *
 * INPUT:
 * #%RAML 0.8
 * title: myapi
 * mediaType: application/json
 * baseUri: /
 * /base:
 *   /{id}/elements:
 *     get:
 *       headers:
 *         Required-Header:
 *           type: integer
 *           required: true
 *         Optional-Header:
 *           type: string
 *         Optional-Header-2:
 *           type: number
 *           required: false
 *
 * OUTPUT:
 * (Integer requiredHeader
 *  , String optionalHeader
 *  , BigDecimal optionalHeader2
 * )
 *
 * @author marco.fenske
 * @since 0.8.0
 */
public class HeaderMethodParamsRule implements Rule<CodeModelHelper.JExtMethod, JMethod, ApiActionMetadata> {

	boolean addParameterJavadoc = false;

	public HeaderMethodParamsRule() {
		this(false);
	}

	/**
	 * If set to true, the rule will also add a parameter javadoc entry
	 *
	 * @param addParameterJavadoc Set to true for javadocs for parameters
	 */
	public HeaderMethodParamsRule(boolean addParameterJavadoc) {
		this.addParameterJavadoc = addParameterJavadoc;
	}
	
    @Override
    public JMethod apply(ApiActionMetadata endpointMetadata, CodeModelHelper.JExtMethod generatableType) {

	    Map<String, Header> headers = endpointMetadata.getAction().getHeaders();

	    if(headers != null) {
		    headers.forEach((name, header) -> {
			    headerParam(name, header, generatableType);
		    });
	    }

        return generatableType.get();
    }

	protected JVar headerParam(String headerName, Header header, CodeModelHelper.JExtMethod generatableType) {
		if (addParameterJavadoc) {
			String paramComment = "";
			if (StringUtils.hasText(header.getDescription())) {
				paramComment = NamingHelper.cleanForJavadoc(header.getDescription());
			}
			generatableType.get().javadoc().addParam(headerName + " " + paramComment);
		}
		JClass headerParamType = findFirstClassBySimpleName(generatableType.owner(), capitalize(header.getType().toString().toLowerCase()));
		return generatableType.get().param(headerParamType, uncapitalize(deleteAny(headerName, "-")));
	}

}
