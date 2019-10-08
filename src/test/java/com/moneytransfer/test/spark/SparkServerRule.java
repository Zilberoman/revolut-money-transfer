package com.moneytransfer.test.spark;

import com.google.inject.Inject;
import org.junit.rules.ExternalResource;
import spark.Service;

import java.util.logging.Logger;

public class SparkServerRule extends ExternalResource {
    private final Logger logger;

    private ServiceInitializer serviceInitializer;
    private Service service;

    public SparkServerRule(Logger logger, ServiceInitializer serviceInitializer) {
        this.serviceInitializer = serviceInitializer;
        this.logger = logger;
    }

    @Override
    protected void before() {
        logger.info("Start spark server");
        service = Service.ignite();
        serviceInitializer.init(service);
        logger.info("Await initialization of Spark...");
        service.awaitInitialization();
        logger.info("Spark is ignited!");
    }

    @Override
    protected void after() {
        logger.info("Stopping Spark...");
//        service.stop();
//        logger.info("Spark stopped");
    }
}
