package com.phoenixnap.oss.ramlapisync.raml.matchers.internal;

import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 */
public class HasEqualResourceMetaData extends BaseMatcher<RamlResource> {

    private final RamlResource actualResource;

    public HasEqualResourceMetaData(RamlResource actualResource) {
        this.actualResource = actualResource;
    }

    @Override
    public boolean matches(Object item) {
        RamlResource expectedResource = (RamlResource) item;

        if(actualResource == expectedResource) {
            return true;
        }

        assertThat(actualResource.getUri(), is(equalTo(expectedResource.getUri())));
        assertThat(actualResource.getRelativeUri(), is(equalTo(expectedResource.getRelativeUri())));
        assertThat(actualResource.getParentUri(), is(equalTo(expectedResource.getParentUri())));
        assertThat(actualResource.getDescription(), is(equalTo(expectedResource.getDescription())));

        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
