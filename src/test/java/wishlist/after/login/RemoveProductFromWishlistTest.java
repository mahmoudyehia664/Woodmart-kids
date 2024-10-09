package wishlist.after.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginAndRegisterPage;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RemoveProductFromWishlistTest extends BaseTest {

    @Test(dependsOnGroups = {"IsProductsExistInMyWishlistAfterLogoutThenLoginTest.isProductsExistInMyWishlistAfterLogoutThenLogin"}, dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(4)
    public void removeProductFromWishlist(String productName1, String productName2){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        DashboardPage dashboardPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/");
        WishlistPage wishlistPage=dashboardPage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty(),"No products in the wishlist");
        wishlistPage.removeProductFromWishlist(productName1).removeProductFromWishlist(productName2);
        String names=wishlistPage.getAllProductsNameInTheWishlistPage();
        assertFalse((names.contains(productName1) && names.contains(productName2)),"Incorrect functionality");
        wishlistPage.header.logout();
    }

}
