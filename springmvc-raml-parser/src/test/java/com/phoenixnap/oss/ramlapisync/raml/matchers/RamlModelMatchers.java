package com.phoenixnap.oss.ramlapisync.raml.matchers;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualActionMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualRamlRootMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualResourceMetaData;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasEqualSchemas;
import com.phoenixnap.oss.ramlapisync.raml.matchers.internal.HasSameUriParameters;
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

    static Matcher<RamlResource> hasSameMetaDataAs(RamlResource actuelResource) {
        return new HasEqualResourceMetaData(actuelResource);
    }

    static Matcher<RamlAction> hasSameMetaDataAs(RamlAction actualAction) {
        return new HasEqualActionMetaData(actualAction);
    }

    static Matcher<Map<String, RamlResource>> hasSameMetaDataAs(Map<String, RamlResource> actualResources) {
        return new RecursiveMultiResourcesMatcher(actualResources, actual -> new HasEqualResourceMetaData(actual));
    }

    static Matcher<Map<String, RamlResource>> hasSameActionsMetaDataAs(Map<String, RamlResource> actualResources) {
        return new RecursiveMultiResourcesMatcher(actualResources, actualResource -> new MultiActionsMatcher(actualResource, actualAction -> new HasEqualActionMetaData(actualAction)));
    }


    static Matcher<? super RamlResource> hasSameUriParametersAs(RamlResource actual) {
        return new HasSameUriParameters(actual);
    }

}
