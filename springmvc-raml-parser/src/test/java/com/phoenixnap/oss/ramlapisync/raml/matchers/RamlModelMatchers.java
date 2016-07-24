package com.phoenixnap.oss.ramlapisync.raml.matchers;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEquivalentRamlRootMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEquivalentResourceMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEquivalentResourcesMetaData;
import org.hamcrest.Matcher;

import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlModelMatchers {

    static Matcher<RamlRoot> hasEquivalentMetaData(RamlRoot root) {
        return new HasEquivalentRamlRootMetaData(root);
    }

    static Matcher<Map<String, RamlResource>> hasEquivalentMetaData(Map<String, RamlResource> resources) {
        return new HasEquivalentResourcesMetaData(resources);
    }

    static Matcher<RamlResource> hasEquivalentMetaData(RamlResource resource) {
        return new HasEquivalentResourceMetaData(resource);
    }

}
