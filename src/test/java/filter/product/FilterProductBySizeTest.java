package filter.product;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ShopPage;

import java.util.TreeSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FilterProductBySizeTest extends BaseTest {

    @Test
    public void filterProductBySize(){
        ShopPage shopPage=(ShopPage)homePage.header.openPageFromMiddlePartOfHeader("Shop");
        shopPage.applyFilter("6 - 12 Months");
        TreeSet<String> sizes=shopPage.getSizesOfAllProductsInThePage();
        System.out.println(sizes);
        int size=sizes.size();
        assertEquals(size,1,"Incorrect functionality");
    }
}
