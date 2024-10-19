package wishlist.after.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddProductToCartFromWishlistTest extends BaseTest {
    @Test(priority = 8,dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(7)
    public void addProductToCartFromWishlist(String productName1, String productName2){
        homePage.loginUsingCookie();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
//        assertFalse(wishlistPage.isEmpty(),"Incorrect functionality(No products in the wishlist)");
        assertFalse(wishlistPage.isEmpty("ITI_3"),"Incorrect functionality(No products in the wishlist)");
        assertFalse(wishlistPage.isEmpty("ITI_4"),"Incorrect functionality(No products in the wishlist)");
//        wishlistPage.dealWithProduct("ITI_3",productName2).openProductPage();
//        wishlistPage.dealWithProduct(productName2).addSimpleProductToCart().continueShopping();
        wishlistPage.dealWithProduct("ITI_3",productName2).addSimpleProductToCart().continueShopping();
        CartPage cartPage=wishlistPage.dealWithProduct("ITI_4",productName1).addVariableProductToCart(1,"Gray",1).openCartPage();
        String names=cartPage.getProductsNamesFromCart();
        assertTrue((names.contains(productName1)&&names.contains(productName2)),"Incorrect functionality");
    }

}
