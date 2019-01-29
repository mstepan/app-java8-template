package com.max.app.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public enum SecurityUtils {

    INST;

    public static final Marker SECURITY_SUCCESS = MarkerFactory.getMarker("SECURITY_SUCCESS");
    public static final Marker SECURITY_FAILURE = MarkerFactory.getMarker("SECURITY_FAILURE");
    public static final Marker SECURITY_AUDIT = MarkerFactory.getMarker("SECURITY_AUDIT");

}
