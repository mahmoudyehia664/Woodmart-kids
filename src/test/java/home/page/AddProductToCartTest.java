package home.page;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddProductToCartTest extends BaseTest {
    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(4)
    public void addProductToCartFromHomePage(String productName1,String productName2){
        homePage.productCart.selectProduct(productName1).addSimpleProductToCart().continueShopping();
        homePage.productCart.selectProduct(productName2).addSimpleProductToCart().continueShopping();
        assertEquals(homePage.header.openDropDownCart().getNumberOfProductsInTheCart(),2,"Incorrect number of products in the cart");
        assertEquals(homePage.header.openDropDownCart().getProductName(1),productName1,"Incorrect number of products in the cart");
        assertEquals(homePage.header.openDropDownCart().getProductName(2),productName2,"Incorrect number of products in the cart");
    }
}
