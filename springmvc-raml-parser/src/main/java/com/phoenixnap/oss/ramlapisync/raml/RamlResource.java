package com.phoenixnap.oss.ramlapisync.raml;

import org.raml.model.parameter.UriParameter;

import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlResource {

    Map<String, UriParameter> getUriParameters();

    RamlResource getParentResource();

    String getUri();

    String getRelativeUri();

    String getDescription();

    void setDescription(String description);

    Map<RamlActionType,RamlAction> getActions();

    Map<String,RamlResource> getResources();

    Map<String, UriParameter> getResolvedUriParameters();

    RamlAction getAction(RamlActionType actionType);

    void setRelativeUri(String relativeUri);

    void setDisplayName(String displayName);

    RamlResource getResource(String resourceName);

    void setParentResource(RamlResource parentResource);

    void setParentUri(String parentUri);

    String getParentUri();
}
