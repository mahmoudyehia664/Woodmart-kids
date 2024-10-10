package base;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.util.Arrays;
import java.util.Map;


public class BaseTest {
    protected static HomePage homePage;
    protected static WebDriver driver;
    @BeforeSuite
    @Parameters({"browser"})
    public static void test(String browser, ITestContext context){
//        context.setAttribute("browser", browser);
        switch (browser){
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // This also helps in hiding the message
                chromeOptions.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                ));
                driver=new ChromeDriver(chromeOptions);
                break;
            case "Edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver=new EdgeDriver(edgeOptions);
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Clear local storage
        js.executeScript("window.localStorage.clear();");
        // Clear session storage
        js.executeScript("window.sessionStorage.clear();");
        // Clear cache
        driver.manage().deleteAllCookies();
        driver.get("https://test-iti-testing-project-v1.pantheonsite.io/");
        homePage.header.logout();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            var camera=(TakesScreenshot)driver;
            File screenShot=camera.getScreenshotAs(OutputType.FILE);
            try {
                Object[] params = result.getParameters();
                String parameters = (params.length == 0) ? "" : Arrays.toString(params);
                String name=result.getMethod().getMethodName()+parameters;
                Files.move(screenShot,new File("src/test/screenshot/"+name.replaceAll("[^a-zA-Z0-9 @.,\\[\\]]", "")+" in "+ result.getTestContext().getCurrentXmlTest().getParameter("browser")+" browser.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
