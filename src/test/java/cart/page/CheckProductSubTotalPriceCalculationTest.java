package cart.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginAndRegisterPage;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckProductSubTotalPriceCalculationTest extends BaseTest {

    @Test(priority = 1)
    public void checkProductSubTotalPriceCalculation(){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        CartPage cartPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/").header.openCartPage();
        assertTrue(cartPage.isFound(),"Page not found");
        assertTrue(cartPage.getPageURL().endsWith("cart/"),"Incorrect page opened");
        double productPrice= cartPage.getProductPrice(1);
        cartPage.dealWithQuantity(1).setQuantity(3);
        double actualTotalPrice= cartPage.getProductSubTotalPrice(1);
        double expectedTotalPrice=productPrice*cartPage.getProductQuantity(1);
        System.out.println("actual :"+actualTotalPrice);
        System.out.println("expected :"+expectedTotalPrice);
        assertEquals(actualTotalPrice,expectedTotalPrice,"Incorrect product total price calculation");
    }

}
