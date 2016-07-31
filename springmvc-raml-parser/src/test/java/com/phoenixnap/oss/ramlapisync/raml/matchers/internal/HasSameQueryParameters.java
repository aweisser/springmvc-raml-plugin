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
    private String expectedMessage = "";
    private String but = "";

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

        if(expectedParameters == null && actualParameters != null) {
            expectedMessage = "No QueryParameters.";
            but =  "found " + actualParameters.size();
            return false;
        }

        if(expectedParameters != null && actualParameters == null) {
            expectedMessage = expectedParameters.size() +" QueryParameters.";
            but = "Found none.";
            return false;
        }

        if(expectedParameters.size() != actualParameters.size()) {
            expectedMessage = expectedParameters.size() + " QueryParameters";
            but = "found " + actualParameters.size();
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
        description.appendText(expectedMessage);
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(but);
    }
}
