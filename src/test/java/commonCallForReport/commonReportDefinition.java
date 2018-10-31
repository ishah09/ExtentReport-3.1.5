package commonCallForReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class commonReportDefinition {

	public ExtentHtmlReporter htmlreport;
	public static ExtentReports reports;
	// This is the object of extentTest class, by which log is generate.
	public static ExtentTest testlog;

	public void definition(String reportName) {
		// Report will generate in Project Directory only.
		// After execution, refresh project directory.
		htmlreport = new ExtentHtmlReporter(".\\Report\\" + reportName + ".html");
		reports = new ExtentReports();
		reports.attachReporter(htmlreport);

		// Customize Report property
		htmlreport.config().setReportName("Test Report");
		reports.setSystemInfo("Host Name", "Test Host");
		reports.setSystemInfo("Environment", "Automation Testing");
		reports.setSystemInfo("User Name", "QA Automation");
		htmlreport.config().setDocumentTitle("Automation Report");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);

		// Two default theme of report
		htmlreport.config().setTheme(Theme.STANDARD);
		// htmlreport.config().setTheme(Theme.DARK);
	}

	public void flushReport() {
		// If flush method did not call, Report will not generate.
		reports.flush();
	}
}
