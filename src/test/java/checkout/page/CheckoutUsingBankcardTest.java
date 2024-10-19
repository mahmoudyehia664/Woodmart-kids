package checkout.page;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.CheckoutPage;

import static org.testng.Assert.assertTrue;

public class CheckoutUsingBankcardTest extends BaseTest {
    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(4)
    public void checkoutUsingBankcard(String productName1,String productName2){
        homePage.loginUsingCookiesAhmed1();
//        homePage.productCart.selectProduct(productName1).addSimpleProductToCart().continueShopping();
        homePage.productCart.selectProduct(productName1).openQuickView().addToCart().continueShopping();
        CheckoutPage checkoutPage=homePage.productCart.selectProduct(productName2).addSimpleProductToCart().openCartPage().openCheckoutPage();
        checkoutPage.addBillingDetails().payWihCreditCard("4242 4242 4242 4242","10/29","532");
        assertTrue(driver.getCurrentUrl().contains("order-received"),"Incorrect checkout functionality");
    }
}
