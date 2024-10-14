package filter.product;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ShopPage;

import java.util.TreeSet;

import static org.testng.Assert.assertTrue;

public class FilterProductByColorTest extends BaseTest {

    @Test
    public void filterProductByColor(){
        ShopPage shopPage=(ShopPage)homePage.header.openPageFromMiddlePartOfHeader("Shop");
        shopPage.applyFilter("Yellow");
        TreeSet<String> colors=shopPage.getColorsOfAllProductsInThePage();
        System.out.println(colors);
        for (String color:colors){
            assertTrue(color.contains("Yellow"));
        }
    }
}
