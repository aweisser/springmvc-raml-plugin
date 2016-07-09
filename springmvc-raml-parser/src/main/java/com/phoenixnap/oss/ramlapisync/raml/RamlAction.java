package com.phoenixnap.oss.ramlapisync.raml;

import org.raml.model.*;
import org.raml.model.parameter.QueryParameter;

import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlAction {

    Resource getResource();

    void setResource(Resource resource);

    Map<String, QueryParameter> getQueryParameters();

    Map<String, MimeType> getBody();

    void setBody(Map<String, MimeType> body);

    Map<String,Response> getResponses();

    boolean hasBody();

    String getDescription();

    void setDescription(String description);

    ActionType getType();

    void setType(RamlActionType type);

    List<SecurityReference> getSecuredBy();

}
