package com.itppm.constants;

public final class FrameworkContants {

    private FrameworkContants() {
    }

    private static final String CHROMEDRIVERPATH = System.getProperty("user.dir") + "/src/main/java/resources/driver/chromedriver/chromedriver";
    private static final String PROPERTIESFILE = System.getProperty("user.dir") + "/src/main/java/resources/data.properties";
    private static final String REPORTFILE = System.getProperty("user.dir") + "/reports/report.html";


    public static String getChromedriverpath() {
        return CHROMEDRIVERPATH;
    }

    public static String getPropertiesfile() {
        return PROPERTIESFILE;
    }

    public static String getReportFile() {
        return REPORTFILE;
    }
}
