package genericutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseClassFinder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener,ISuiteListener{

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String date = new Date().toString().replace("","_").replace(":","_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/reports_"+date+".html");
		spark.config().setDocumentTitle("Ninza CRM Reports");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "edge");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		test = report.createTest(testCaseName);
		System.out.println(testCaseName+" Excecution Started");
		test.log(Status.INFO,testCaseName+"Execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		test.log(Status.PASS,testCaseName+"Execution Success");
		//System.out.println(testCaseName+" Excecution Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		String date = new Date().toString().replace(" ","_").replace(":", "_");
		test.log(Status.FAIL, testCaseName+"Execution Failed");
		//System.out.println(testCaseName+" Excecution Failed");
		TakesScreenshot ts = (TakesScreenshot)baseClass.BasePage.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
				
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		test.log(Status.SKIP,testCaseName+ "Execution Skipped");
		//System.out.println(testCaseName+" Excecution Skipped");
	}

	
	
}
