package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage extends AbstractPage{
    public WishlistPage(WebDriver driver){
        super(driver);
    }

    /**
     *
     * @return true if the page empty , false if there is products
     */
    public boolean isEmpty(){
        try {
            driver.findElement(By.cssSelector(".wd-empty-wishlist"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
