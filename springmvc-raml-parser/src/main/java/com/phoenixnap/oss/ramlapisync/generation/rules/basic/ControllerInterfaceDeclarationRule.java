package com.phoenixnap.oss.ramlapisync.generation.rules.basic;

import com.phoenixnap.oss.ramlapisync.data.ApiControllerMetadata;
import com.phoenixnap.oss.ramlapisync.generation.rules.Rule;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

/**
 * @author armin.weisser
 * @since 0.3.2
 */
public class ControllerInterfaceDeclarationRule implements Rule<JPackage,JDefinedClass, ApiControllerMetadata> {

    @Override
    public JDefinedClass apply(ApiControllerMetadata controllerMetadata, JPackage generatableType) {
        String controllerClassName = controllerMetadata.getName();
        JDefinedClass definedClass;
        try {
            definedClass = generatableType._interface(controllerClassName);
        } catch (JClassAlreadyExistsException e1) {
            definedClass = generatableType._getClass(controllerClassName);
        }
        return definedClass;
    }

}