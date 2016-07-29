package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Map;
import java.util.function.Function;

/**
 * Iterate recursively through a set of RamlResource objects and apply a Matcher<RamlResource> instance returned by the given Function ...
 *
 * @author armin.weisser
 */
public class RecursiveMultiResourcesMatcher extends BaseMatcher<Map<String, RamlResource>> {


    private final Map<String, RamlResource> actual;
    private final Function<RamlResource, Matcher<RamlResource>> matcherFactory;

    /**
     *
     * @param actual a Map of RamlResource items to check
     * @param matcherFactory a function returning a Matcher<RamlResource> instance
     */
    public RecursiveMultiResourcesMatcher(Map<String, RamlResource> actual, Function<RamlResource, Matcher<RamlResource>> matcherFactory) {
        this.actual = actual;
        this.matcherFactory = matcherFactory;
    }

    @Override
    public boolean matches(Object item) {
        Map<String, RamlResource> expected = (Map<String, RamlResource>) item;

        // check identy
        if (actual == expected) {
            return true;
        }

        // prevent NPE
        if(actual == null && expected != null || actual != null && expected == null) {
            return false;
        }

        // check same size
        if (actual.size() != expected.size()) {
            return false;
        }

        // check all RamlResources (recursivaly) and apply the Matcher returned by the matcherFactory
        return actual.keySet().stream().allMatch(key -> {
            RamlResource actualItem = actual.get(key);
            RamlResource expectedItem = expected.get(key);
            boolean currentLevelMatches = matcherFactory.apply(actualItem).matches(expectedItem);
            if(!currentLevelMatches) {
                return false;
            }
            boolean subsequentLevelsMatches = new RecursiveMultiResourcesMatcher(actualItem.getResources(), matcherFactory).matches(expectedItem.getResources());
            return currentLevelMatches && subsequentLevelsMatches;
        });
    }

    @Override
    public void describeTo(Description description) {
    }
}