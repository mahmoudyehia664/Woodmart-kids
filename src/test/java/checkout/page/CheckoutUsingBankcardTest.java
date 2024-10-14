package checkout.page;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.CheckoutPage;

import static org.testng.Assert.assertTrue;

public class CheckoutUsingBankcardTest extends BaseTest {
    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(4)
    public void checkoutUsingBankcard(String productName1,String productName2){
        homePage.loginUsingCookiesBvn();
        homePage.productCart.selectProduct(productName1).addSimpleProductToCart().continueShopping();
        CheckoutPage checkoutPage=homePage.productCart.selectProduct(productName2).addSimpleProductToCart().openCartPage().openCheckoutPage();
        checkoutPage.addBillingDetails().payWihCreditCard("4242 4242 4242 4242","10/29","532");
        synchronized (this){
            try {
                wait(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertTrue(driver.getCurrentUrl().contains("order-received"),"Incorrect functionality");
    }
}
