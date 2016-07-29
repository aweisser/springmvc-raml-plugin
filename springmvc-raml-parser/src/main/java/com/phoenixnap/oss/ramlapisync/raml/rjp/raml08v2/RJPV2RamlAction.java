package com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlHeader;
import com.phoenixnap.oss.ramlapisync.raml.RamlMimeType;
import com.phoenixnap.oss.ramlapisync.raml.RamlQueryParameter;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlResponse;
import com.phoenixnap.oss.ramlapisync.raml.RamlSecurityReference;
import org.raml.v2.api.model.v08.methods.Method;

import java.util.List;
import java.util.Map;

/**
 * @author armin.weisser
 */
public class RJPV2RamlAction implements RamlAction {

    private static RJP08V2RamlModelFactory ramlModelFactory = new RJP08V2RamlModelFactory();

    private final Method method;

    public RJPV2RamlAction(Method method) {
        this.method = method;
    }

    @Override
    public RamlActionType getType() {
        return ramlModelFactory.createRamlActionType(method);
    }

    @Override
    public Map<String, RamlQueryParameter> getQueryParameters() {
        return null;
    }

    @Override
    public Map<String, RamlResponse> getResponses() {
        return null;
    }

    @Override
    public RamlResource getResource() {
        return null;
    }

    @Override
    public Map<String, RamlHeader> getHeaders() {
        return null;
    }

    @Override
    public Map<String, RamlMimeType> getBody() {
        return null;
    }

    @Override
    public boolean hasBody() {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setBody(Map<String, RamlMimeType> body) {

    }

    @Override
    public void setResource(RamlResource resource) {

    }

    @Override
    public void setType(RamlActionType actionType) {

    }

    @Override
    public List<RamlSecurityReference> getSecuredBy() {
        return null;
    }

    @Override
    public void addResponse(String httpStatus, RamlResponse response) {

    }

    @Override
    public void addQueryParameters(Map<String, RamlQueryParameter> queryParameters) {

    }
}
