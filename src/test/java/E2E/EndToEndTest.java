package E2E;

import base.BaseTest;
import data.FakerData;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {
    @Test(dataProvider = "fakeRegistrationData",dataProviderClass = FakerData.class)
    public void fullCycleInTheSite(String userName,String email,String password){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        DashboardPage dashboardPage=loginAndRegisterPage.register(userName,email,password);
//        DashboardPage dashboardPage=loginAndRegisterPage.register("ahmed12","ahmed12@gmail.com","302vbcvf*aszxsd");
        dashboardPage.header.openHomePage();
        homePage.productCart.selectProduct("3 Pack Baby Socks").openQuickView().addToCart().continueShopping();
        homePage.productCart.selectProduct("Small Rainbow Jumper").addVariableProductToCart(1,"Blue",1).continueShopping();
        ShopPage shopPage=(ShopPage)homePage.header.openPageFromMiddlePartOfHeader("Shop");
        CartPage cartPage=shopPage.applyPriceFilter(30,30).productCart.selectProduct("Fluffy Cloud Growsuit").addSimpleProductToCart().openCartPage();
        CheckoutPage checkoutPage=cartPage.openCheckoutPage();
        checkoutPage.addBillingDetails();
        checkoutPage.payWithPaypal();
    }
}
