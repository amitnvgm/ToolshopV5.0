package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> classTest = new ThreadLocal<>(); // ✅ Parent (Class Level)
    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>(); // ✅ Child (Method Level)
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();// ✅ Thread-safe startTime variable
    private String repName;

    public void onStart(ITestContext context) 
    {
        System.out.println("🚀 Initializing Extent Report...");
        if (extent == null) 
        {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName = "Test-Report-" + timestamp + ".html";
            sparkReporter = new ExtentSparkReporter("./reports/" + repName);

            // 📌 Customize Report Appearance
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimelineEnabled(true); // ✅ Enable execution timeline graph

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            System.out.println("✅ Extent Report Initialized");
        } 
        else 
        {
            System.out.println("⚠️ WARNING: Extent Report already initialized");
        }
    }

    public void onTestStart(ITestResult result) 
    {
        startTime.set(System.currentTimeMillis()); // ✅ Capture start time

        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();

        // ✅ Ensure class-level ExtentTest exists
        if (classTest.get() == null || !classTest.get().getModel().getName().equals(className)) 
        {
            ExtentTest parentTest = extent.createTest(className); // ✅ Parent test (Class)
            classTest.set(parentTest);
        }

        // ✅ Create child test under class test
        ExtentTest childTest = classTest.get().createNode(methodName);
        methodTest.set(childTest); // ✅ Assign to ThreadLocal

        // ✅ Ensure methodTest is not null before logging
        if (methodTest.get() != null) 
        {
            methodTest.get().log(Status.INFO, "Test Execution Started: " + methodName);
        } 
        else 
        {
            System.out.println("⚠️ ERROR: methodTest is NULL for " + methodName);
        }

        // ✅ Assign TestNG Groups
        String[] groups = result.getMethod().getGroups();
        for (String group : groups) 
        {
            methodTest.get().assignCategory(group);
        }
    }

    public void onTestSuccess(ITestResult result) 
    {
        long executionTime = System.currentTimeMillis() - startTime.get(); // ✅ Calculate execution time
        if (methodTest.get() != null) 
        {
            methodTest.get().log(Status.PASS, result.getMethod().getMethodName() + " passed ✅");
            methodTest.get().log(Status.INFO, "⏱ Execution Time: " + executionTime + " ms");
        }
    }

    public void onTestFailure(ITestResult result) 
    {
        if (methodTest.get() != null) 
        {
            methodTest.get().log(Status.FAIL, result.getMethod().getMethodName() + " failed ❌");
            methodTest.get().log(Status.INFO, "Failure Reason: " + result.getThrowable().getMessage());

            try 
            {
                String imgPath = new BaseClass().captureScreen(result.getMethod().getMethodName());
                methodTest.get().addScreenCaptureFromPath(imgPath, "Screenshot on Failure");
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                methodTest.get().log(Status.WARNING, "Failed to capture screenshot!");
            }
        } 
        else 
        {
            System.out.println("⚠️ ERROR: methodTest is NULL for " + result.getMethod().getMethodName());
        }
    }

    public void onTestSkipped(ITestResult result) 
    {
        if (methodTest.get() != null) 
        {
            methodTest.get().log(Status.SKIP, result.getMethod().getMethodName() + " skipped ⚠️");
        }
    }

    public void onFinish(ITestContext context) 
    {
        extent.flush();
    }
}
