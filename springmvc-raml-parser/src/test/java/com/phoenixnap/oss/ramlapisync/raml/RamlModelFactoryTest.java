package com.phoenixnap.oss.ramlapisync.raml;

import com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v1.Jrp08V1RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v2.Jrp08V2RamlModelFactory;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author armin.weisser
 */
public class RamlModelFactoryTest {

    @Test
    public void raml08V1_and_raml08V2_shouldHaveEquivalentRamlRoot() throws InvalidRamlResourceException {

        Jrp08V1RamlModelFactory ramlModelFactory08V1 = new Jrp08V1RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V1);
        RamlRoot ramlRoot08V1 = ramlModelFactory08V1.buildRamlRoot("test-simple.raml");

        Jrp08V2RamlModelFactory ramlModelFactory08V2 = new Jrp08V2RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V2);
        RamlRoot ramlRoot08V2 = ramlModelFactory08V2.buildRamlRoot("test-simple.raml");

        assertThat(ramlRoot08V1.getBaseUri(), is(equalTo(ramlRoot08V2.getBaseUri())));
        assertThat(ramlRoot08V1.getMediaType(), is(equalTo(ramlRoot08V2.getMediaType())));
        assertThat(ramlRoot08V1.getSchemas(), is(equalTo(ramlRoot08V2.getSchemas())));

        Map<String, RamlResource> ramlRoot08V1Resources = ramlRoot08V1.getResources();
        Map<String, RamlResource> ramlRoot08V2Resources = ramlRoot08V2.getResources();

        assertThat(ramlRoot08V1Resources.size(), is(equalTo(ramlRoot08V2Resources.size())));



    }

    @Test
    public void raml08V1_and_raml08V2_shouldHaveEquivalentStringRepresentation() throws InvalidRamlResourceException {

        Jrp08V1RamlModelFactory ramlModelFactory08V1 = new Jrp08V1RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V1);
        RamlRoot ramlRoot08V1 = ramlModelFactory08V1.buildRamlRoot("test-simple.raml");
        RamlModelEmitter ramlModelEmitter08V1 = ramlModelFactory08V1.createRamlModelEmitter();
        String raml08V1 = ramlModelEmitter08V1.dump(ramlRoot08V1);

        Jrp08V2RamlModelFactory ramlModelFactory08V2 = new Jrp08V2RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V2);
        RamlRoot ramlRoot08V2 = ramlModelFactory08V2.buildRamlRoot("test-simple.raml");
        RamlModelEmitter ramlModelEmitter08V2 = ramlModelFactory08V2.createRamlModelEmitter();
        String raml08V2 = ramlModelEmitter08V2.dump(ramlRoot08V2);

        assertEquals(raml08V1, raml08V2);
    }



}
