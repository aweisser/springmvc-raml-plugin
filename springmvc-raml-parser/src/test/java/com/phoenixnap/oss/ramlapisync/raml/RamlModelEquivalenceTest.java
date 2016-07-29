package com.phoenixnap.oss.ramlapisync.raml;

import com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v1.RJP08V1RamlModelFactory;
import com.phoenixnap.oss.ramlapisync.raml.rjp.raml08v2.RJP08V2RamlModelFactory;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.resources.Resource;

import static com.phoenixnap.oss.ramlapisync.raml.matchers.RamlModelMatchers.hasSameActionsMetaDataAs;
import static com.phoenixnap.oss.ramlapisync.raml.matchers.RamlModelMatchers.hasSameMetaDataAs;
import static com.phoenixnap.oss.ramlapisync.raml.matchers.RamlModelMatchers.hasSameSchemasAs;
import static com.phoenixnap.oss.ramlapisync.raml.matchers.RamlModelMatchers.hasSameUriParametersAs;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author armin.weisser
 */
public class RamlModelEquivalenceTest {

    public static final String RAML_FILE = "test-model-factory.raml";

    private static RamlRoot ramlRoot08V1, ramlRoot08V2;

    @BeforeClass
    public static void initRamlRoots() throws InvalidRamlResourceException {
        RJP08V1RamlModelFactory ramlModelFactory08V1 = new RJP08V1RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V1);
        ramlRoot08V1 = ramlModelFactory08V1.createRamlRoot(RAML_FILE);

        RJP08V2RamlModelFactory ramlModelFactory08V2 = new RJP08V2RamlModelFactory();
        RamlModelFactoryOfFactories.setRamlModelFactory(ramlModelFactory08V2);
        ramlRoot08V2 = ramlModelFactory08V2.createRamlRoot(RAML_FILE);
    }

    @Test
    public void modelsShouldHaveSameMetaData() {
        assertThat(ramlRoot08V1, hasSameMetaDataAs(ramlRoot08V2));
    }

    @Test
    public void modelsShouldHaveSameSchemas()  {
        assertThat(ramlRoot08V1, hasSameSchemasAs(ramlRoot08V2));
    }

    @Test
    public void modelsShouldHaveSameResourcesMetaData()  {
        assertThat(ramlRoot08V1.getResources(), hasSameMetaDataAs(ramlRoot08V2.getResources()));
    }

    @Test
    public void modelsShouldHaveSameActionsMetaData() {
        assertThat(ramlRoot08V1.getResources(), hasSameActionsMetaDataAs(ramlRoot08V2.getResources()));
    }

    @Test
    public void modelsShouldReturnTopLevelResourcesByRelativePath()  {
        RamlResource expected = ramlRoot08V1.getResource("/base");
        RamlResource actual = ramlRoot08V2.getResource("/base");
        assertThat(expected, hasSameMetaDataAs(actual));
    }

    @Test
    public void modelsShouldReturnSecondLevelResourcesByRelativePath()  {
        RamlResource expected = ramlRoot08V1.getResource("/base").getResource("/endpointWithGet");
        RamlResource actual = ramlRoot08V2.getResource("/base").getResource("/endpointWithGet");
        assertThat(expected, hasSameMetaDataAs(actual));
    }

    @Test
    public void modelsShouldReturnActionsByName() {
        RamlAction expected = ramlRoot08V1.getResource("/base").getResource("/endpointWithGet").getAction(RamlActionType.GET);
        RamlAction actual = ramlRoot08V2.getResource("/base").getResource("/endpointWithGet").getAction(RamlActionType.GET);
        assertThat(expected, hasSameMetaDataAs(actual));
    }

    @Test
    public void modelsShouldReturnResourcesByPath() {
        RamlResource expected = ramlRoot08V1.getResource("/base/endpointWithGet");
        RamlResource actual = ramlRoot08V2.getResource("/base/endpointWithGet");
        assertThat(expected, hasSameMetaDataAs(actual));
    }

    @Test
    public void resourcesShouldHandleEndpointWithAUriParam() {
        RamlResource expected = ramlRoot08V1.getResource("/secondBase/endpointWithURIParam/{uriParam}");
        RamlResource actual = ramlRoot08V2.getResource("/secondBase/endpointWithURIParam/{uriParam}");
        assertThat(expected, hasSameUriParametersAs(actual));
    }

    @Test
    public void resourcesShouldHandleEndpointWithMultipleUriParams() {
        RamlResource expected = ramlRoot08V1.getResource("/secondBase/endpointWithURIParam/{uriParam}/list/{anotherUriParam}");
        RamlResource actual = ramlRoot08V2.getResource("/secondBase/endpointWithURIParam/{uriParam}/list/{anotherUriParam}");
        assertThat(expected, hasSameUriParametersAs(actual));
    }

    @Test
    // https://github.com/raml-org/raml-java-parser/issues/201
    public void uriParamsShouldNotBeNull() {
        String raml =
                "#%RAML 0.8\n" +
                "title: myapi\n" +
                "baseUri: /\n" +
                "version: 1\n" +
                "\n" +
                "/endpointWithURIParam:\n" +
                "   /{uriParam}:\n" +
                "       get:";

        RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(raml, ".");
        Api api = ramlModelResult.getApiV08();

        Resource endpointWithURIParam = api.resources().get(0);
        assertThat(endpointWithURIParam.displayName(), Matchers.equalTo("/endpointWithURIParam"));

        Resource uriParam = endpointWithURIParam.resources().get(0);
        assertThat(uriParam.displayName(), Matchers.equalTo("/{uriParam}"));

        assertThat(uriParam.uriParameters(), Matchers.hasSize(1)); // ??? There should be a uriParameter in this resource...

    }


}