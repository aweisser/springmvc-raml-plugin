package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 */
public class HasEqualActionMetaData extends BaseMatcher<RamlAction> {

    private final RamlAction actual;

    public HasEqualActionMetaData(RamlAction actual) {
        this.actual = actual;
    }

    @Override
    public boolean matches(Object item) {
        RamlAction expected = (RamlAction) item;

        assertThat(actual.getDescription(), is(equalTo(expected.getDescription())));
        assertThat(actual.getType(), is(equalTo(expected.getType())));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
