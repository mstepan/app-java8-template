package com.max.app;


import io.reactivex.functions.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;


final class Main {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final class SimpleConsumer implements Consumer<String> {
        @Override
        public void accept(String value) throws Exception {
            LOG.info(value);
        }
    }

    private Main() throws Exception {


        LOG.info("java version: {}", System.getProperty("java.version"));
    }


    public static void main(String[] args) {
        try {
            new Main();
        }
        catch (Exception ex) {
            LOG.error("Error occurred", ex);
        }
    }
}
