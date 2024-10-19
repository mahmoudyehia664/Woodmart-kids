package wishlist.after.login;

import base.BaseTest;
import org.testng.annotations.Test;
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
