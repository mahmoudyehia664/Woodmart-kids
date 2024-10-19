package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckoutPage extends AbstractPage{
    WebDriverWait waitCheckout;
    public CheckoutPage(WebDriver driver){
        super(driver);
        waitCheckout=new WebDriverWait(driver,Duration.ofSeconds(30));
    }
    public CheckoutPage addBillingDetails(){
        try {
            driver.findElement(By.id("billing_first_name")).clear();
            driver.findElement(By.id("billing_first_name")).sendKeys("ITI");
            driver.findElement(By.id("billing_last_name")).clear();
            driver.findElement(By.id("billing_last_name")).sendKeys("Project");
            driver.findElement(By.id("billing_address_1")).clear();
            driver.findElement(By.id("billing_address_1")).sendKeys("Smart village");
            waitForMilliseconds(1000);
            driver.findElement(By.id("billing_city")).clear();
            driver.findElement(By.id("billing_city")).sendKeys("cairo");
            waitForMilliseconds(1000);
            driver.findElement(By.id("billing_postcode")).clear();
            driver.findElement(By.id("billing_postcode")).sendKeys("53201");
            waitForMilliseconds(1000);
            driver.findElement(By.id("billing_phone")).clear();
            driver.findElement(By.id("billing_phone")).sendKeys("302156242");
            waitForMilliseconds(1000);
        } catch (Exception e) {
            System.out.println("You should login to checkout");
            throw new RuntimeException(e);
        }
        return this;
    }
    private void choosePaymentMethod(String paymentMethod){
        switch (paymentMethod.toLowerCase()){
            case "credit":
                driver.findElement(By.id("payment_method_woocommerce_payments")).click();
                waitForMilliseconds(500);
                break;
            case "cash":
                driver.findElement(By.id("payment_method_cod")).click();
//                waitForMilliseconds(500);
                break;
            case "paypal":
                driver.findElement(By.id("payment_method_ppcp-gateway")).click();
                waitForMilliseconds(500);
                break;
        }
    }
    private void acceptTerms(){
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.blockOverlay")));
       driver.findElement(By.id("terms")).click();
    }
    private void placeOrder(){
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.blockOverlay")));
        driver.findElement(By.id("place_order")).click();
        try {
            waitCheckout.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notice--success")));
        } catch (TimeoutException e) {
            System.out.println("No response after Waiting for 30 seconds");
            throw new RuntimeException(e);
        }
//        waitForMilliseconds(20000);
    }
    public OrderCompletePage payWithCashOnDelivery(){
        acceptTerms();
        choosePaymentMethod("cash");
        placeOrder();
        return new OrderCompletePage(driver);
    }
    public OrderCompletePage payWihCreditCard(String cardNumber,String expireDate,String cvc){
        acceptTerms();
        choosePaymentMethod("credit");
        driver.switchTo().frame(driver.findElement(By.cssSelector(".payment_method_woocommerce_payments iframe")));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Field-numberInput"))).sendKeys(cardNumber);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Field-expiryInput"))).sendKeys(expireDate);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Field-cvcInput"))).sendKeys(cvc);
        driver.switchTo().parentFrame();
        placeOrder();
        return new OrderCompletePage(driver);
    }

    public OrderCompletePage payWithPaypal(){
        acceptTerms();
        choosePaymentMethod("paypal");
        String mainWindow= driver.getWindowHandle();
        WebElement iframeElement = driver.findElement(By.cssSelector(".paypal-buttons iframe"));
        driver.switchTo().frame(iframeElement);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".paypal-button-label-container"))).click();
        waitForMilliseconds(6000);
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        String lastWindowHandle = windowHandlesList.get(windowHandlesList.size() - 1);
        driver.switchTo().window(lastWindowHandle);
        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
            driver.findElement(By.id("email")).sendKeys("sb-ojk3w29742864@personal.example.com");
            driver.findElement(By.id("btnNext")).click();
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log in with a password instead']"))).click();
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys("e#N&B.a0");
            driver.findElement(By.id("btnLogin")).click();
        } catch (TimeoutException e) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log in with a password instead']"))).click();
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys("sb-ojk3w29742864@personal.example.com");
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys("e#N&B.a0");
            driver.findElement(By.id("btnLogin")).click();
        }
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(By.id("payment-submit-btn"))).click();
            driver.switchTo().window(mainWindow);
        } catch (Exception e) {
            driver.close();
            driver.switchTo().window(mainWindow);
        }
//        waitForMilliseconds(20000);
        try {
            waitCheckout.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notice--success")));
        } catch (TimeoutException e) {
            System.out.println("No response after Waiting for 30 seconds");
            throw new RuntimeException(e);
        }
        return new OrderCompletePage(driver);
    }
}
