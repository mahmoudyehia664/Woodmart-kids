package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.component.QuantityController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CartPage extends AbstractPage{
    WebElement baseElement;
    WebElement product=null;
    By baseElementLocator=By.cssSelector(".cart tbody");
    By openCheckoutPage=By.cssSelector(".checkout-button");
    By allProductsLocator=By.cssSelector(".woocommerce-cart-form__cart-item");
    By totalPrice=By.cssSelector(".order-total bdi");
    By name =By.cssSelector(".product-name");
    By quantity=By.cssSelector(".qty");
    By price=By.tagName("bdi");
    By subTotalPrice=By.cssSelector(".product-subtotal bdi");

    public CartPage(WebDriver driver){
        super(driver);
        baseElement=driver.findElement(baseElementLocator);
    }
    public boolean isQuantityChanged(){
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-message"))).getText().contains("Cart updated.");
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isProductRemoved(){
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-message"))).getText().endsWith("Undo?");
        } catch (Exception e) {
            return false;
        }
    }
    public CheckoutPage openCheckoutPage(){
        driver.findElement(openCheckoutPage).click();
        return new CheckoutPage(driver);
    }
    public CartPage dealWithQuantity(int index){
        if(!checkIndex(index)){
            System.out.println("Wrong index");
            return null;
        }
        product=baseElement.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
        return this;
    }
    /************/

    public CartPage setQuantity(int quantity){
        new QuantityController(driver,product).setQuantity(quantity);
        return this;
    }
    public CartPage plusQuantity(){
        new QuantityController(driver,product).plusQuantity();
        return this;
    }
    public CartPage minusQuantity(){
        new QuantityController(driver,product).minusQuantity();
        return this;
    }
    /***********/
    public void deleteProductFromCart(int index){
        if(!checkIndex(index)){
            System.out.println("Wrong index");
        }else {
            WebElement product=baseElement.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
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
    public double getTotalPriceOfProducts(){
        if (getNumberOfProductsInTheCart()==0){
            return 0.0;
        }
        if(header.getCurrentCurrency().equals("USD")){
            return Double.parseDouble(driver.findElement(totalPrice).getText().replaceAll("[,$]", ""));
        }else {
            return Double.parseDouble(driver.findElement(totalPrice).getText().replaceAll("[.€EGP]", "").replace(",", "."));
        }
    }
    private boolean checkIndex(int index){
        return index<=getNumberOfProductsInTheCart() && index>0;
    }

    /**
     *
     * @param index starts from 1
     * @return product name
     */
    public String getProductName(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            return singleProduct.findElement(name).getText();
        }else {
            return "Wrong index";
        }
    }

    /**
     *
     * @param index starts from 1
     * @return product price
     */
    public String getProductPrice(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            return singleProduct.findElement(price).getText();
        }else {
            return "Wrong index";
        }
    }
    /**
     *
     * @param index starts from 1
     * @return product total price
     */
    public String getProductSubTotalPrice(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            return singleProduct.findElement(subTotalPrice).getText();
        }else {
            return "Wrong index";
        }
    }

    /**
     *
     * @param index starts from 1
     * @return product quantity
     */
    public String getProductQuantity(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
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
    private String getProductSubTotalPrice(WebElement element){
        return element.findElement(subTotalPrice).getText();
    }

    /**
     *
     * @param index starts from 1
     * @return HashMap<String, String> contain product data [Name,Price,Quantity]
     */
    public HashMap<String, String> getProductDataFromCart(int index){
        if (!checkIndex(index)){
            System.out.println("Wrong index");
            return null;
        }
        WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
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
    public List<HashMap<String, String>> getProductsDataFromCart(){
        List<HashMap<String, String>> productsDetails = new ArrayList<>();
        for (int i=0;i<getNumberOfProductsInTheCart();i++){
            productsDetails.add(getProductDataFromCart(i));
        }
        return productsDetails;
    }
    public String getProductsNamesFromCart(){
        List<WebElement> elements=baseElement.findElements(By.cssSelector(".cart_item"));
        List<String> names=new ArrayList<>();
        for (WebElement element:elements){
            names.add(getProductName(element));
        }
        return names.toString();
    }
    public double[] getProductsPriceFromCart(){
        List<WebElement> elements=baseElement.findElements(By.cssSelector(".cart_item"));
        double[] prices=new double[elements.size()];
//        List<String> prices=new ArrayList<>();
        int i=0;
        for (WebElement element:elements){
            if(header.getCurrentCurrency().equals("USD")){
                prices[i]=Double.parseDouble(getProductSubTotalPrice(element).replaceAll("[,$]", ""));
            }else {
                prices[i]=Double.parseDouble(getProductSubTotalPrice(element).replaceAll("[.€EGP]", "").replace(",", "."));
            }
            i++;
        }
        return prices;
    }
    public CartPage applyCoupon(String coupon){
        baseElement.findElement(By.id("coupon_code")).sendKeys(coupon);
        baseElement.findElement(with(By.tagName("button")).toLeftOf(By.id("coupon_code"))).click();
//        baseElement.findElement(By.name("apply_coupon")).click();
        return this;
    }



}
