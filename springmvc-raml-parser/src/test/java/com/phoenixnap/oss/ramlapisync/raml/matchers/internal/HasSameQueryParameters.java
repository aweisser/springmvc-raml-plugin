package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlQueryParameter;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;

/**
 * @author armin.weisser
 */
public class HasSameQueryParameters extends BaseMatcher<RamlAction> {
    private final RamlAction actual;

    public HasSameQueryParameters(RamlAction actual) {
        this.actual = actual;
    }

    @Override
    public boolean matches(Object item) {
        RamlAction expected = (RamlAction) item;
        Map<String, RamlQueryParameter> expectedParameters = expected.getQueryParameters();
        Map<String, RamlQueryParameter> actualParameters = actual.getQueryParameters();
        if(expectedParameters == actualParameters) {
            return true;
        }

        if(expectedParameters == null && actualParameters != null || expectedParameters != null && actualParameters == null) {
            return false;
        }

        if(expectedParameters.size() != actualParameters.size()) {
            return false;
        }

        // check all RamlUriParameters and apply the HasEqualRamlParameterMetaData matcher
        return actualParameters.keySet().stream().allMatch(key -> {
            RamlQueryParameter actualItem = actualParameters.get(key);
            RamlQueryParameter expectedItem = expectedParameters.get(key);
            return new HasEqualRamlParameterMetaData(actualItem).matches(expectedItem);
        });
    }

    @Override
    public void describeTo(Description description) {

    }
}
