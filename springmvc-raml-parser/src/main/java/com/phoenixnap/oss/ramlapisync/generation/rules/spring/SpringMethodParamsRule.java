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

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.phoenixnap.oss.ramlapisync.data.ApiActionMetadata;
import com.phoenixnap.oss.ramlapisync.data.ApiParameterMetadata;
import com.phoenixnap.oss.ramlapisync.generation.CodeModelHelper;
import com.phoenixnap.oss.ramlapisync.generation.rules.basic.MethodParamsRule;
import com.phoenixnap.oss.ramlapisync.raml.RamlHeader;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JVar;

/**
 * Generates all method parameters with Spring annotations needed for an endpoint defined by ApiMappingMetadata.
 * This includes path variables, request parameters and the request body.
 *
 * INPUT:
 * #%RAML 0.8
 * title: myapi
 * mediaType: application/json
 * baseUri: /
 * /base:
 *   /{id}/elements:
 *     get:
 *       queryParameters:
 *         requiredQueryParam:
 *           type: integer
 *           required: true
 *         optionalQueryParam:
 *           type: string
 *         optionalQueryParam2:
 *           type: number
 *           required: false
 *
 * OUTPUT:
 * ({@literal @}PathVariable String id
 *  , {@literal @}RequestParam Integer requiredQueryParam
 *  , {@literal @}RequestParam(required=false) String optionalQueryParam
 *  , {@literal @}RequestParam(required=false) BigDecimal optionalQueryParam2
 * )
 *
 * @author armin.weisser
 * @since 0.4.1
 */
public class SpringMethodParamsRule extends MethodParamsRule {

    protected JVar param(ApiParameterMetadata paramMetaData, CodeModelHelper.JExtMethod generatableType) {
        JVar jVar = super.param(paramMetaData, generatableType);
        JAnnotationUse jAnnotationUse;
        if (paramMetaData.getRamlParam() != null && paramMetaData.getRamlParam() instanceof RamlUriParameter) {
            jVar.annotate(PathVariable.class);
            return jVar;
        } else if (paramMetaData.getRamlParam() != null && paramMetaData.getRamlParam() instanceof RamlHeader) {
            jAnnotationUse = jVar.annotate(RequestHeader.class);
            jAnnotationUse.param("name", paramMetaData.getName());
            if (!paramMetaData.getRamlParam().isRequired()) {
                jAnnotationUse.param("required", false);
            }
            return jVar;
        } else {
            jAnnotationUse = jVar.annotate(RequestParam.class);
            // In RAML parameters are optional unless the required attribute is included and its value set to 'true'.
            // In Spring a parameter is required by default unlesse the required attribute is included and its value is set to 'false'
            // So we just need to set required=false if the RAML "required" parameter is not set or explicitly set to false.
            if(paramMetaData.getRamlParam() != null && !paramMetaData.getRamlParam().isRequired()) {
                jAnnotationUse.param("required", false);
            }
            return jVar;
        }

    }

    @Override
    protected JVar param(ApiActionMetadata endpointMetadata, CodeModelHelper.JExtMethod generatableType) {
        JVar param = super.param(endpointMetadata, generatableType);
        param.annotate(Valid.class);
        param.annotate(RequestBody.class);
        return param;
    }

    @Override
    protected JVar paramHttpHeaders(CodeModelHelper.JExtMethod generatableType) {
        JVar paramHttpHeaders = super.paramHttpHeaders(generatableType);
        paramHttpHeaders.annotate(RequestHeader.class);
        return paramHttpHeaders;
    }

}
