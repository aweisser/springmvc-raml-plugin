package com.phoenixnap.oss.ramlapisync.raml.jrp08;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import org.raml.model.*;
import org.raml.model.parameter.QueryParameter;

import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public class Jrp08Action implements RamlAction {

    private Action action;

    public Jrp08Action(Action action) {
        this.action = action;
    }


    @Override
    public Resource getResource() {
        return action.getResource();
    }

    @Override
    public void setResource(Resource resource) {
        action.setResource(resource);
    }

    @Override
    public Map<String, QueryParameter> getQueryParameters() {
        return action.getQueryParameters();
    }

    @Override
    public Map<String, MimeType> getBody() {
        return action.getBody();
    }

    @Override
    public void setBody(Map<String, MimeType> body) {
        action.setBody(body);
    }

    @Override
    public Map<String, Response> getResponses() {
        return action.getResponses();
    }

    @Override
    public boolean hasBody() {
        return action.hasBody();
    }

    @Override
    public String getDescription() {
        return action.getDescription();
    }

    @Override
    public void setDescription(String description) {
        action.setDescription(description);
    }

    @Override
    public ActionType getType() {
        return action.getType();
    }

    @Override
    public void setType(RamlActionType type) {
        action.setType(RamlActionType.asActionType(type));
    }

    @Override
    public List<SecurityReference> getSecuredBy() {
        return action.getSecuredBy();
    }
}
