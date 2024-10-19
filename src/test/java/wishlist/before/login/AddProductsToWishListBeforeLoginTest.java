package wishlist.before.login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.WishlistPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddProductsToWishListBeforeLoginTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(4)
    public void addProductsToWishList(String productName1, String productName2){
        homePage.productCart.selectProduct(productName1).addToWishlist();
        homePage.productCart.selectProduct(productName2).addToWishlist();
        WishlistPage wishlistPage=homePage.header.openWishlistPage();
        assertTrue(wishlistPage.isFound(),"Page not found");
        assertTrue(wishlistPage.getPageURL().endsWith("wishlist/"),"Incorrect page opened");
        assertFalse(wishlistPage.isEmpty(),"Incorrect functionality(No products added to wishlist)");
    }
}
