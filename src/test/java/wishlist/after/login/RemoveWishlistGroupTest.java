package wishlist.after.login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginAndRegisterPage;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RemoveWishlistGroupTest extends BaseTest {
    @Test(priority = 7)
    public void removeWishlistGroupTest(/*String wishlistGroupName*/){
        homePage.loginUsingCookie();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        wishlistPage.removeWishlistGroup("PROJECT_ITI_1");
        String names=wishlistPage.getWishlistGroupsNames();
        assertFalse(names.contains("PROJECT_ITI_1"),"Incorrect functionality");
    }
}
