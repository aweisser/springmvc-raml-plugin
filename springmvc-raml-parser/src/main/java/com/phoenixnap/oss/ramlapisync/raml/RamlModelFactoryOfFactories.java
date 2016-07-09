package com.phoenixnap.oss.ramlapisync.raml;

import com.phoenixnap.oss.ramlapisync.raml.jrp08.Jrp08RamlModelFactory;

/**
 * @author armin.weisser
 */
public interface RamlModelFactoryOfFactories {
    static RamlModelFactory createRamlModelFactory() {
        // Currently we only have java-raml-parser v1 for raml 0.8
        return new Jrp08RamlModelFactory();
    }
}
