package com.phoenixnap.oss.ramlapisync.raml;

import java.util.List;
import java.util.Map;

/**
 * This is the root element of a .raml spec.
 * It holds the top-level meta information like version, mediaType or baseUri
 * as well as a reference to all top-level resources.
 *
 * @author armin.weisser
 */
public interface RamlRoot extends RamlResourceRoot {

    String getMediaType();

    List<Map<String, String>> getSchemas();

    void setBaseUri(String baseUri);

    void setVersion(String version);

    void setTitle(String title);

    void setDocumentation(List<RamlDocumentationItem> documentationItems);

    void setMediaType(String mediaType);

    String getBaseUri();
}
