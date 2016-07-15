package com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlDocumentationItem;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.resources.Resource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public class Jrp08V2RamlRoot implements RamlRoot {

    private static Jrp08V2RamlModelFactory ramlModelFactory = new Jrp08V2RamlModelFactory();

    private final Api api;

    public Jrp08V2RamlRoot(Api api) {
        this.api = api;
    }

    @Override
    public String getMediaType() {
        return api.mediaType().value();
    }

    @Override
    public List<Map<String, String>> getSchemas() {
        List<Map<String, String>> schemas = new ArrayList<>();
        api.schemas().forEach(globalSchema -> {
            Map<String, String> schemaProperties = new LinkedHashMap<>();
            schemaProperties.put(globalSchema.key(), globalSchema.value().value());
            schemas.add(schemaProperties);
        });
        return schemas;
    }

    @Override
    public String getBaseUri() {
        return api.baseUri().value();
    }

    @Override
    public RamlResource getResource(String path) {
        for (Resource resource : api.resources()) {
            String relativeUriOfResource = resource.relativeUri().value();
            if (path.startsWith(relativeUriOfResource)) {
                if (path.length() == relativeUriOfResource.length()) {
                    return ramlModelFactory.createRamlResource(resource);
                }
                if (path.charAt(relativeUriOfResource.length()) == '/') {
                    return getResource(path.substring(relativeUriOfResource.length()));
                }
            }
        }
        return null;
    }

    @Override
    public Map<String, RamlResource> getResources() {
        List<Resource> originalResources = api.resources();
        Map<String, RamlResource> resources = new LinkedHashMap<>(originalResources.size());
        originalResources.forEach(resource -> {
            String relativePath = resource.relativeUri().value();
            RamlResource ramlResource = ramlModelFactory.createRamlResource(resource);
            resources.put(relativePath, ramlResource);
        });
        return resources;
    }

    @Override
    public void setBaseUri(String baseUri) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void setVersion(String version) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void setTitle(String title) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void setDocumentation(List<RamlDocumentationItem> documentationItems) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void setMediaType(String mediaType) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void addResource(String path, RamlResource childResource) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void removeResource(String firstResourcePart) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }

    @Override
    public void addResources(Map<String, RamlResource> resources) {
        // TODO the org.raml.v2.api.model.v08.api.Api is a read only interface. How can we generate a raml from java?
    }
}
