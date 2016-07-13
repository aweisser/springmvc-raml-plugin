package com.phoenixnap.oss.ramlapisync.raml;

import org.raml.model.Action;
import org.raml.model.Resource;

import java.util.Map;

/**
 * @author armin.weisser
 */
public interface RamlModelFactory {

    RamlAction createRamlAction();

    RamlAction createRamlAction(Object baseAction);

    // TODO this should be obsolete and not part of the generic interface after all org.raml.model classes have an abstraction ....
    Action createAction(RamlAction action);

    RamlResource createRamlResource(Object resource);

    RamlResource createRamlResource();

    // TODO this should be obsolete and not part of the generic interface after all org.raml.model classes have an abstraction ....
    Resource createResource(RamlResource resource);

    Map<String, RamlResource> createRamlResources(Map<String, ? extends Object> resources);

    Map<RamlActionType,RamlAction> createRamlActions(Map<? extends Object, ? extends Object> actions);
}
