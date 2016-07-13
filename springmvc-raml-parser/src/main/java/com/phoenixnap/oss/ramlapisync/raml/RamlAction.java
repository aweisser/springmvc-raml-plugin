package com.phoenixnap.oss.ramlapisync.raml;

import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.SecurityReference;
import org.raml.model.parameter.QueryParameter;

import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlAction {

    RamlResource getResource();

    void setResource(RamlResource resource);

    Map<String, QueryParameter> getQueryParameters();

    Map<String, MimeType> getBody();

    void setBody(Map<String, MimeType> body);

    Map<String,Response> getResponses();

    boolean hasBody();

    String getDescription();

    void setDescription(String description);

    RamlActionType getType();

    void setType(RamlActionType type);

    List<SecurityReference> getSecuredBy();

}
