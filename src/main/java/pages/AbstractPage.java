package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.component.Header;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    public Header header;

    public AbstractPage(WebDriver _driver){
        driver=_driver;
        header=new Header(driver);
    }
    protected WebDriverWait getWait(){
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected JavascriptExecutor getJs(){
        return (JavascriptExecutor)driver;
    }
    public void closQuickView(){
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
    }
}
