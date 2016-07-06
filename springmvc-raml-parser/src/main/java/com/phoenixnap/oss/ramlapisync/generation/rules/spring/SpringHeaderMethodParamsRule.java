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
package com.phoenixnap.oss.ramlapisync.generation.rules.spring;

import com.phoenixnap.oss.ramlapisync.generation.CodeModelHelper;
import com.phoenixnap.oss.ramlapisync.generation.rules.basic.HeaderMethodParamsRule;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JVar;
import org.raml.model.parameter.Header;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Generates method parameters with Spring annotations for an endpoint defined by headers in ApiMappingMetadata.
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
 * ({@literal @}RequestHeader(name = "Required-Header", required = false) Integer requiredHeader
 *  , {@literal @}RequestHeader(name = "Optional-Header", required = false) String optionalHeader
 *  , {@literal @}RequestHeader(name = "Optional-Header-2", required = false)  BigDecimal optionalHeader2
 * )
 *
 * @author marco.fenske
 * @since 0.8.0
 */
public class SpringHeaderMethodParamsRule extends HeaderMethodParamsRule {

    @Override
    protected JVar headerParam(String name, Header header, CodeModelHelper.JExtMethod generatableType) {
        JVar jVar = super.headerParam(name, header, generatableType);
        JAnnotationUse jAnnotationUse = jVar.annotate(RequestHeader.class);
        jAnnotationUse.param("name", name);
        // In RAML headers are optional unless the required attribute is included and its value set to 'true'.
        // In Spring a parameter is required by default unless the required attribute is included and its value is set to 'false'
        // So we just need to set required=false if the RAML "required" header is not set or explicitly set to false.
        if(!header.isRequired()) {
            jAnnotationUse.param("required", false);
        }

        if(StringUtils.hasText(header.getDefaultValue())) {
            jAnnotationUse.param("defaultValue", header.getDefaultValue());
        }

        return jVar;
    }

}
