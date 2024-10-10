package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports report;
    public static ExtentReports configExtentReport(String browser){
        if(report==null){
            ExtentSparkReporter reporter=new ExtentSparkReporter("src/test/reports/test report_"+browser+" browser.html");
            reporter.config().setDocumentTitle("Test Report");
            reporter.config().setReportName("Woodmart-kids Test Report on "+browser+" browser");
            report=new ExtentReports();
            report.attachReporter(reporter);
            report.setSystemInfo("Tester","Mahmoud Yehia");
            report.setSystemInfo("Browser",browser);
            report.setSystemInfo("Website Link","https://test-iti-testing-project-v1.pantheonsite.io/");
        }
        return report;
    }
}

//package utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//public class ExtentReport {
//    private static ExtentReports extent;
//
//    public synchronized static ExtentReports configExtentReport(String browser){
//        if (extent == null) {
//            ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/reports/test_report_" + browser + "_browser.html");
//            reporter.config().setDocumentTitle("Test Report");
//            reporter.config().setReportName("Woodmart-kids Test Report on " + browser + " browser");
//            extent = new ExtentReports();
//            extent.attachReporter(reporter);
//            extent.setSystemInfo("Tester", "Mahmoud Yehia");
//            extent.setSystemInfo("Browser", browser);
//            extent.setSystemInfo("Website Link", "https://test-iti-testing-project-v1.pantheonsite.io/");
//        }
//        return extent;
//    }
//}

