package com.phoenixnap.oss.ramlapisync.raml;

import com.phoenixnap.oss.ramlapisync.raml.jrp08.Jrp08Action;
import org.raml.model.*;
import org.raml.model.parameter.QueryParameter;

import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlAction {

    // TODO Remove this method and refactor
    static RamlAction asRamlAction(Action action) {
        return new Jrp08Action(action);
    }

    Resource getResource();

    Map<String, QueryParameter> getQueryParameters();

    Map<String, MimeType> getBody();

    Map<String,Response> getResponses();

    boolean hasBody();

    String getDescription();

    ActionType getType();

    List<SecurityReference> getSecuredBy();
}
