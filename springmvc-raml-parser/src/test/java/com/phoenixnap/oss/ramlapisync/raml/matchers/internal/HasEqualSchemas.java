package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author armin.weisser
 */
public class HasEqualSchemas extends BaseMatcher<RamlRoot> {
    private final RamlRoot actual;

    public HasEqualSchemas(RamlRoot actual) {
        this.actual = actual;
    }


    @Override
    public boolean matches(Object item) {
        RamlRoot expected = (RamlRoot) item;
        List<Map<String, String>> expectedSchemas = expected.getSchemas();
        List<Map<String, String>> actualSchemas = actual.getSchemas();

        assertThat(expectedSchemas, hasSize(actualSchemas.size()));

        for(int i = 0; i< expectedSchemas.size(); i++) {
            Map<String, String> expectedSchema = expectedSchemas.get(i);
            Map<String, String> actualSchema = actualSchemas.get(i);
            assertThat(expectedSchema.size(), equalTo(actualSchema.size()));
            expectedSchema.keySet().forEach(key -> assertThat(expectedSchema.get(key), equalTo(actualSchema.get(key))));
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
