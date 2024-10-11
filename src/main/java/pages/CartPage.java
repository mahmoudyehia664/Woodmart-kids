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
//    WebElement baseElement;
    WebElement product=null;
//    By baseElementLocator=By.cssSelector(".cart tbody");
    By openCheckoutPage=By.cssSelector(".cart tbody .checkout-button");
    By allProductsLocator=By.cssSelector(".cart tbody .cart_item");
    By totalPrice=By.cssSelector(".cart tbody .order-total bdi");
    By name =By.cssSelector(".cart tbody .product-name");
    By quantity=By.cssSelector(".cart tbody .qty");
    By price=By.cssSelector(".cart tbody bdi");
    By subTotalPrice=By.cssSelector(".cart tbody .product-subtotal bdi");
    By remove=By.cssSelector(".product-remove .remove");

    public CartPage(WebDriver driver){
        super(driver);
//        baseElement=driver.findElement(baseElementLocator);
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
        product=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
        return this;
    }
    /************/

    public CartPage setQuantity(int quantity){
        new QuantityController(driver,product).setQuantity(quantity);
//        isQuantityChanged();
        waitForMilliseconds(2000);
        return this;
    }
    public CartPage plusQuantity(){
        new QuantityController(driver,product).plusQuantity();
        waitForMilliseconds(2000);
        return this;
    }
    public CartPage minusQuantity(){
        new QuantityController(driver,product).minusQuantity();
        waitForMilliseconds(2000);
        return this;
    }
    /***********/
    public void deleteProductFromCart(int index){
        if(!checkIndex(index)){
            System.out.println("Wrong index");
        }else {
            WebElement product=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            product.findElement(By.className("remove")).click();
        }
    }
    public int getNumberOfProductsInTheCart(){
        try {
            return driver.findElements(allProductsLocator).size();
        } catch (Exception e) {
            e.printStackTrace();
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
        return (index<=getNumberOfProductsInTheCart() && index>0);
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
    public double getProductPrice(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            if(header.getCurrentCurrency().equals("USD")){
                return Double.parseDouble(singleProduct.findElement(price).getText().replaceAll("[,$]", ""));
            }else {
                return Double.parseDouble(singleProduct.findElement(price).getText().replaceAll("[.€EGP]", "").replace(",", "."));
            }
        }else {
            return 0.0;
        }
    }
    /**
     *
     * @param index starts from 1
     * @return product total price
     */
    public double getProductSubTotalPrice(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            if(header.getCurrentCurrency().equals("USD")){
                return Double.parseDouble(singleProduct.findElement(subTotalPrice).getText().replaceAll("[,$]", ""));
            }else {
                return Double.parseDouble(singleProduct.findElement(subTotalPrice).getText().replaceAll("[.€EGP]", "").replace(",", "."));
            }
        }else {
            return 0.0;
        }
    }

    /**
     *
     * @param index starts from 1
     * @return product quantity
     */
    public int getProductQuantity(int index){
        if (checkIndex(index)){
            WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
            return Integer.parseInt(singleProduct.findElement(quantity).getAttribute("value"));
        }else {
            return -1;
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
        List<WebElement> elements=driver.findElements(By.cssSelector(".cart_item"));
        List<String> names=new ArrayList<>();
        for (WebElement element:elements){
            names.add(getProductName(element));
        }
        return names.toString();
    }
    public double[] getProductsPriceFromCart(){
        List<WebElement> elements=driver.findElements(By.cssSelector(".cart_item"));
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
        driver.findElement(By.id("coupon_code")).sendKeys(coupon);
        driver.findElement(with(By.tagName("button")).toLeftOf(By.id("coupon_code"))).click();
//        baseElement.findElement(By.name("apply_coupon")).click();
        return this;
    }
    public CartPage removeProduct(int index){
        if (!checkIndex(index)){
            System.out.println("Wrong index");
            return null;
        }
        WebElement singleProduct=driver.findElement(By.xpath("(//tr[contains(@class,'cart_item')])["+index+"]"));
        singleProduct.findElement(remove).click();
        return this;
    }
    public CartPage undoRemoveProduct(){
       try {
           getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".restore-item"))).click();
           waitForMilliseconds(1500);
       } catch (Exception e) {
           System.out.println("No product removed to undo the remove action");
           e.printStackTrace();
       }
       return this;
    }




}
