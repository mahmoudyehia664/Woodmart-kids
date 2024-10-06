package home.page;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BuyBundleCollectionTest extends BaseTest {

    @Test
    public void buyBundleCollection(){
        assertTrue(homePage.buyBundle().getPageURL().endsWith("checkout/"),"Incorrect functionality");
    }
}
