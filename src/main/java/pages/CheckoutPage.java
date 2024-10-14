package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends AbstractPage{
    public CheckoutPage(WebDriver driver){
        super(driver);
    }
    public CheckoutPage addBillingDetails(){
        driver.findElement(By.id("billing_first_name")).clear();
        driver.findElement(By.id("billing_first_name")).sendKeys("ITI");
        driver.findElement(By.id("billing_last_name")).clear();
        driver.findElement(By.id("billing_last_name")).sendKeys("Project");
        driver.findElement(By.id("billing_address_1")).clear();
        driver.findElement(By.id("billing_address_1")).sendKeys("Smart village");
        driver.findElement(By.id("billing_city")).clear();
        driver.findElement(By.id("billing_city")).sendKeys("cairo");
        driver.findElement(By.id("billing_postcode")).clear();
        driver.findElement(By.id("billing_postcode")).sendKeys("53201");
        driver.findElement(By.id("billing_phone")).clear();
        driver.findElement(By.id("billing_phone")).sendKeys("302156242");
        return this;
    }
    private void choosePaymentMethod(String paymentMethod){
        switch (paymentMethod.toLowerCase()){
            case "credit":
                driver.findElement(By.id("payment_method_woocommerce_payments")).click();
                break;
            case "cash":
                driver.findElement(By.id("payment_method_cod")).click();
                break;
            case "paypal":
                driver.findElement(By.id("payment_method_ppcp-gateway")).click();
                break;
        }
    }
    private void acceptTerms(){
        driver.findElement(By.id("terms")).click();
    }
    private void placeOrder(){
        driver.findElement(By.id("place_order")).click();
    }
    public OrderCompletePage payWithCashOnDelivery(){
        choosePaymentMethod("cash");
        placeOrder();
        return new OrderCompletePage(driver);
    }
    public OrderCompletePage payWihCreditCard(String cardNumber,String expireDate,String Cvc){
        choosePaymentMethod("credit");
        driver.findElement(By.id("Field-numberInput")).sendKeys(cardNumber);
        driver.findElement(By.id("Field-expiryInput")).sendKeys(expireDate);
        driver.findElement(By.id("Field-cvcInput")).sendKeys(Cvc);
        placeOrder();
        return new OrderCompletePage(driver);
    }

    public OrderCompletePage payWithPaypal(){
        choosePaymentMethod("paypal");
        acceptTerms();
        driver.findElement(By.cssSelector(".paypal-button-label-container")).click();
        String mailWindow= driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Log in with a password instead']")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("sb-ojk3w29742864@personal.example.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("e#N&B.a0");
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.id("payment-submit-btn")).click();
        return new OrderCompletePage(driver);
    }
}
