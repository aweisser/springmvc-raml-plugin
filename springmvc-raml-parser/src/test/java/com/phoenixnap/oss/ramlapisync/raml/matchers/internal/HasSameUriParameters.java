package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;

/**
 * @author armin.weisser
 */
public class HasSameUriParameters extends BaseMatcher<RamlResource> {
    private final RamlResource actual;

    public HasSameUriParameters(RamlResource actual) {
        this.actual = actual;
    }

    @Override
    public boolean matches(Object item) {
        RamlResource expected = (RamlResource) item;
        Map<String, RamlUriParameter> expectedParameters = expected.getUriParameters();
        Map<String, RamlUriParameter> actualParameters = actual.getUriParameters();
        boolean uriParametersOk = checkUriParameters(expectedParameters, actualParameters);

        if(!uriParametersOk) {
            return false;
        }

        expectedParameters = expected.getResolvedUriParameters();
        actualParameters = actual.getResolvedUriParameters();
        boolean resolvedUriParametersOk = checkUriParameters(expectedParameters, actualParameters);

        return uriParametersOk && resolvedUriParametersOk;
    }

    private boolean checkUriParameters(Map<String, RamlUriParameter> expectedParameters, Map<String, RamlUriParameter> actualParameters) {
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
            RamlUriParameter actualItem = actualParameters.get(key);
            RamlUriParameter expectedItem = expectedParameters.get(key);
            return new HasEqualRamlParameterMetaData(actualItem).matches(expectedItem);
        });
    }

    @Override
    public void describeTo(Description description) {

    }
}
