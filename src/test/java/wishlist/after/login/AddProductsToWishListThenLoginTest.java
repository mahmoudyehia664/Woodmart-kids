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

public class AddProductsToWishListThenLoginTest extends BaseTest {

    @Test(priority = 4,dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(4)
    public void addProductsToWishListThenLogin(String productName1, String productName2){
        homePage.productCart.selectProduct(productName1).addToWishlist();
        homePage.productCart.selectProduct(productName2).addToWishlist();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage) wishlistPage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(),"Page not found");
        assertTrue(loginAndRegisterPage.getPageURL().endsWith("my-account/"),"Incorrect page opened");
        DashboardPage dashboardPage=loginAndRegisterPage.login("iti","Jwcgdb/*8z#d+7/");
        assertTrue(dashboardPage.isFound(),"Page not found");
        wishlistPage=dashboardPage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty(),"Incorrect functionality(No products added to wishlist)");
        String names=wishlistPage.getAllProductsNameInTheWishlistPage();
        assertTrue((names.contains(productName1) && names.contains(productName2)),"Incorrect functionality");
    }
}
