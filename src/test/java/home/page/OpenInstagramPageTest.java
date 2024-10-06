package home.page;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OpenInstagramPageTest extends BaseTest {
    @Test
    public void openInstagramPage(){
        homePage.openInstagramPage();
        assertEquals(homePage.getPageURL(),"https://www.instagram.com/babyshop/","Incorrect instagram page");
    }
}
