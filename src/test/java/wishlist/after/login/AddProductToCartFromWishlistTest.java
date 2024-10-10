package wishlist.after.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DashboardPage;
import pages.LoginAndRegisterPage;
import pages.WishlistPage;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddProductToCartFromWishlistTest extends BaseTest {
    @Test(priority = 8,dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(7)
    public void addProductToCartFromWishlist(String productName1, String productName2){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        DashboardPage dashboardPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/");
        assertTrue(dashboardPage.isFound(),"Page not found");
        WishlistPage wishlistPage=dashboardPage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty("ITI"),"Incorrect functionality(No products in the wishlist)");
        assertFalse(wishlistPage.isEmpty("ITI_2"),"Incorrect functionality(No products in the wishlist)");
        wishlistPage.dealWithProduct("ITI",productName2).addSimpleProductToCart().continueShopping();
        CartPage cartPage=wishlistPage.dealWithProduct("ITI_2",productName1).addVariableProductToCart(1,"Gray",1).openCartPage();
        String names=cartPage.getProductsNamesFromCart();
        assertTrue((names.contains(productName1)&&names.contains(productName2)),"Incorrect functionality");
    }

}
