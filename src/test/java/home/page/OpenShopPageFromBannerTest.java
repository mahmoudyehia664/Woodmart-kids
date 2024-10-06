package home.page;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OpenShopPageFromBannerTest extends BaseTest {
    @Test
    public void openShopPageFromBanner(){
        assertTrue(homePage.openShopPageFromBanner().getPageURL().endsWith("shop/"),"Incorrect link to shop page");
    }
}
