package wishlist.after.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginAndRegisterPage;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddProductsToWishListAfterLoginTest extends BaseTest {

    @Test(priority = 1,dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(4)
    public void addProductsToWishListAfterLogin(String productName1, String productName2){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        DashboardPage dashboardPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/");
        assertTrue(dashboardPage.isFound(),"Page not found");
        dashboardPage.header.openHomePage();
        homePage.productCart.selectProduct(productName1).addToWishlist().addToWishlistThenBackToShop();
        homePage.productCart.selectProduct(productName2).addToWishlist().addToWishlistThenBackToShop();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty(),"Incorrect functionality(No products added to wishlist)");
        String names=wishlistPage.getAllProductsNameInTheWishlistPage();
        assertTrue((names.contains(productName1) && names.contains(productName2)),"Incorrect functionality");
    }
}
