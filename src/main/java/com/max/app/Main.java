package com.max.app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public final class Main {

    public static void main(String[] args) throws IOException {

        Date oldDate = new Date();
        System.out.println(oldDate);

        LocalDateTime date = LocalDateTime.now();
        System.out.println(date.getHour());

        System.out.printf("java version: %s%n", System.getProperty("java.version"));
    }


}
