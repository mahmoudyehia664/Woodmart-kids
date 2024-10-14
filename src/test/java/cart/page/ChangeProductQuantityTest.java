package cart.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ChangeProductQuantityTest extends BaseTest {

    @Test(priority = 3)
    public void changeProductQuantity(){
        homePage.loginUsingCookie();
        CartPage cartPage=homePage.header.openCartPage();
        assertTrue(cartPage.isFound(),"Page not found");
        assertTrue(cartPage.getPageURL().endsWith("cart/"),"Incorrect page opened");
        cartPage.dealWithQuantity(2).plusQuantity();
        assertEquals(cartPage.getProductQuantity(2),2,"Incorrect product total price calculation");
        cartPage.dealWithQuantity(2).minusQuantity();
    }


}
