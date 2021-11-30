package com.itppm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.itppm.constants.FrameworkContants;

public class ExtentReporterNG {
    public static ExtentReports extent;

    public static ExtentReports generateExtentReport(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(FrameworkContants.getReportFile());
        reporter.config().setReportName("Web Automation");
        reporter.config().setDocumentTitle("Execution Results");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Company", "Freshworks");
        extent.setSystemInfo("Team", "ITPPM-QA");
        extent.setSystemInfo("Tester", "Naveen N");
        return extent;
    }


}
