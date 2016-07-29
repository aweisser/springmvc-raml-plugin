package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlAbstractParam;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 */
public class HasEqualRamlParameterMetaData extends BaseMatcher<RamlAbstractParam> {

    private final RamlAbstractParam actual;

    public HasEqualRamlParameterMetaData(RamlAbstractParam actual) {
        this.actual = actual;
    }

    @Override
    public boolean matches(Object item) {
        RamlAbstractParam expected = (RamlAbstractParam) item;

        assertThat(actual.getClass(), equalTo(expected.getClass()));
        assertThat(actual.getDescription(), equalTo(expected.getDescription()));
        assertThat(actual.getType(), equalTo(expected.getType()));
        assertThat(actual.getDisplayName(), equalTo(expected.getDisplayName()));
        assertThat(actual.getExample(), equalTo(expected.getExample()));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
