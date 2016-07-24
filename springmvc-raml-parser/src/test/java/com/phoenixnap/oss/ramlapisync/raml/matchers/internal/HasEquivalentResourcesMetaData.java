package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;

/**
 * @author armin.weisser
 */
public class HasEquivalentResourcesMetaData extends BaseMatcher<Map<String, RamlResource>> {


    private final Map<String, RamlResource> actualResources;

    public HasEquivalentResourcesMetaData(Map<String, RamlResource> resources) {
        this.actualResources = resources;
    }

    @Override
    public boolean matches(Object item) {
        Map<String, RamlResource> expectedResources = (Map<String, RamlResource>) item;

        // check identy
        if (actualResources == expectedResources) {
            return true;
        }

        // check same size
        if (actualResources != null && expectedResources != null && actualResources.size() != expectedResources.size()) {
            return false;
        }

        // check all elements
        return actualResources.keySet().stream().allMatch(key -> {
            RamlResource actualResource = actualResources.get(key);
            RamlResource expectedResource = expectedResources.get(key);
            return new HasEquivalentResourceMetaData(actualResource).matches(expectedResource);
        });
    }

    @Override
    public void describeTo(Description description) {
    }
}