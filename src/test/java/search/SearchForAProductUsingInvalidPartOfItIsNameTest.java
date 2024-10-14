package search;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class SearchForAProductUsingInvalidPartOfItIsNameTest extends BaseTest {
    @Test(priority = 3)
    public void searchForAProductUsingInvalidPartOfItIsName(){
        assertFalse(homePage.header.sendTextToSearchFor("iti"),"Incorrect Functionality");
    }
}
