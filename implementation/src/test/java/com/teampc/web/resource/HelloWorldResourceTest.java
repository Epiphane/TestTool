package com.teampc.web.resource;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Test;

import org.junit.ClassRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloWorldResourceTest {
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
        .addResource(new HelloWorldResource())
        .build();

    @Test
    public void testHelloWorld() {
        String response = resources.client()
                .target("/")
                .request()
                .get(String.class);
        assertEquals(response, "Hello, World!!!");
    }
}
