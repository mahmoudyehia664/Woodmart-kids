package filter.product;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ShopPage;

import java.util.TreeSet;

import static org.testng.Assert.assertFalse;

public class FilterProductsByPriceRangeTest extends BaseTest {

    @Test
    public void filterProductsByPriceRange(){
        ShopPage shopPage=(ShopPage)homePage.header.openPageFromMiddlePartOfHeader("Shop");
        shopPage.applyPriceFilter(20,30);
        TreeSet<Double> prices=shopPage.getPricesOfAllProductsInThePage();
        Double min = prices.first();
        Double max = prices.last();
        if (min < 20 || max > 30){
            assertFalse(true,"Incorrect functionality");
        }
    }
}
