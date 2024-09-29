import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Arrays;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    @Test
    public void base(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://test-iti-testing-project-v1.pantheonsite.io/");
        homePage=new HomePage(driver);
        homePage.header.changeCurrency(1);
//        System.out.println(homePage.header.sendTextToSearchFor("bunny"));
//        driver.quit();
    }
}
