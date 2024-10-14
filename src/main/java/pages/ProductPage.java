package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.component.PopupAddedToCart;

public class ProductPage extends AbstractPage{
    public ProductPage(WebDriver driver){
        super(driver);
    }
    public PopupAddedToCart addToCart(){
        driver.findElement(By.cssSelector(".single_add_to_cart_button")).click();
        return new PopupAddedToCart(driver);
    }
    public CheckoutPage buyNow(){
        driver.findElement(By.cssSelector(".wd-buy-now-btn")).click();
        return new CheckoutPage(driver);
    }
    public String getProductName(){
        return driver.findElement(By.tagName("h1")).getText();
    }
}
