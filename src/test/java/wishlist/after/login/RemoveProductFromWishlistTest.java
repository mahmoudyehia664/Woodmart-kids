package wishlist.after.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RemoveProductFromWishlistTest extends BaseTest {

    @Test(priority = 3, dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(4)
    public void removeProductFromWishlist(String productName1, String productName2){
        homePage.loginUsingCookie();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty(),"No products in the wishlist");
        wishlistPage.removeProductFromWishlist(productName1).removeProductFromWishlist(productName2);
        String names=wishlistPage.getAllProductsNameInTheWishlistPage();
        assertFalse((names.contains(productName1) && names.contains(productName2)),"Incorrect functionality");
    }

}
