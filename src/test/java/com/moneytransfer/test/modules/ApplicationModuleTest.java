package com.moneytransfer.test.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.moneytransfer.ApplicationModule;
import com.moneytransfer.test.spark.SparkServerRule;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationModuleTest implements Module {
    @Override
    public void configure(Binder binder) {
        this.loadProperties(binder);
    }

    private void loadProperties(Binder binder) {
        InputStream stream = ApplicationModuleTest.class.getResourceAsStream("/jdbc.properties");
        Properties appProperties = new Properties();

        try {
            appProperties.load(stream);
            Names.bindProperties(binder, appProperties);
        } catch (IOException e) {
            binder.addError(e);
        }
    }
}
