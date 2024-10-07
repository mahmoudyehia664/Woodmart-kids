package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AboutUsPage extends AbstractPage{
    public AboutUsPage(WebDriver driver){
        super(driver);
    }
    public ContactUsPage openContactUsPage(){
        driver.findElement(By.cssSelector(".btn-style-default")).click();
        return new ContactUsPage(driver);
    }
}
