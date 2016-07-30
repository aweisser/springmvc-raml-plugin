package com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlParamType;
import com.phoenixnap.oss.ramlapisync.raml.RamlQueryParameter;
import org.raml.v2.api.model.v08.parameters.Parameter;
import org.raml.v2.api.model.v08.system.types.MarkdownString;

import java.math.BigDecimal;

/**
 * @author armin.weisser
 */
public class RJP08V2RamlQueryParameter extends RamlQueryParameter {

    public static final RJP08V2RamlModelFactory ramlModelFactory = new RJP08V2RamlModelFactory();
    private final Parameter parameter;

    public RJP08V2RamlQueryParameter(Parameter parameter) {
        this.parameter = parameter;
    }


    @Override
    public void setRepeat(boolean repeat) {

    }

    @Override
    public Integer getMinLength() {
        return null;
    }

    @Override
    public Integer getMaxLength() {
        return null;
    }

    @Override
    public BigDecimal getMinimum() {
        return null;
    }

    @Override
    public BigDecimal getMaximum() {
        return null;
    }

    @Override
    public String getPattern() {
        return null;
    }

    @Override
    public boolean isRepeat() {
        return parameter.repeat();
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
        return ramlModelFactory.createRamlParamType(parameter.type());
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
