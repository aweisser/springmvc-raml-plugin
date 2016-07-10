package com.phoenixnap.oss.ramlapisync.raml.jrp08;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactory;
import org.raml.model.Action;

/**
 * @author armin.weisser
 */
public class Jrp08RamlModelFactory implements RamlModelFactory {

    @Override
    public RamlAction createRamlAction() {
        return new Jrp08Action(new Action());
    }

    @Override
    public RamlAction createRamlAction(Object baseAction) {
        if(baseAction == null) return null;
        return new Jrp08Action((Action)baseAction);
    }

    @Override
    public Action createAction(RamlAction ramlAction) {
        Action action = new Action();
        action.setBody(ramlAction.getBody());
        action.setDescription(ramlAction.getDescription());
        action.setQueryParameters(ramlAction.getQueryParameters());
        action.setResource(ramlAction.getResource());
        action.setResponses(ramlAction.getResponses());
        action.setSecuredBy(ramlAction.getSecuredBy());
        action.setType(ramlAction.getType());
        return action;
    }
}
