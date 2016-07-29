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
        Map<String, RamlUriParameter> expectedUriParameters = expected.getUriParameters();
        Map<String, RamlUriParameter> actualUriParameters = actual.getUriParameters();
        boolean uriParametersOk = checkUriParameters(expectedUriParameters, actualUriParameters);

        if(!uriParametersOk) {
            return false;
        }

        expectedUriParameters = expected.getResolvedUriParameters();
        actualUriParameters = actual.getResolvedUriParameters();
        boolean resolvedUriParametersOk = checkUriParameters(expectedUriParameters, actualUriParameters);

        return uriParametersOk && resolvedUriParametersOk;
    }

    private boolean checkUriParameters(Map<String, RamlUriParameter> expectedUriParameters, Map<String, RamlUriParameter> actualUriParameters) {
        if(expectedUriParameters == actualUriParameters) {
            return true;
        }

        if(expectedUriParameters == null && actualUriParameters != null || expectedUriParameters != null && actualUriParameters == null) {
            return false;
        }

        if(expectedUriParameters.size() != actualUriParameters.size()) {
            return false;
        }

        // check all RamlUriParameters and apply the HasEqualRamlParameterMetaData matcher
        return actualUriParameters.keySet().stream().allMatch(key -> {
            RamlUriParameter actualItem = actualUriParameters.get(key);
            RamlUriParameter expectedItem = expectedUriParameters.get(key);
            return new HasEqualRamlParameterMetaData(actualItem).matches(expectedItem);
        });
    }

    @Override
    public void describeTo(Description description) {

    }
}
