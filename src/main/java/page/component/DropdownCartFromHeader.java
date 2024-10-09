package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CheckoutPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DropdownCartFromHeader {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    WebElement baseElement;
    WebElement product=null;
    By baseElementLocator =By.cssSelector(".wd-dropdown-cart");
    By dropDownCart=By.cssSelector("[title='Shopping cart']");
    By openCart=By.className("btn-cart");
    By openCheckoutPage=By.className("checkout");
    By totalPrice=By.cssSelector(".total bdi");
    By allProductsLocator=By.cssSelector(".mini_cart_item");
    By name =By.cssSelector(".wd-entities-title");
    By quantity=By.cssSelector(".qty");
    By price=By.tagName("bdi");

    public DropdownCartFromHeader(WebDriver _driver){
        driver=_driver;
        actions=new Actions(driver);
        actions.moveToElement(driver.findElement(dropDownCart)).perform();
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        baseElement=wait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
    }
    public CartPage openCart(){
        baseElement.findElement(openCart).click();
        return new CartPage(driver);
    }
    public CheckoutPage openCheckoutPage(){
        baseElement.findElement(openCheckoutPage).click();
        return new CheckoutPage(driver);
    }
    public DropdownCartFromHeader dealWithQuantity(int index){
        if(!checkIndex(index)){
            System.out.println("Wrong index");
            return null;
        }
        product=baseElement.findElement(By.xpath("(//li[contains(@class,'mini_cart_item')])["+index+"]"));
        return this;
    }
    /************/

    public DropdownCartFromHeader setQuantity(int quantity){
        new QuantityController(driver,product).setQuantity(quantity);
        return this;
    }
    public DropdownCartFromHeader plusQuantity(){
        new QuantityController(driver,product).plusQuantity();
        return this;
    }
    public DropdownCartFromHeader minusQuantity(){
        new QuantityController(driver,product).minusQuantity();
        return this;
    }


    /***********/
    public void deleteProductFromCart(int index){
        if(!checkIndex(index)){
            System.out.println("Wrong index");
        }else {
            WebElement product=baseElement.findElement(By.xpath("(//li[contains(@class,'mini_cart_item')])["+index+"]"));
            product.findElement(By.className("remove")).click();
        }
    }
    public int getNumberOfProductsInTheCart(){
        try {
        return baseElement.findElements(allProductsLocator).size();
        } catch (Exception e) {
            return 0;
        }
    }
    public String getTotalPriceOfProducts(){
        if (getNumberOfProductsInTheCart()==0){
            return "No products in the dropdown cart";
        }
        return baseElement.findElement(totalPrice).getText();
    }
    private boolean checkIndex(int index){
        return index<=getNumberOfProductsInTheCart() && index>0;
    }
    public String getProductName(int index){
        if (checkIndex(index)){
            WebElement singleProduct=baseElement.findElement(By.xpath("(//li[contains(@class,'mini_cart_item')])["+index+"]"));
            return singleProduct.findElement(name).getText();
        }else {
            return "Wrong index";
        }
    }
    public String getProductPrice(int index){
        if (checkIndex(index)){
            WebElement singleProduct=baseElement.findElement(By.xpath("(//li[contains(@class,'mini_cart_item')])["+index+"]"));
            return singleProduct.findElement(price).getText();
        }else {
            return "Wrong index";
        }
    }
    public String getProductQuantity(int index){
        if (checkIndex(index)){
            WebElement singleProduct=baseElement.findElement(By.xpath("(//li[contains(@class,'mini_cart_item')])["+index+"]"));
            return singleProduct.findElement(quantity).getAttribute("value");
        }else {
            return "Wrong index";
        }
    }
    private String getProductName(WebElement element){
        return element.findElement(name).getText();
    }
    private String getProductPrice(WebElement element){
        return element.findElement(price).getText();
    }
    private String getProductQuantity(WebElement element){
        return element.findElement(quantity).getAttribute("value");
    }

    /**
     *
     * @param index starts from 1
     * @return HashMap<String, String> contain product data [Name,Price,Quantity]
     */
    public HashMap<String, String> getProductDataFromMiniCart(int index){
        if (!checkIndex(index)){
            System.out.println("Wrong index");
            return null;
        }
        WebElement singleProduct=baseElement.findElement(By.xpath("(//li[contains(@class,'mini_cart_item')])["+index+"]"));
        HashMap<String, String> productData = new HashMap<>();
        productData.put("Name",getProductName(singleProduct));
        productData.put("Price",getProductPrice(singleProduct));
        productData.put("Quantity",getProductQuantity(singleProduct));
        return productData;
    }

    /**
     *
     * @return List<HashMap<String, String>> contain [Name,Price,Quantity] for each product
     */
    public List<HashMap<String, String>> getProductsDataFromMiniCart(){
        List<HashMap<String, String>> productsDetails = new ArrayList<>();
        for (int i=0;i<getNumberOfProductsInTheCart();i++){
            productsDetails.add(getProductDataFromMiniCart(i));
        }
        return productsDetails;
    }


}
