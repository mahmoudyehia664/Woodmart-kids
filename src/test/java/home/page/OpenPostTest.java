package home.page;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.PostPage;

import static org.testng.Assert.assertEquals;

public class OpenPostTest extends BaseTest {
    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(5)
    public void openPost(String postName){
        PostPage postPage=homePage.openPostPage(postName);
        assertEquals(postPage.postTitle(),postName,"Incorrect functionality");
    }
}
