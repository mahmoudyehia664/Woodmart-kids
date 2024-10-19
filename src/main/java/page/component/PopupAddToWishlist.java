package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.WishlistPage;

import java.time.Duration;
import java.util.Arrays;

public class PopupAddToWishlist {

    WebDriver driver;
    WebDriverWait wait;
    By wishlistNamesLocator=By.cssSelector(".wd-wishlist-group-list");
    public PopupAddToWishlist(WebDriver _driver){
        driver=_driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void waitForMilliseconds(long milliseconds) {
        synchronized (this) {
            try {
                wait(milliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void backToShop(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-back-to-shop"))).click();
    }
    private void openWishlistPage(){
        driver.findElement(By.cssSelector(".wd-wishlist-back-to-lists")).click();
    }

    /**
     * add product to the default wishlist(My wishlist if not changed)
     */
    public void addToWishlistThenBackToShop(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-save-btn"))).click();
        backToShop();
        waitForMilliseconds(1500);
    }
    /**
     * add product to the default wishlist(My wishlist if not changed)
     */
    public WishlistPage addToWishlistThenOpenWishlist(){
        driver.findElement(By.cssSelector(".wd-wishlist-save-btn")).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-add-success .title")));
        } catch (Exception e) {
            System.out.println("Product not added to the wishlist");
            throw new RuntimeException(e);
        }
        openWishlistPage();
        return new WishlistPage(driver);
    }

    /**
     *
     * @param wishlistName add product to selected wishlist
     */
    public void addToWishlistThenBackToShop(String wishlistName){
        String[] wishlistNames= driver.findElement(wishlistNamesLocator).getText().replace("\nAdd new wishlist","").replace("\n",",").split(",");
        int index=Arrays.asList(wishlistNames).indexOf(wishlistName)+1;
        try {
            driver.findElement(By.xpath("(//ul/li/label)["+index+"]")).click();
        } catch (Exception e) {
            System.out.println("This wishlist group not existing , you should add it first");
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector(".wd-wishlist-save-btn")).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-add-success .title")));
        } catch (Exception e) {
            System.out.println("Product not added to the wishlist");
            throw new RuntimeException(e);
        }
        backToShop();
        waitForMilliseconds(1500);
    }
    /**
     *
     * @param wishlistName add product to selected wishlist
     */
    public WishlistPage addToWishlistThenOpenWishlist(String wishlistName){
        String[] wishlistNames= driver.findElement(wishlistNamesLocator).getText().replace("\nAdd new wishlist","").replace("\n",",").split(",");
//        System.out.println(Arrays.toString(wishlistNames));
        int index=Arrays.asList(wishlistNames).indexOf(wishlistName)+1;
//        System.out.println(index);
        try {
//            System.out.println(driver.findElement(By.xpath("(//ul/li/label)["+index+"]")).getText());
            driver.findElement(By.xpath("(//ul/li/label)["+index+"]")).click();
        } catch (Exception e) {
            System.out.println("This wishlist group not existing , you should add it first");
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector(".wd-wishlist-save-btn")).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-add-success .title")));
        } catch (Exception e) {
            System.out.println("Product not added to the wishlist");
            throw new RuntimeException(e);
        }
        openWishlistPage();
        return new WishlistPage(driver);
    }
    public void addToNewWishListThenBackToShop(String wishlistGroupName){
        driver.findElement(By.cssSelector(".wd-wishlist-add-group a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-group-name"))).sendKeys(wishlistGroupName);
        driver.findElement(By.cssSelector(".wd-wishlist-save-btn")).click();
        backToShop();
    }
    public PopupAddToWishlist addToNewWishListThenOpenWishlistPage(String wishlistGroupName){
        driver.findElement(By.cssSelector(".wd-wishlist-add-group a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-group-name"))).sendKeys(wishlistGroupName);
        driver.findElement(By.cssSelector(".wd-wishlist-save-btn")).click();
        openWishlistPage();
        return this;
    }
}
