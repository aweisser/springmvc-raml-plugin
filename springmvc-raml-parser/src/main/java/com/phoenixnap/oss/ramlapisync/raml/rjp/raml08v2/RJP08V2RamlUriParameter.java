package com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlParamType;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import com.phoenixnap.oss.ramlapisync.raml.UnknownParamTypeException;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.system.types.MarkdownString;

/**
 * @author armin.weisser
 */
public class RJP08V2RamlUriParameter extends RamlUriParameter {

    private final Parameter parameter;

    public RJP08V2RamlUriParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public void setType(RamlParamType paramType) {
    }

    @Override
    public void setRequired(boolean required) {

    }

    @Override
    public void setExample(String example) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public boolean isRequired() {
        return parameter.required();
    }

    @Override
    public RamlParamType getType() {
        RamlParamType ramlParamType = RamlParamType.valueOf(parameter.type().toUpperCase());
        if(ramlParamType == null) {
            throw new UnknownParamTypeException(parameter.type());
        }
        return ramlParamType;
    }

    @Override
    public String getExample() {
        return parameter.example();
    }

    @Override
    public void setDisplayName(String displayName) {

    }

    @Override
    public String getDescription() {
        MarkdownString description = parameter.description();
        return description == null ? null : description.value();
    }

    @Override
    public String getDisplayName() {
        return parameter.displayName();
    }
}
