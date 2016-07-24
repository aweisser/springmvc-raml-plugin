package com.phoenixnap.oss.ramlapisync.raml;


import com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v1.RJP08V1RamlModelFactory;

/**
 * @author armin.weisser
 */
public abstract class RamlModelFactoryOfFactories {
    static RamlModelFactory CURRENT_FACTORY = new RJP08V1RamlModelFactory();

    public static RamlModelFactory createRamlModelFactory() {
        return CURRENT_FACTORY;
    }

    public static void setRamlModelFactory(RamlModelFactory ramlModelFactory) {
        CURRENT_FACTORY = ramlModelFactory;
    }
}
