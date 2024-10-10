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
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        DashboardPage dashboardPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/");
        assertTrue(dashboardPage.isFound(),"Page not found");
        WishlistPage wishlistPage=dashboardPage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        wishlistPage.editWishlistGroupName("ITI","PROJECT_ITI");
        String names=wishlistPage.getWishlistGroupsNames();
        assertTrue(names.contains("PROJECT_ITI"),"Incorrect functionality");
    }
}
