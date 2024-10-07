package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.util.Arrays;


public class BaseTest {
    protected static HomePage homePage;
    protected static WebDriver driver;
    @BeforeSuite
    @Parameters({"browser"})
    public static void test(String browser, ITestContext context){
        context.setAttribute("browser", browser);
        switch (browser){
            case "Chrome":
                driver=new ChromeDriver();
                break;
            case "Edge":
                driver=new EdgeDriver();
                break;
            case "Firefox":
                driver=new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        homePage=new HomePage(driver,"https://test-iti-testing-project-v1.pantheonsite.io/");
    }
    @BeforeMethod
    public void getPage(){
        driver.get("https://test-iti-testing-project-v1.pantheonsite.io/");
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
        var camera=(TakesScreenshot)driver;
        File screenShot=camera.getScreenshotAs(OutputType.FILE);
        try {
            Object[] params = result.getParameters();
            String parameters = (params.length == 0) ? "" : Arrays.toString(params);
            Files.move(screenShot,new File("src/test/screenshot/"+result.getMethod().getMethodName()+parameters+" in "+ result.getTestContext().getAttribute("browser")+" browser" +".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
