package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.component.Header;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
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
    protected Actions getActions(){
        return new Actions(driver);
    }
    public void closQuickView(){
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
    }

    /**
     *
     * @return true --> page found      false --> page not found
     */
    public boolean isFound(){
        try {
            driver.findElement(By.cssSelector(".page-header h3"));
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    public String getPageTitle(){
        return driver.getTitle();
    }
    public String getPageURL(){
        return driver.getCurrentUrl();
    }
    public void waitForMilliseconds(long milliseconds) {
        synchronized (this) {
            try {
                wait(milliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
