package com.phoenixnap.oss.ramlapisync.raml;

import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlResourceRoot {

    default RamlResource getResource(String path) {
        String[] segments = path.split("/");
        RamlResourceRoot current = this;
        RamlResource resource = null;
        for(String segment: segments) {
            if(segment != null && !"".equals(segment)) {
                resource = current.getResources().get("/" + segment);
                current = resource;
            }
        }
        return resource;
    }

    Map<String, RamlResource> getResources();

    void addResource(String path, RamlResource childResource);

    void removeResource(String firstResourcePart);

    void addResources(Map<String, RamlResource> resources);

}
