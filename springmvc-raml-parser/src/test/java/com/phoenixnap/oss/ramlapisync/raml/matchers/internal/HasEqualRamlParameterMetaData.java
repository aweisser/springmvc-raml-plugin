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

        assertThat(actual.getDescription(), equalTo(expected.getDescription()));
        assertThat(actual.getType(), equalTo(expected.getType()));
        assertThat(actual.getExample(), equalTo(expected.getExample()));

        // According to raml spec the  "If displayName is not specified, it defaults to the property's key (the name of the property itself)".
        // TODO Due to a bug in raml-java-parser v1 the QueryParameter.getDisplayName() returns null if no displayName attribute is set in the .raml file.
        // The raml-java-parse v2 however does not have this bug. So this cant' be asserted here...
        // assertThat(actual.getDisplayName(), equalTo(expected.getDisplayName()));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
