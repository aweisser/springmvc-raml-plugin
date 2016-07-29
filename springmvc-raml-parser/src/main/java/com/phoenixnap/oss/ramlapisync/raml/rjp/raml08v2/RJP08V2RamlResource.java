package com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import org.raml.v2.api.model.v08.resources.Resource;
import org.raml.v2.api.model.v08.system.types.MarkdownString;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author armin.weisser
 */
public class RJP08V2RamlResource implements RamlResource {

    private static RamlModelFactory ramlModelFactory = new RJP08V2RamlModelFactory();

    private final Resource resource;
    private Map<String, RamlResource> resources = new LinkedHashMap<>();
    private Map<RamlActionType, RamlAction> actions = new LinkedHashMap<>();

    public RJP08V2RamlResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String getRelativeUri() {
        return resource.relativeUri().value();
    }

    @Override
    public Map<RamlActionType, RamlAction> getActions() {
        return ramlModelFactory.transformToUnmodifiableMap(resource.methods(), actions, ramlModelFactory::createRamlAction, ramlModelFactory::createRamlActionType);
    }

    @Override
    public Map<String, RamlUriParameter> getUriParameters() {
        return null;
    }

    @Override
    public void addUriParameter(String name, RamlUriParameter uriParameter) {

    }

    @Override
    public Map<String, RamlUriParameter> getResolvedUriParameters() {
        return null;
    }

    @Override
    public String getUri() {
        return resource.resourcePath();
    }

    @Override
    public String getDescription() {
        MarkdownString description = resource.description();
        return description == null ? null: description.value();
    }

    @Override
    public RamlResource getParentResource() {
        return ramlModelFactory.createRamlResource(resource.parentResource());
    }

    @Override
    public void setParentResource(RamlResource parentResource) {

    }

    @Override
    public String getParentUri() {
        if(getParentResource() == null) return "";
        return getParentResource().getUri();
    }

    @Override
    public void setParentUri(String parentUri) {

    }

    @Override
    public void setRelativeUri(String relativeUri) {

    }

    @Override
    public void setDisplayName(String displayName) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public RamlAction getAction(RamlActionType actionType) {
        return getActions().get(actionType);
    }

    @Override
    public void addAction(RamlActionType apiAction, RamlAction action) {

    }

    @Override
    public void addActions(Map<RamlActionType, RamlAction> actions) {

    }

    @Override
    public Map<String, RamlResource> getResources() {
        return ramlModelFactory.transformToUnmodifiableMap(
                resource.resources(),
                resources,
                ramlModelFactory::createRamlResource,
                r -> r.relativeUri().value());
    }

    @Override
    public void addResource(String path, RamlResource childResource) {

    }

    @Override
    public void removeResource(String firstResourcePart) {

    }

    @Override
    public void addResources(Map<String, RamlResource> resources) {

    }
}
