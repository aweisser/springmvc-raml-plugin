package com.phoenixnap.oss.ramlapisync.raml;


import com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v1.Jrp08V1RamlModelFactory;

/**
 * @author armin.weisser
 */
public abstract class RamlModelFactoryOfFactories {

    private static RamlModelFactory ramlModelFactory = new Jrp08V1RamlModelFactory();

    public static RamlModelFactory createRamlModelFactory() {
        // Currently we only have java-raml-parser v1 for raml 0.8
        return RamlModelFactoryOfFactories.ramlModelFactory;
    }

    public static void setRamlModelFactory(RamlModelFactory ramlModelFactory) {
        RamlModelFactoryOfFactories.ramlModelFactory = ramlModelFactory;
    }
}
