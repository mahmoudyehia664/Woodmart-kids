package search;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SearchForAProductUsingItIsNameTest extends BaseTest {

    @Test(priority = 1)
    public void searchForAProductUsingItIsName(){
        homePage.header.searchForProductUsingItIsExactlyName("CFrangipani Embroidered Dress");
        assertTrue(driver.getCurrentUrl().contains("CFrangipani Embroidered Dress".toLowerCase().replace(" ","-")),"Incorrect functionality");
    }
}
