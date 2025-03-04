package home.page;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OpenCategoryPageTest extends BaseTest {
    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(3)
    public void openCategoryPage(String categoryName){
        assertTrue(homePage.categories.openCategoryPage(categoryName).getPageURL().endsWith("/product-category/"+categoryName.toLowerCase()+"/"),"Incorrect link to category page");
    }
}
