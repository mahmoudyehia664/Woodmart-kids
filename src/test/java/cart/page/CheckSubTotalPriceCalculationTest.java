package cart.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DashboardPage;
import pages.LoginAndRegisterPage;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckSubTotalPriceCalculationTest extends BaseTest {


    @Test
    public void checkSubTotalPriceCalculation(){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        CartPage cartPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/").header.openCartPage();
        assertTrue(cartPage.isFound(),"Page not found");
        assertTrue(cartPage.getPageURL().endsWith("cart/"),"Incorrect page opened");
        double expectedSum= Arrays.stream(cartPage.getProductsPriceFromCart()).sum();
        double actualSum= cartPage.getTotalPriceOfProducts();
        System.out.println(expectedSum);
        System.out.println(actualSum);
        synchronized (this){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertEquals(actualSum,expectedSum,"Incorrect total price calculation");
    }

}
