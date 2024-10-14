package cart.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UndoRemoveProductFromCartTest extends BaseTest {

    @Test(priority = 5)
    public void undoRemoveProductFromCart(){
        homePage.loginUsingCookie();
        CartPage cartPage=homePage.header.openCartPage();
        assertTrue(cartPage.isFound(),"Page not found");
        assertTrue(cartPage.getPageURL().endsWith("cart/"),"Incorrect page opened");
        int numberBeforeRemove=cartPage.getNumberOfProductsInTheCart();
        cartPage.removeProduct(3);
        cartPage.undoRemoveProduct();
        int numberAfterUndo=cartPage.getNumberOfProductsInTheCart();
        assertEquals(numberAfterUndo,numberBeforeRemove,"Incorrect Functionality");
    }


}
