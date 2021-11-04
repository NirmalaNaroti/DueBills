package com.fragma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "conf")
public class ConfigurationHelper {

    private static String query;
    private static  String unconfirmedQuery;
    private static String excelLocation;

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String query) {
        ConfigurationHelper.query = query;
    }

    public static String getUnconfirmedQuery() {
        return unconfirmedQuery;
    }

    public static void setUnconfirmedQuery(String unconfirmedQuery) {
        ConfigurationHelper.unconfirmedQuery = unconfirmedQuery;
    }

    public static String getExcelLocation() {
        return excelLocation;
    }

    public static void setExcelLocation(String excelLocation) {
        ConfigurationHelper.excelLocation = excelLocation;
    }
}
