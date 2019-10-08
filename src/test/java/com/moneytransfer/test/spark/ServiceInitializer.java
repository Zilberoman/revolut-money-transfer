package com.moneytransfer.test.spark;

import spark.Service;

@FunctionalInterface
public interface ServiceInitializer {
    void init(Service service);
}
