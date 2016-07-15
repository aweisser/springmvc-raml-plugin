package com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v2;

import com.phoenixnap.oss.ramlapisync.raml.InvalidRamlResourceException;
import com.phoenixnap.oss.ramlapisync.raml.RamlAction;
import com.phoenixnap.oss.ramlapisync.raml.RamlActionType;
import com.phoenixnap.oss.ramlapisync.raml.RamlDocumentationItem;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelEmitter;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.RamlResource;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.resources.Resource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author armin.weisser
 */
public class Jrp08V2RamlModelFactory implements RamlModelFactory {
    @Override
    public RamlModelEmitter createRamlModelEmitter() {
        return new Jrp08V2RamlModelEmitter();
    }

    @Override
    public RamlRoot buildRamlRoot(String ramlFileUrl) throws InvalidRamlResourceException {
        RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(ramlFileUrl);
        if (ramlModelResult.hasErrors()) {
            List<String> errors = ramlModelResult.getValidationResults()
                    .stream()
                    .map(validationResult -> validationResult.getMessage())
                    .collect(Collectors.toList());
            throw new InvalidRamlResourceException(ramlFileUrl, errors);
        }
        Api api = ramlModelResult.getApiV08();
        return new Jrp08V2RamlRoot(api);
    }

    @Override
    public RamlRoot createRamlRoot() {
        return null;
    }

    @Override
    public RamlRoot createRamlRoot(String ramlFileUrl) {
        return null;
    }

    @Override
    public RamlResource createRamlResource() {
        return null;
    }

    @Override
    public RamlResource createRamlResource(Object resource) {
        return new Jrp08V2RamlResource((Resource)resource);
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
    public RamlDocumentationItem createDocumentationItem() {
        return null;
    }

    @Override
    public RamlDocumentationItem createDocumentationItem(Object documentationItem) {
        return null;
    }

    @Override
    public RamlActionType createActionType(Object type) {
        return null;
    }
}
