package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public class HasSameUriParameters extends BaseMatcher<RamlResource> {
    private final RamlResource actual;
    private List<String> expectedMessages = new ArrayList<>();
    private String but = "";

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
            expectedMessages.add("UriParameters check failed.");
            return false;
        }

        expectedParameters = expected.getResolvedUriParameters();
        actualParameters = actual.getResolvedUriParameters();
        boolean resolvedUriParametersOk = checkUriParameters(expectedParameters, actualParameters);

        if(!resolvedUriParametersOk) {
            expectedMessages.add("Resolved UriParameters check failed. ");
            return false;
        }

        return true;
    }

    private boolean checkUriParameters(Map<String, RamlUriParameter> expectedParameters, Map<String, RamlUriParameter> actualParameters) {
        if(expectedParameters == actualParameters) {
            return true;
        }

        if(expectedParameters == null && actualParameters != null) {
            expectedMessages.add("No UriParameters. ");
            but = "found " + actualParameters.size();
            return false;
        }

        if(expectedParameters != null && actualParameters == null) {
            expectedMessages.add(+expectedParameters.size() +" UriParameters. ");
            but = "found none.";
            return false;
        }

        if(expectedParameters.size() != actualParameters.size()) {
            expectedMessages.add(expectedParameters.size() + " UriParameters. ");
            but = "found " + actualParameters.size();
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
        expectedMessages.forEach(m -> description.appendText(m));
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(but);
    }
}
