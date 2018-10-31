package extentReportDemos;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class D_extentLogswithChildTree {

	public static void main(String[] args) {

		System.out.println("Start");

		// Report will generate in Project Directory only.
		// After execution, refresh project directory.
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(".\\Report\\extentLogs with Child Tree.html");
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(htmlreport);

		// This is the object of extentTest class, by which log is generate.
		ExtentTest testlog;
		ExtentTest childLog;

		// Customize Report property
		htmlreport.config().setReportName("Test Report");
		reports.setSystemInfo("Host Name", "Test Host");
		reports.setSystemInfo("Environment", "Automation Testing");
		reports.setSystemInfo("User Name", "QA Automation");
		htmlreport.config().setDocumentTitle("Automation Report");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);

		// Two default theme of report
		// htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setTheme(Theme.DARK);

		testlog = reports.createTest("Test Log Method-1");
		testlog.info("This is Info log");
		testlog.pass("This is Pass log");
		testlog.fail("This is Fail log");
		testlog.error("This is Error log");

		testlog = reports.createTest("Test Log Method-2 with Child nodes");
		testlog.log(Status.INFO, "This is Info log");
		testlog.log(Status.PASS, "This is Pass log");
		testlog.log(Status.FAIL, "This is Fail log");
		testlog.log(Status.FATAL, "This is Fatal log");

		childLog = testlog.createNode("Child Test Section-1");
		childLog.info("This is Info log");
		childLog.pass("This is Pass log");
		childLog.fail("This is Fail log");

		childLog = childLog.createNode("Child Test Section-2");
		childLog.info("This is Info log");
		childLog.pass("This is Pass log");
		childLog.fail("This is Fail log");

		testlog = reports.createTest("Test log with Extent Color");
		testlog.log(Status.INFO, MarkupHelper.createLabel("This is Info log", ExtentColor.ORANGE));
		testlog.log(Status.PASS, MarkupHelper.createLabel("This is pass log", ExtentColor.CYAN));

		// Child test object to integrate with Parent test
		// It will append with Last test section
		childLog = testlog.createNode("Child Test Section-1");
		childLog.info("This is Info log");
		childLog.pass("This is Pass log");

		childLog = testlog.createNode("Child Test Section-2");
		childLog.info("This is Info log");
		childLog.pass("This is Pass log");

		// If flush method did not call, Report will not generate.
		reports.flush();
		System.out.println("End");
	}
}
