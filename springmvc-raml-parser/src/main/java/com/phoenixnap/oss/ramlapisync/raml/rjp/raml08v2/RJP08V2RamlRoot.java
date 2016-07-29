package com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlDocumentationItem;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.api.GlobalSchema;
import org.raml.v2.api.model.v08.bodies.MimeType;
import org.raml.v2.api.model.v08.resources.Resource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author armin.weisser
 */
public class RJP08V2RamlRoot implements RamlRoot {

    private static RJP08V2RamlModelFactory ramlModelFactory = new RJP08V2RamlModelFactory();

    private final Api api;

    public RJP08V2RamlRoot(Api api) {
        this.api = api;
    }

    /**
     * Expose internal representation only package private
     * @return the internal model
     */
    Api getApi() {
        return api;
    }

    @Override
    public String getMediaType() {
        MimeType mimeType = api.mediaType();
        return mimeType == null ? null : mimeType.value();
    }

    @Override
    public List<Map<String, String>> getSchemas() {
        return api.schemas()
                .stream()
                .map(this::globalSchemaToMap)
                .collect(Collectors.toList());
    }

    private Map<String, String> globalSchemaToMap(GlobalSchema globalSchema) {
        Map<String, String> schemaProperties = new LinkedHashMap<>();
        schemaProperties.put(globalSchema.key(), globalSchema.value().value());
        return schemaProperties;
    }

    @Override
    public String getBaseUri() {
        return api.baseUri().value();
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
        return Collections.unmodifiableMap(resources);
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
