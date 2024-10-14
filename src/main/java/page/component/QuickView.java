package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import pages.ProductPage;

import java.time.Duration;
import java.util.HashMap;

public class QuickView {
    private final WebDriver driver;
    private final WebElement productCart;
//    public QuantityController quantityController;
    private WebElement baseElement;
    private final By brand=By.cssSelector(".woocommerce-product-attributes-item--attribute_pa_brand p");
    private final By size=By.cssSelector(".woocommerce-product-attributes-item--attribute_pa_size .woocommerce-product-attributes-item__value");
    private final By color=By.cssSelector(".woocommerce-product-attributes-item--attribute_pa_color .woocommerce-product-attributes-item__value");
    private final By name=By.cssSelector(".product_title a");
    private final By price=By.tagName("bdi");
    private final By categories=By.cssSelector(".posted_in");
    private final By sku=By.cssSelector(".sku");
    private final By close=By.className("mfp-close");
    private final By addToCartButton=By.className("single_add_to_cart_button");
    private final By buyNowButton=By.className("wd-buy-now-btn");
    private final By viewDetailsButton=By.className("view-details-btn");
    private final By images=By.className("wd-carousel-wrap");


    public QuickView(WebDriver _driver, WebElement product){
        driver=_driver;
        productCart=product;
        /***/
        Actions actions=new Actions(driver);
        actions.moveToElement(product).perform();
        /***/
        openQuickView();
    }

    private void openQuickView(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        /***/
        /***/
        wait.until(ExpectedConditions.elementToBeClickable(productCart.findElement(By.cssSelector(".quick-view a")))).click();
        baseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-quick-view")));
//        quantityController=new QuantityController(driver,baseElement);
//        return this;
    }
    public QuickView setQuantity(int quantity){
        new QuantityController(driver,baseElement).setQuantity(quantity);
        return this;
    }
    public String getQuantity(){
        return new QuantityController(driver,baseElement).getQuantity();
    }
    public QuickView plusQuantity(){
        new QuantityController(driver,baseElement).plusQuantity();
        return this;
    }
    public QuickView minusQuantity(){
        new QuantityController(driver,baseElement).minusQuantity();
        return this;
    }
    public void closeQuickView(){
        /*Actions actions=new Actions(driver);*/
        /*actions.keyDown(Keys.ESCAPE).build().perform();*/
        driver.findElement(close).click();
    }
    public String getProductName(){
        return baseElement.findElement(name).getText();
    }
    public String getProductURL(){
        return baseElement.findElement(name).getAttribute("href");
    }
    public String getProductPrice(){
        return baseElement.findElement(price).getText();
    }
    public String getProductSize(){
        return baseElement.findElement(size).getText();
    }
    public String getProductColor(){
        return baseElement.findElement(color).getText().replace("\n"," ").replace(",","|");
    }
    public String getProductBrand(){
        return baseElement.findElement(brand).getText();
    }
    public String getProductCategories(){
        return baseElement.findElement(categories).getText().replace("Categories:","").replace(",","|");
    }
    public String getProductSku(){
        return baseElement.findElement(sku).getText();
    }
    public ProductPage openProductPage(){
        baseElement.findElement(viewDetailsButton).click();
        return new ProductPage(driver);
    }
    public HashMap<String,String> getAllProductDetails(){
        HashMap<String,String> productDetails=new HashMap<>();
        productDetails.put("Name ",getProductName());
        productDetails.put("URL ",getProductURL());
        productDetails.put("Price ",getProductPrice());
        productDetails.put("Size ",getProductSize());
        productDetails.put("Color ",getProductColor());
        productDetails.put("Brand ",getProductBrand());
        productDetails.put("Sku ",getProductSku());
        productDetails.put("Categories ",getProductCategories());
        return productDetails;
    }

    public PopupAddedToCart addToCart(){
        baseElement.findElement(addToCartButton).click();
        return new PopupAddedToCart(driver);
    }
    public CheckoutPage buyNow(){
        baseElement.findElement(buyNowButton).click();
        return new CheckoutPage(driver);
    }

}
