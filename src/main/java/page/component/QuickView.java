package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QuickView {
    private final WebDriver driver;
    private final WebElement productCart;
    private WebElement baseElement;
    public QuickView(WebDriver _driver, WebElement product){
        driver=_driver;
        productCart=product;
    }

    public QuickView openQuickView(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(productCart.findElement(By.cssSelector(".quick-view a")))).click();
        baseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-quick-view")));
        /*class product_type_variable,class product_type_simple , class variations*/
        /*try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }*/
        return this;
    }
    public void closeQuickView(){
        /*Actions actions=new Actions(driver);*/
        /*actions.keyDown(Keys.ESCAPE).build().perform();*/
        driver.findElement(By.className("mfp-close")).click();
    }
    public String getProductName(){
        return baseElement.findElement(By.cssSelector(".product_title a")).getText();
    }

}
