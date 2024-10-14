package filter.product;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ShopPage;

import static org.testng.Assert.assertEquals;

public class FilterProductByBrandTest extends BaseTest {

    @Test
    public void filterProductByBrand(){
        ShopPage shopPage=(ShopPage)homePage.header.openPageFromMiddlePartOfHeader("Shop");
        shopPage.applyFilter("Baby Bling");
        int size=shopPage.getBrandsOfAllProductsInThePage().size();
        assertEquals(size,1,"Incorrect functionality");
    }
}
