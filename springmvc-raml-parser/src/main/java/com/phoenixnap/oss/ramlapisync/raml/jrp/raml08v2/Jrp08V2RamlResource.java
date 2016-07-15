package com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import org.raml.model.parameter.UriParameter;
import org.raml.v2.api.model.v08.resources.Resource;

import java.util.Map;

/**
 * @author armin.weisser
 */
public class Jrp08V2RamlResource implements RamlResource {

    private final Resource resource;

    public Jrp08V2RamlResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String getRelativeUri() {
        return null;
    }

    @Override
    public Map<RamlActionType, RamlAction> getActions() {
        return null;
    }

    @Override
    public Map<String, UriParameter> getUriParameters() {
        return null;
    }

    @Override
    public Map<String, UriParameter> getResolvedUriParameters() {
        return null;
    }

    @Override
    public String getUri() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public RamlResource getParentResource() {
        return null;
    }

    @Override
    public void setParentResource(RamlResource parentResource) {

    }

    @Override
    public String getParentUri() {
        return null;
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
        return null;
    }

    @Override
    public void addAction(RamlActionType apiAction, RamlAction action) {

    }

    @Override
    public void addActions(Map<RamlActionType, RamlAction> actions) {

    }

    @Override
    public Map<String, RamlResource> getResources() {
        return null;
    }

    @Override
    public void addResource(String path, RamlResource childResource) {

    }

    @Override
    public RamlResource getResource(String path) {
        return null;
    }

    @Override
    public void removeResource(String firstResourcePart) {

    }

    @Override
    public void addResources(Map<String, RamlResource> resources) {

    }
}
