package com.teampc.web.server;


import com.teampc.web.resource.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;

public class TestToolApp extends Application<TestToolConf> {
    public static void main(String... args) throws Exception {
        new TestToolApp().run(args);
    }

    @Override
    public String getName() {
        return "TestTool";
    }

    @Override
    public void initialize(Bootstrap<TestToolConf> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/html/", "/", "index.html"));
    }

    @Override
    public void run(TestToolConf conf, Environment env) {
        env.jersey().setUrlPattern("/api/*");
        env.jersey().register(new HelloWorldResource());
    }

}
