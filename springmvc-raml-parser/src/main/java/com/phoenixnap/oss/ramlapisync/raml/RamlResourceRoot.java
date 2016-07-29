package com.phoenixnap.oss.ramlapisync.raml;

import java.util.Map;

/**
 * RamlResourceRoot is an element that can contain other resources.
 *
 * @author armin.weisser
 */
public interface RamlResourceRoot {

    /**
     * Given a path with more than one segment the getResource(String) method will recursively lookup a matching resource.
     * @param path a relative or absolute URI
     * @return the child RamlResource that matches the given path.
     */
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

    /**
     * @return all direct child resources of this resource.
     */
    Map<String, RamlResource> getResources();

    void addResource(String path, RamlResource childResource);

    void removeResource(String firstResourcePart);

    void addResources(Map<String, RamlResource> resources);

}
