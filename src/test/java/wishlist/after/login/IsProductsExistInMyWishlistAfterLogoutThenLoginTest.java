package wishlist.after.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class IsProductsExistInMyWishlistAfterLogoutThenLoginTest extends BaseTest {
    @Test(priority = 2,dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(4)
    public void isProductsExistInMyWishlistAfterLogoutThenLogin(String productName1, String productName2){
        homePage.loginUsingCookie();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        synchronized (this){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty(),"Incorrect functionality(No products in the wishlist)");
        String names=wishlistPage.getAllProductsNameInTheWishlistPage();
        assertTrue((names.contains(productName1) && names.contains(productName2)),"Incorrect functionality");
    }
}
