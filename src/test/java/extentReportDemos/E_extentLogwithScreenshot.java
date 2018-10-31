package extentReportDemos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class E_extentLogwithScreenshot {

	@Test
	public void logWithScreenshot() throws IOException {

		//Chrome Driver should be installed in your System
		WebDriver driver = new ChromeDriver();
		driver.get("http://google.com");

		System.out.println("Start");

		// Report will generate in Project Directory only.
		// After execution, refresh project directory.
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(".\\Report\\Extent Report with Screenshot.html");
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(htmlreport);

		// This is the object of extentTest class, by which log is generate.
		ExtentTest testlog;

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

		testlog = reports.createTest("Test Log Method-1");
		testlog.info("This is Info log");
		testlog.pass("This is Pass log");
		testlog.fail("This is Fail log");
		testlog.error("This is Error log");

		testlog = reports.createTest("Test Log Method-2");
		testlog.log(Status.INFO, "This is Info log");
		testlog.log(Status.PASS, "This is Pass log");
		testlog.log(Status.FAIL, "This is Fail log");
		testlog.log(Status.FATAL, "This is Fatal log");

		testlog = reports.createTest("Test log with Extent Color");
		testlog.log(Status.INFO, MarkupHelper.createLabel("This is Info log", ExtentColor.ORANGE));
		testlog.log(Status.PASS, MarkupHelper.createLabel("This is pass log", ExtentColor.CYAN));

		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage img = ImageIO.read(screen);
		File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
		ImageIO.write(img, "png", new File(filetest + "\\Screenshots\\" + "Test.png"));
		testlog.info("Details of " + "Test screenshot", MediaEntityBuilder
				.createScreenCaptureFromPath(System.getProperty("user.dir") + "\\Screenshots\\" + "Test.png").build());

		// If flush method did not call, Report will not generate.
		reports.flush();
		driver.quit();
		System.out.println("End");
	}
}
