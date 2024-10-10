package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.component.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class WishlistPage extends AbstractPage{
    WebElement wishlistGroup;
    public WishlistPage(WebDriver driver){
        super(driver);
//        productCart=new ProductCart(driver);
        if (header.isUserLogin())
        {
            wishlistGroup=driver.findElement(By.cssSelector(".wd-wishlist-group"));
        }
    }

    /**
     *
     * @return true if the page empty , false if there is products
     */
    public boolean isEmpty(){
        if (header.isUserLogin())
        {
            try {
                getWait().until(ExpectedConditions.visibilityOf(wishlistGroup.findElement(By.cssSelector(".wd-empty-wishlist"))));
                return true;
            } catch (Exception e) {
                return false;
            }
        }else {
            try {
                getWait().until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".wd-empty-wishlist"))));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     *
     * @param wishlistGroupName the name of the wishlist group
     * @return
     */
    public boolean isEmpty(String wishlistGroupName){
        selectWishlistGroup(wishlistGroupName);
        try {
            wishlistGroup.findElement(By.cssSelector(".wd-empty-wishlist"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void selectWishlistGroup(String wishlistGroupName){
        wishlistGroup=driver.findElement(By.xpath("//h4[contains(.,'"+wishlistGroupName+"')]/ancestor::div[@class='wd-wishlist-group']"));
        int y=wishlistGroup.getLocation().y-155;
        getJs().executeScript("window.scrollTo(0, "+y+");");
        waitForMilliseconds(1500);
    }
    public int numberOfWishlistGroups(){
        return driver.findElements(By.cssSelector(".wd-wishlist-group")).size();
    }
    public int getNumberOfProductsInAWishlistGroup(String wishlistGroupName){
        selectWishlistGroup(wishlistGroupName);
        return wishlistGroup.findElements(By.cssSelector(".wd-product")).size();
    }

    /**
     *
     * @param wishlistGroupName wanted wishlist group
     * @param productName the wanted product
     * @return
     */
    public ProductCart dealWithProduct(String wishlistGroupName,String productName){
        selectWishlistGroup(wishlistGroupName);
        WebElement productCart=wishlistGroup.findElement(By.xpath("//div/h3/a[.='"+productName+"']/ancestor::div[contains(@class,'type-product')]"));
        return new ProductCart(driver,productCart);
    }

    /**
     *
     * @param productName the wanted product from the default wishlist group(My wishlist - if not changed)
     * @return
     */
    public ProductCart dealWithProduct(String productName){
        WebElement productCart=wishlistGroup.findElement(By.xpath("//div/h3/a[.='"+productName+"']/ancestor::div[contains(@class,'type-product')]"));
        return new ProductCart(driver,productCart);
    }

    /**
     *
     * @param productName the name of product to remove from the default wishlist group(My wishlist - if not changed)
     * @return
     */
    public WishlistPage removeProductFromWishlist(String productName){
        WebElement productCart=wishlistGroup.findElement(By.xpath("//div/h3/a[.='"+productName+"']/ancestor::div[contains(@class, 'type-product')]"));
        productCart.findElement(By.cssSelector(".wd-wishlist-remove")).click();
        waitForMilliseconds(1000);
        return this;
    }

    public WishlistPage removeProductFromWishlist(String wishlistGroupName,String productName){
        selectWishlistGroup(wishlistGroupName);
        WebElement productCart=wishlistGroup.findElement(By.xpath("//div/h3/a[.='"+productName+"']/ancestor::div[contains(@class, 'type-product')]"));
        productCart.findElement(By.cssSelector(".wd-wishlist-remove")).click();
        return this;
    }

    public WishlistPage editWishlistGroupName(String name){
        getActions().moveToElement(wishlistGroup.findElement(By.cssSelector(".wd-wishlist-group-action"))).perform();
        getWait().until(ExpectedConditions.visibilityOf(wishlistGroup.findElement(By.cssSelector(".wd-wishlist-edit-title")))).click();
        WebElement renameInput=wishlistGroup.findElement(By.cssSelector(".wd-wishlist-input-rename"));
        getWait().until(ExpectedConditions.visibilityOf(renameInput)).clear();
        renameInput.sendKeys(name);
        wishlistGroup.findElement(By.cssSelector(".wd-wishlist-rename-save")).click();
        return this;
    }
    public WishlistPage editWishlistGroupName(String wishlistGroupName,String name){
        selectWishlistGroup(wishlistGroupName);
        getActions().moveToElement(wishlistGroup.findElement(By.cssSelector(".wd-wishlist-group-action"))).perform();
        getWait().until(ExpectedConditions.visibilityOf(wishlistGroup.findElement(By.cssSelector(".wd-wishlist-edit-title")))).click();
        WebElement renameInput=wishlistGroup.findElement(By.cssSelector(".wd-wishlist-input-rename"));
        getWait().until(ExpectedConditions.visibilityOf(renameInput)).clear();
        renameInput.sendKeys(name);
        wishlistGroup.findElement(By.cssSelector(".wd-wishlist-rename-save")).click();
        return this;
    }
    public WishlistPage removeWishlistGroup(String wishlistGroupName){
        selectWishlistGroup(wishlistGroupName);
        getActions().moveToElement(wishlistGroup.findElement(By.cssSelector(".wd-wishlist-group-action"))).perform();
        getWait().until(ExpectedConditions.visibilityOf(wishlistGroup.findElement(By.cssSelector(".wd-wishlist-remove-group")))).click();
        driver.switchTo().alert().accept();
        waitForMilliseconds(1000);
        return this;
    }
    public String getWishlistGroupsNames(){
        waitForMilliseconds(2000);
        List<WebElement> names=driver.findElements(By.cssSelector(".wd-wishlist-group-title h4"));
        List<String> text=new ArrayList<>();
        for (WebElement name:names){
            text.add(name.getText());
        }
        return text.toString();
    }
    public String getAllProductsNameInTheWishlistPage(){
        List<WebElement> names=driver.findElements(By.cssSelector(".product .wd-entities-title"));
        List<String> text=new ArrayList<>();
        for (WebElement name:names){
            text.add(name.getText());
        }
        return text.toString();
    }

    /**
     *
     * @return string contain all products names from the default wishlist group
     */
    public String getAllProductsNameInAWishlistGroup(){
        List<WebElement> names=wishlistGroup.findElements(By.cssSelector(".product .wd-entities-title"));
        List<String> text=new ArrayList<>();
        for (WebElement name:names){
            text.add(name.getText());
        }
        return text.toString();
    }
    public String getAllProductsNameInAWishlistGroup(String wishlistGroupName){
        selectWishlistGroup(wishlistGroupName);
        List<WebElement> names=wishlistGroup.findElements(By.cssSelector(".product .wd-entities-title"));
        List<String> text=new ArrayList<>();
        for (WebElement name:names){
            text.add(name.getText());
        }
        return text.toString();
    }

    public WishlistPage addNewWishlistGroup(String wishlistGroupName){
        driver.findElement(By.cssSelector(".wd-wishlist-create-group-btn")).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wd-wishlist-group-name"))).sendKeys(wishlistGroupName);
        driver.findElement(By.cssSelector(".wd-wishlist-save-btn")).click();
        getActions().click().perform();
        waitForMilliseconds(3000);
        return this;
    }

}
