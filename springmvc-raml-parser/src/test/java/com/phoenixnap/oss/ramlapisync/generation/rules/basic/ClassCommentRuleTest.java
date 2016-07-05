package com.phoenixnap.oss.ramlapisync.generation.rules.basic;

import com.phoenixnap.oss.ramlapisync.generation.rules.AbstractSingleControllerRuleTestBase;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 * @since 0.4.1
 */
public class ClassCommentRuleTest extends AbstractSingleControllerRuleTestBase {

    private ClassCommentRule rule = new ClassCommentRule();

    @Test
    public void applyRule_shouldCreate_validClassComment() throws JClassAlreadyExistsException {

        JDefinedClass jClass = jCodeModel.rootPackage()._class("BaseController");
        JDocComment jDocComment = rule.apply(getControllerMetadata(), jClass);
        assertNotNull(jDocComment);
        assertThat(serializeModel(), containsString("* The BaseController class"));
    }

}
