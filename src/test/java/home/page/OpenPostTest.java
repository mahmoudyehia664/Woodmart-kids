package home.page;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.PostPage;

import static org.testng.Assert.assertTrue;

public class OpenPostTest extends BaseTest {
    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(5)
    public void openPost(String postName){
        PostPage postPage=homePage.openPostPage(postName);
//        System.out.println(postPage.getPageURL());
//        System.out.println(postName.toLowerCase().replace(" &","").replace(" ","-")+"/");
        assertTrue(postPage.getPageURL().endsWith(postName.toLowerCase().replace(" &","").replace(" ","-")+"/"),"Incorrect functionality");
    }
}
