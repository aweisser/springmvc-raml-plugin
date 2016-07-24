package com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2;

import com.phoenixnap.oss.ramlapisync.data.RamlFormParameter;
import com.phoenixnap.oss.ramlapisync.raml.InvalidRamlResourceException;
import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlDocumentationItem;
import com.phoenixnap.oss.ramlapisync.raml.RamlHeader;
import com.phoenixnap.oss.ramlapisync.raml.RamlMimeType;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelEmitter;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.RamlParamType;
import com.phoenixnap.oss.ramlapisync.raml.RamlQueryParameter;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlResponse;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import com.phoenixnap.oss.ramlapisync.raml.RamlSecurityReference;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;
import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.resources.Resource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author armin.weisser
 */
public class RJP08V2RamlModelFactory implements RamlModelFactory {
    @Override
    public RamlModelEmitter createRamlModelEmitter() {
        return new RJP08V2RamlModelEmitter();
    }

    @Override
    public RamlRoot createRamlRoot(String ramlFileUrl) throws InvalidRamlResourceException {
        RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(ramlFileUrl);
        if (ramlModelResult.hasErrors()) {
            List<String> errors = ramlModelResult.getValidationResults()
                    .stream()
                    .map(validationResult -> validationResult.getMessage())
                    .collect(Collectors.toList());
            throw new InvalidRamlResourceException(ramlFileUrl, errors);
        }

        // The Api is created by RamlModelBuilder during runtime via a yagi ModelProxyBuilder.
        // In org.raml.v2 there is no direct implementation for Api interface during compile time.
        Api api = ramlModelResult.getApiV08();

        return new RJP08V2RamlRoot(api);
    }

    @Override
    public RamlRoot createRamlRoot() {
        return null;
    }

    @Override
    public RamlResource createRamlResource() {
        return null;
    }

    @Override
    public RamlResource createRamlResource(Object resource) {
        if (resource == null) {
            return null;
        }
        return new RJP08V2RamlResource((Resource)resource);
    }

    @Override
    public RamlAction createRamlAction(Object action) {
        return null;
    }

    @Override
    public RamlAction createRamlAction() {
        return null;
    }

    @Override
    public RamlDocumentationItem createRamlDocumentationItem() {
        return null;
    }

    @Override
    public RamlDocumentationItem createRamlDocumentationItem(Object documentationItem) {
        return null;
    }

    @Override
    public RamlActionType createRamlActionType(Object type) {
        return null;
    }

    @Override
    public RamlResponse createRamlResponse() {
        return null;
    }

    @Override
    public RamlResponse createRamlResponse(Object response) {
        return null;
    }

    @Override
    public RamlMimeType createRamlMimeType() {
        return null;
    }

    @Override
    public RamlMimeType createRamlMimeType(Object mimeType) {
        return null;
    }

    @Override
    public RamlMimeType createRamlMimeTypeWithMime(String mime) {
        return null;
    }

    @Override
    public RamlHeader createRamlHeader(Object haeder) {
        return null;
    }

    @Override
    public RamlUriParameter createRamlUriParameter(Object o) {
        return null;
    }

    @Override
    public RamlUriParameter createRamlUriParameterWithName(String name) {
        return null;
    }

    @Override
    public RamlQueryParameter createRamlQueryParameter() {
        return null;
    }

    @Override
    public RamlQueryParameter createRamlQueryParameter(Object queryParameter) {
        return null;
    }

    @Override
    public RamlFormParameter createRamlFormParameter() {
        return null;
    }

    @Override
    public RamlFormParameter createRamlFormParameter(Object formParameter) {
        return null;
    }

    @Override
    public List<RamlFormParameter> createRamlFormParameters(List<? extends Object> formParameters) {
        return null;
    }

    @Override
    public List<RamlSecurityReference> createRamlSecurityReferences(List<? extends Object> securityReferences) {
        return null;
    }

    @Override
    public RamlSecurityReference createRamlSecurityReference(Object securityReference) {
        return null;
    }

    @Override
    public RamlParamType createRamlParamType(Object paramType) {
        return null;
    }
}
