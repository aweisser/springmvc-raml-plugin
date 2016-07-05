package com.phoenixnap.oss.ramlapisync.generation.rules;

import org.junit.BeforeClass;

/**
 * @author armin.weisser
 * @since 0.4.1
 */
public abstract class AbstractSingleControllerRuleTestBase extends AbstractRuleTestBase {

    @BeforeClass
    public static void initRaml() {
        initRaml("test-single-controller.raml");
    }

}
