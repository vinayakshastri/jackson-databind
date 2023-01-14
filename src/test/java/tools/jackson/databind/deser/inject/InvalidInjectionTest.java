package tools.jackson.databind.deser.inject;

import com.fasterxml.jackson.annotation.JacksonInject;

import tools.jackson.databind.BaseMapTest;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.InvalidDefinitionException;

public class InvalidInjectionTest extends BaseMapTest
{
    static class BadBean1 {
        @JacksonInject protected String prop1;
        @JacksonInject protected String prop2;
    }

    static class BadBean2 {
        @JacksonInject("x") protected String prop1;
        @JacksonInject("x") protected String prop2;
    }

    /*
    /**********************************************************
    /* Unit tests
    /**********************************************************
     */

    private final ObjectMapper MAPPER = newJsonMapper();

    public void testInvalidDup() throws Exception
    {
        try {
            MAPPER.readValue("{}", BadBean1.class);
            fail("Should not pass");
        } catch (InvalidDefinitionException e) {
            verifyException(e, "Duplicate injectable value");
        }
        try {
            MAPPER.readValue("{}", BadBean2.class);
            fail("Should not pass");
        } catch (InvalidDefinitionException e) {
            verifyException(e, "Duplicate injectable value");
        }
    }

}