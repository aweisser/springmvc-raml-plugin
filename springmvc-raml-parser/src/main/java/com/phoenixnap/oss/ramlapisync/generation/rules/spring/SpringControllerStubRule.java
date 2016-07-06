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

import com.phoenixnap.oss.ramlapisync.data.ApiResourceMetadata;
import com.phoenixnap.oss.ramlapisync.data.ApiActionMetadata;
import com.phoenixnap.oss.ramlapisync.generation.rules.GenericJavaClassRule;
import com.phoenixnap.oss.ramlapisync.generation.rules.Rule;
import com.phoenixnap.oss.ramlapisync.generation.rules.basic.*;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;

/**
 * A code generation Rule that provides a simple Controller stub class with Spring4 annotations and empty method bodies.
 * This is the default code generation rule formally executed by the RamlGenerator.generateClassForRaml(...) method.
 * A raml endpoint called /people for example would generate an artefact like this:
 *
 * {@literal @}RestController
 * {@literal @}RequestMapping("/people")
 * class PeopleController {
 *
 *     {@literal @}RequestMapping(value="", method=RequestMethod.GET)
 *     public ResponseEntity getPeople() {
 *         return null; // TODO Autogenerated Method Stub. Implement me please.
 *     }
 * }
 *
 * After code generation the user has to implement the method bodies.
 * So this solution is mainly usefull for one time code generation.
 *
 * @author armin.weisser
 * @author kurtpa
 * @since 0.4.1
 */
public abstract class SpringControllerStubRule implements Rule<JCodeModel, JDefinedClass, ApiResourceMetadata> {

    @Override
    public final JDefinedClass apply(ApiResourceMetadata metadata, JCodeModel generatableType) {

        GenericJavaClassRule generator = new GenericJavaClassRule()
                .setPackageRule(new PackageRule())
                .setClassCommentRule(new ClassCommentRule())
                .addClassAnnotationRule(getControllerAnnotationRule())
                .addClassAnnotationRule(new SpringRequestMappingClassAnnotationRule())
                .setClassRule(new ControllerClassDeclarationRule())
                .setMethodCommentRule(new MethodCommentRule())
                .addMethodAnnotationRule(new SpringRequestMappingMethodAnnotationRule())
                .addMethodAnnotationRule(getResponseBodyAnnotationRule())
                .setMethodSignatureRule(new ControllerMethodSignatureRule(
                        new SpringSimpleResponseTypeRule(),
                        new SpringMethodParamsRule(),
                        new SpringHeaderMethodParamsRule()
                ))
                .setMethodBodyRule(new ImplementMeMethodBodyRule());

        return generator.apply(metadata, generatableType);
    }
    
    protected abstract Rule<JMethod, JAnnotationUse, ApiActionMetadata> getResponseBodyAnnotationRule();
    
    protected abstract Rule<JDefinedClass, JAnnotationUse, ApiResourceMetadata> getControllerAnnotationRule();
}
