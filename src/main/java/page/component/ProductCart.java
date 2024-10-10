package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CompareProductsPage;
import pages.ProductPage;
import pages.WishlistPage;

import java.time.Duration;
import java.util.HashMap;

public class ProductCart {
    private final WebDriver driver;
    private WebElement productCart=null;
    private Actions actions;
    private WebDriverWait wait;
    WebElement addToWishlistButton;
    WebElement addToCompareButton;
    public ProductCart(WebDriver _driver){
        driver=_driver;
    }
    public ProductCart(WebDriver _driver,WebElement _productCart){
        driver=_driver;
        productCart=_productCart;
        actions=new Actions(driver);
        actions.scrollByAmount(0,-5).moveToElement(_productCart).build().perform();
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

    /*class product_type_variable,class product_type_simple , class variations , class out-of-stock*/

    /**
     *
     * @param productId productId take the value of attribute (data-id)
     * @return the ProductCart object again to continue using it is functionality
     */
    public ProductCart selectProduct(int productId){
        actions=new Actions(driver);
        productCart=driver.findElement(By.cssSelector("div[data-id='"+productId+"']"));
//        quickView=new QuickView(driver,productCart);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int y=productCart.getLocation().y-150;
        js.executeScript("window.scrollTo(0, "+y+");");
        actions.moveToElement(productCart).perform();
        return this;
    }

    /**
     *
     * @param productName take the exact name of the product
     * @return the ProductCart object again to continue using it is functionality
     */
    public ProductCart selectProduct(String productName){
        actions=new Actions(driver);
        productCart=driver.findElement(By.xpath("//div/h3/a[.='"+productName+"']/ancestor::div[contains(@class, 'type-product')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int y=productCart.getLocation().y-155;
        js.executeScript("window.scrollTo(0, "+y+");");
        actions.scrollByAmount(0,5);
        actions.moveToElement(productCart).perform();
//        quickView=new QuickView(driver,productCart);
        return this;
    }
    public QuickView openQuickView(){
        if (productCart==null){
            System.out.println("You must select the product first");
            return null;
        }
        return new QuickView(driver,productCart);
    }
    public PopupAddedToCart addSimpleProductToCart(){
        wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(productCart.findElement(By.className("product_type_simple")))).click();
        } catch (Exception e) {
            System.out.println("This is a variable product");
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-added_to_cart")));
        return new PopupAddedToCart(driver);
    }
    public ProductPage addProductWithSoldOutLabelToCart(){
        if (productCart.getAttribute("class").contains("outofstock")){
            productCart.findElement(By.className("add-to-cart-loop")).click();
        }else{
            System.out.println("This product in the stock(no sold out label)");
        }
        return new ProductPage(driver);
    }

    /**
     *
     * @param sizeIndex start from 1
     * @param colorName name of the color start with capital letter
     * @param brandIndex start from 1
     * @return
     */
    public PopupAddedToCart addVariableProductToCart(int sizeIndex,String colorName,int brandIndex){
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
        wait.until(ExpectedConditions.elementToBeClickable(productCart.findElement(By.className("product_type_variable")))).click();
        } catch (Exception e) {
            System.out.println("This is a simple product not variable");
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quick-shop-wrapper")));
        new Select(productCart.findElement(By.id("pa_size"))).selectByIndex(sizeIndex);
        productCart.findElement(By.cssSelector("[data-title='"+colorName+"']")).click();
        new Select(productCart.findElement(By.id("pa_brand"))).selectByIndex(brandIndex);
        productCart.findElement(By.className("single_add_to_cart_button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-added_to_cart")));
        return new PopupAddedToCart(driver);
    }

    public ProductPage openProductPage(){
        productCart.click();
        return new ProductPage(driver);
    }

    private final By loginText=By.cssSelector("[title='My account'] .wd-tools-text");
    public boolean isUserLogin(){
        return !driver.findElement(loginText).getText().equals("Login / Register");
    }


    public PopupAddToWishlist addToWishlist(){
        addToWishlistButton=productCart.findElement(By.cssSelector(".wd-wishlist-btn a"));
        actions=new Actions(driver);
        actions.moveToElement(addToWishlistButton).perform();
        if(productCart.findElement(By.cssSelector(".wd-wishlist-btn span")).getText().equals("Add to wishlist")){
            addToWishlistButton.click();
            if (isUserLogin()){
                return new PopupAddToWishlist(driver);
            }
            waitForMilliseconds(1000);
        }else {
            System.out.println("The product already in the wishlist");
        }
        return null;
    }
    public WishlistPage addToWishlistThenOpenWishListPage(){
        addToWishlist();
        addToWishlistButton.click();
        return new WishlistPage(driver);
    }
    public void addToComparePage(){
        addToCompareButton=productCart.findElement(By.cssSelector(".wd-compare-btn a"));
        actions=new Actions(driver);
        actions.moveToElement(addToCompareButton).perform();
        if(productCart.findElement(By.cssSelector(".wd-compare-btn span")).getText().equals("Compare")){
            addToCompareButton.click();
        }else {
            System.out.println("The product already in the compare list");
        }
    }
    public CompareProductsPage addToCompareThenOpenComparePage(){
        addToComparePage();
        addToCompareButton.click();
        return new CompareProductsPage(driver);
    }

    public String getProductName(){
        return productCart.findElement(By.cssSelector(".wd-entities-title a")).getText();
    }
    public String getProductBrand(){
        return productCart.findElement(By.cssSelector(".wd-product-brands-links a")).getText();
    }
    public String getProductPrice(){
        return productCart.findElement(By.cssSelector(".price bdi")).getText();
    }
    public String getProductURL(){
        return productCart.findElement(By.cssSelector(".wd-entities-title a")).getAttribute("href");
    }
    public String getProductId(){
        return productCart.getAttribute("data-id");
    }
    public String getProductCategories(){
        return productCart.findElement(By.cssSelector(".wd-product-cats")).getText().replace(", ","|");
    }

    /**
     *
     * @return HashMap<String,String> contain product data [Name,URL,Price,Brand,Categories,ID]
     */
    public HashMap<String,String> getAllProductDetails(){
        HashMap<String,String> productDetails=new HashMap<>();
        productDetails.put("Name",getProductName());
        productDetails.put("URL",getProductURL());
        productDetails.put("Price",getProductPrice());
        productDetails.put("Brand",getProductBrand());
        productDetails.put("Categories",getProductCategories());
        productDetails.put("ID",getProductId());
        return productDetails;
    }
}
