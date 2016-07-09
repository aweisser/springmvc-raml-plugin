package com.phoenixnap.oss.ramlapisync.raml;

import org.raml.model.Action;

/**
 * @author armin.weisser
 */
public interface RamlModelFactory {

    RamlAction createRamlAction();

    RamlAction createRamlAction(Object baseAction);

    // TODO this should be obsolete and not part of the generic interface after all org.raml.model classes have an abstraction ....
    Action createAction(RamlAction action);
}
