package com.phoenixnap.oss.ramlapisync.raml;

/**
 * @author armin.weisser
 */
public class UnknownParamTypeException extends RuntimeException {
    public UnknownParamTypeException(String type) {
        super(type);
    }
}
