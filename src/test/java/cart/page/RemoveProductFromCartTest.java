package cart.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RemoveProductFromCartTest extends BaseTest {

    @Test(priority = 4)
    public void removeProductFromCart(){
        homePage.loginUsingCookie();
        CartPage cartPage=homePage.header.openCartPage();
        assertTrue(cartPage.isFound(),"Page not found");
        assertTrue(cartPage.getPageURL().endsWith("cart/"),"Incorrect page opened");
        cartPage.removeProduct(3);
        assertTrue(cartPage.isProductRemoved(),"Incorrect Functionality");
    }


}
