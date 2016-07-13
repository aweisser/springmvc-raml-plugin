package com.phoenixnap.oss.ramlapisync.raml.jrp08;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactoryOfFactories;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import org.raml.model.Resource;
import org.raml.model.parameter.UriParameter;

import java.util.Map;

/**
 * @author armin.weisser
 */
public class Jrp08RamlResource implements RamlResource {

    private final RamlModelFactory ramlModelFactory = RamlModelFactoryOfFactories.createRamlModelFactory();

    private final Resource resource;
    private Map<String, RamlResource> resources;
    private Map<RamlActionType, RamlAction> actions;
    private RamlResource parentResource;

    public Jrp08RamlResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Map<String, UriParameter> getUriParameters() {
        return resource.getUriParameters();
    }

    @Override
    public RamlResource getParentResource() {
        if(parentResource == null) {
            parentResource = ramlModelFactory.createRamlResource(resource.getParentResource());
        }
        return parentResource;
    }

    @Override
    public String getUri() {
        return resource.getUri();
    }

    @Override
    public String getRelativeUri() {
        return resource.getRelativeUri();
    }

    @Override
    public void setRelativeUri(String relativeUri) {
        resource.setRelativeUri(relativeUri);
    }

    @Override
    public String getDescription() {
        return resource.getDescription();
    }

    @Override
    public Map<RamlActionType, RamlAction> getActions() {
        if(actions == null) {
            actions = ramlModelFactory.createRamlActions(resource.getActions());
        }
        return actions;
    }

    @Override
    public Map<String, RamlResource> getResources() {
        if(resources == null) {
            resources = ramlModelFactory.createRamlResources(resource.getResources());
        }
        return resources;
    }

    @Override
    public Map<String, UriParameter> getResolvedUriParameters() {
        return resource.getResolvedUriParameters();
    }

    @Override
    public RamlAction getAction(RamlActionType actionType) {
        return getActions().get(actionType);
    }

    @Override
    public void setDescription(String description) {
        resource.setDescription(description);
    }

    @Override
    public void setDisplayName(String displayName) {
        resource.setDisplayName(displayName);
    }

    @Override
    public RamlResource getResource(String resourceName) {
        return ramlModelFactory.createRamlResource(resource.getResource(resourceName));
    }

    @Override
    public String getParentUri() {
        return resource.getParentUri();
    }

    @Override
    public void setParentResource(RamlResource parentResource) {
        resource.setParentResource(ramlModelFactory.createResource(parentResource));
    }

    @Override
    public void setParentUri(String parentUri) {
        resource.setParentUri(parentUri);
    }
}
