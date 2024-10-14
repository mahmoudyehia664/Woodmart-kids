package search;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SearchForAProductUsingValidPartOfItIsNameTest extends BaseTest {
    @Test(priority = 2)
    public void searchForAProductUsingValidPartOfItIsName(){
        assertTrue(homePage.header.sendTextToSearchFor("3 Pack"),"Incorrect Functionality");
    }
}
