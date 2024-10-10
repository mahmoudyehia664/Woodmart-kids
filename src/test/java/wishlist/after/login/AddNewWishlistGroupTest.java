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

public class AddNewWishlistGroupTest extends BaseTest {
    @Test(priority = 5,dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(7)
    public void addNewWishlistGroup(String productName1, String productName2){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        DashboardPage dashboardPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/");
        assertTrue(dashboardPage.isFound(),"Page not found");
        WishlistPage wishlistPage=dashboardPage.header.openWishlistPage();
        wishlistPage.addNewWishlistGroup("ITI").header.openHomePage();
//        wishlistPage.header.openHomePage();
        homePage.productCart.selectProduct(productName1).addToWishlist().addNewWishList("ITI_2").addToWishlistThenBackToShop("ITI_2");
        wishlistPage=homePage.productCart.selectProduct(productName2).addToWishlist().addToWishlistThenOpenWishlist("ITI");
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        String names=wishlistPage.getWishlistGroupsNames();
        assertTrue((names.contains("ITI") && names.contains("ITI_2")),"Incorrect functionality");
    }
}
