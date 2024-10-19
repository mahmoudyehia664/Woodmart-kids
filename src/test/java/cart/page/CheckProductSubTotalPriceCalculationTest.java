package cart.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginAndRegisterPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckProductSubTotalPriceCalculationTest extends BaseTest {

    @Test(priority = 1)
    public void checkProductSubTotalPriceCalculation(){
        homePage.loginUsingCookie();
        CartPage cartPage=homePage.header.openCartPage();
        assertTrue(cartPage.isFound(),"Page not found");
        assertTrue(cartPage.getPageURL().endsWith("cart/"),"Incorrect page opened");
        double productPrice= cartPage.getProductPrice(1);
        cartPage.dealWithQuantity(1).setQuantity(3);
        double actualTotalPrice= cartPage.getProductSubTotalPrice(1);
        double expectedTotalPrice=productPrice*cartPage.getProductQuantity(1);
        /**********/

//        double expectedTotalPrice = productPrice * cartPage.getProductQuantity(6);
//        BigDecimal bd = new BigDecimal(expectedTotalPrice).setScale(2, RoundingMode.HALF_UP);
//        expectedTotalPrice = bd.doubleValue();

        /**********/
        System.out.println("actual :"+actualTotalPrice);
        System.out.println("expected :"+expectedTotalPrice);
        assertEquals(actualTotalPrice,expectedTotalPrice,"Incorrect product total price calculation");
    }

}
