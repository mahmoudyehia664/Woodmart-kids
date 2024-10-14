package filter.product;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ShopPage;

import static org.testng.Assert.assertEquals;

public class FilterProductByPriceTest extends BaseTest {

    @Test
    public void filterProductByPrice(){
        ShopPage shopPage=(ShopPage)homePage.header.openPageFromMiddlePartOfHeader("Shop");
        shopPage.applyPriceFilter(30,30);
        int size=shopPage.getPricesOfAllProductsInThePage().size();
        assertEquals(size,1,"Incorrect functionality");
    }
}
