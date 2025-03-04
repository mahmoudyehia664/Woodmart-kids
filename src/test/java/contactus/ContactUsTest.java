package contactus;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.ContactUsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactUsTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(6)
    public void contactUs(String firstName,String lastName,String email,String message,String expectedResult){
        ContactUsPage contactUsPage=(ContactUsPage)homePage.header.openPageFromLeftPartOfHeader(2);
        assertTrue(contactUsPage.isFound(),"Page Not Found");
        String actualResult=contactUsPage.getInTouch(firstName,lastName,email,message).checkMessageSentOrNot();
        assertEquals(actualResult,expectedResult,"Incorrect functionality"/* expected ("+ExpectedResult+") but found ("+actualResult+")"*/);
    }
}
