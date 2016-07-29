package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Map;
import java.util.function.Function;

/**
 * Iterate through all RamlActions of the given RamlResources objects and apply the Matcher<RamlAction> instance returned by the given Function ...
 *
 * @author armin.weisser
 */
public class MultiActionsMatcher extends BaseMatcher<RamlResource> {


    private final RamlResource actual;
    private final Function<RamlAction, Matcher<RamlAction>> matcherFactory;

    public MultiActionsMatcher(RamlResource actual, Function<RamlAction, Matcher<RamlAction>> matcherFactory) {
        this.actual = actual;
        this.matcherFactory = matcherFactory;
    }

    @Override
    public boolean matches(Object item) {
        RamlResource expected = (RamlResource) item;
        Map<RamlActionType, RamlAction> expectedActions = expected.getActions();
        Map<RamlActionType, RamlAction> actualActions = actual.getActions();


        // check identy
        if (expectedActions == actualActions) {
            return true;
        }

        // prevent NPE
        if(expectedActions == null && actualActions != null || expectedActions != null && actualActions == null) {
            return false;
        }

        // check same size
        if (actualActions.size() != expectedActions.size()) {
            return false;
        }

        // check all RamlActions and apply the Matcher returned by the matcherFactory
        return actualActions.keySet().stream().allMatch(key -> {
            RamlAction actualItem = actualActions.get(key);
            RamlAction expectedItem = expectedActions.get(key);
            return matcherFactory.apply(actualItem).matches(expectedItem);
        });
    }

    @Override
    public void describeTo(Description description) {
    }
}