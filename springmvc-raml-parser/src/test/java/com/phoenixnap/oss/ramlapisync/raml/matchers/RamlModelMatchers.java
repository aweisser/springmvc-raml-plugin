package com.phoenixnap.oss.ramlapisync.raml.matchers;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualActionMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualRamlRootMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualResourceMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualSchemas;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.MultiActionsMatcher;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.RecursiveMultiResourcesMatcher;
import org.hamcrest.Matcher;

import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlModelMatchers {

    static Matcher<RamlRoot> hasSameMetaDataAs(RamlRoot root) {
        return new HasEqualRamlRootMetaData(root);
    }

    static Matcher<RamlRoot> hasSameSchemasAs(RamlRoot root) {
        return new HasEqualSchemas(root);
    }

    static Matcher<Map<String, RamlResource>> hasSameMetaDataAs(Map<String, RamlResource> resources) {
        return new RecursiveMultiResourcesMatcher(resources, actual -> new HasEqualResourceMetaData(actual));
    }

    static Matcher<Map<String, RamlResource>> hasSameActionsMetaDataAs(Map<String, RamlResource> resources) {
        return new RecursiveMultiResourcesMatcher(resources, actualResource -> new MultiActionsMatcher(actualResource, actualAction -> new HasEqualActionMetaData(actualAction)));
    }

}
