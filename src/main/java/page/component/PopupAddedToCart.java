package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;

import java.time.Duration;

public class PopupAddedToCart {

    WebDriver driver;
    WebDriverWait wait;
    public PopupAddedToCart(WebDriver _driver){
        driver=_driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public CartPage openCartPage(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".view-cart"))).click();
        return new CartPage(driver);
    }
    public void continueShopping(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close-popup"))).click();
    }
    public void closPopupAddedToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mfp-close"))).click();
    }
}
