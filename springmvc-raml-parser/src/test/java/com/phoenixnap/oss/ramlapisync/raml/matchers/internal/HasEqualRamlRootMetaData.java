package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 */
public class HasEqualRamlRootMetaData extends BaseMatcher<RamlRoot> {

    private final RamlRoot actual;

    public HasEqualRamlRootMetaData(RamlRoot actual) {
        this.actual = actual;
    }

    @Override
    public boolean matches(Object item) {
        RamlRoot expected = (RamlRoot) item;

        assertThat(actual.getBaseUri(), is(equalTo(expected.getBaseUri())));
        assertThat(actual.getMediaType(), is(equalTo(expected.getMediaType())));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
