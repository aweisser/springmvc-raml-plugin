package com.phoenixnap.oss.ramlapisync.raml;

import java.util.List;
import java.util.Map;

/**
 * A RamlAction represents an executable method on a resource.
 * Every RamlAction is identified by its type (GET, PUT, POST, DELETE ...).
 * Every RamlResource can only have one RamlAction for each type.
 *
 * @author armin.weisser
 */
public interface RamlAction {

    RamlActionType getType();

    Map<String, RamlQueryParameter> getQueryParameters();

    Map<String, RamlResponse> getResponses();

    RamlResource getResource();

    Map<String, RamlHeader> getHeaders();

    Map<String, RamlMimeType> getBody();

    boolean hasBody();

    String getDescription();

    void setDescription(String description);

    void setBody(Map<String, RamlMimeType> body);

    void setResource(RamlResource resource);

    void setType(RamlActionType actionType);

    List<RamlSecurityReference> getSecuredBy();

    void addResponse(String httpStatus, RamlResponse response);

    void addQueryParameters(Map<String, RamlQueryParameter> queryParameters);
}
