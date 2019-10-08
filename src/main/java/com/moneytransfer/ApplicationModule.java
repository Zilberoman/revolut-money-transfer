package com.moneytransfer;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationModule implements Module {

    @Override
    public void configure(Binder binder) {
        this.loadProperties(binder);
    }

    private void loadProperties(Binder binder) {
        InputStream stream = ApplicationModule.class.getResourceAsStream("/jdbc.properties");
        Properties appProperties = new Properties();

        try {
            appProperties.load(stream);
            Names.bindProperties(binder, appProperties);
        } catch (IOException e) {
            binder.addError(e);
        }
    }
}
