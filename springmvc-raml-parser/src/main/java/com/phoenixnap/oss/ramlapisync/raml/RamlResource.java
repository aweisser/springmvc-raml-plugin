package com.phoenixnap.oss.ramlapisync.raml;


import java.util.Map;

/**
 * A RamlResource is something that can be identified by a URI.
 * In a RAML spec a resource is introduced by a forward slash followed by a name and a colon
 * RAML example:
 * /users:
 *
 * @author armin.weisser
 */
public interface RamlResource extends RamlResourceRoot {

    /**
     * @return Relative URI of this resource from the parent resource
     */
    String getRelativeUri();

    /**
     * @return all actions that can be called on this resource mapped to its ActionType (GET, PUT, POST ....)
     */
    Map<RamlActionType, RamlAction> getActions();

    /**
     * @return URI parameters defined for the current resource
     */
    Map<String, RamlUriParameter> getUriParameters();

    void addUriParameter(String name, RamlUriParameter uriParameter);

    /**
     * @return URI parameters defined for the current resource plus
     * all URI parameters defined in the resource hierarchy
     */
    Map<String, RamlUriParameter> getResolvedUriParameters();

    /**
     * @return the full URI (parentUri + relativeUri)
     */
    String getUri();

    /**
     * @return a brief description of this resource
     */
    String getDescription();

    /**
     * @return the parent resource of null if this resource is a top-level resource
     */
    RamlResource getParentResource();

    void setParentResource(RamlResource parentResource);

    /**
     * @return the full URI of the parent resource.
     */
    String getParentUri();

    void setParentUri(String parentUri);

    void setRelativeUri(String relativeUri);

    void setDisplayName(String displayName);

    void setDescription(String description);

    RamlAction getAction(RamlActionType actionType);

    void addAction(RamlActionType apiAction, RamlAction action);

    void addActions(Map<RamlActionType, RamlAction> actions);
}
