package com.phoenixnap.oss.ramlapisync.raml;

import org.raml.model.ActionType;

/**
 * @author armin.weisser
 */
public enum RamlActionType {
        GET, POST, PUT, DELETE, HEAD, PATCH, OPTIONS, TRACE;

    // TODO Remove this method and refactor
    public static RamlActionType asRamlActionType(ActionType type) {
        return valueOf(type.name());
    }

    // TODO move this into jrp08 package
    public static ActionType asActionType(RamlActionType type) {
        return ActionType.valueOf(type.name());
    }
}
