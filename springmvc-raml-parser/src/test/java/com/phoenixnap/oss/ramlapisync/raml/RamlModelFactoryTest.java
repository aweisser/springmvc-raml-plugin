package com.phoenixnap.oss.ramlapisync.raml;

import com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v1.RJP08V1RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2.RJP08V2RamlModelFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.phoenixnap.oss.ramlapisync.raml.matchers.RamlModelMatchers.hasEquivalentMetaData;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author armin.weisser
 */
public class RamlModelFactoryTest {

    public static final String RAML_FILE = "test-simple.raml";

    private static RamlRoot ramlRoot08V1, ramlRoot08V2;

    @BeforeClass
    public static void initRamlRoots() throws InvalidRamlResourceException {
        RJP08V1RamlModelFactory ramlModelFactory08V1 = new RJP08V1RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V1);
        ramlRoot08V1 = ramlModelFactory08V1.createRamlRoot(RAML_FILE);

        RJP08V2RamlModelFactory ramlModelFactory08V2 = new RJP08V2RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V2);
        ramlRoot08V2 = ramlModelFactory08V2.createRamlRoot(RAML_FILE);
    }

    @Test
    public void modelsShouldHaveEquivalentMetaData() throws InvalidRamlResourceException {
        assertThat(ramlRoot08V1, hasEquivalentMetaData(ramlRoot08V2));
    }

    @Test
    public void modelsShouldHaveEquivalentSchemas() throws InvalidRamlResourceException {
        assertThat(ramlRoot08V1.getSchemas(), is(equalTo(ramlRoot08V2.getSchemas())));
    }


    @Test
    public void modelsShouldHaveEquivalentResources() throws InvalidRamlResourceException {
        assertThat(ramlRoot08V1.getResources(), hasEquivalentMetaData(ramlRoot08V2.getResources()));
    }
}