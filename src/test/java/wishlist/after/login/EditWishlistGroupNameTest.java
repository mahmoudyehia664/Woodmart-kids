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

public class EditWishlistGroupNameTest extends BaseTest {
    @Test(priority = 6)
    public void editWishlistGroupName(){
        homePage.loginUsingCookie();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        wishlistPage.editWishlistGroupName("MA","PROJECT_ITI");
        String names=wishlistPage.getWishlistGroupsNames();
        assertTrue(names.contains("PROJECT_ITI"),"Incorrect functionality");
    }
}
