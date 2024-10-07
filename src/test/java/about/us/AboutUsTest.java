package about.us;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.AboutUsPage;
import pages.ContactUsPage;

import static org.testng.Assert.assertTrue;

public class AboutUsTest extends BaseTest {

    @Test
    public void openAboutUsPage(){
        AboutUsPage aboutUsPage=(AboutUsPage) homePage.header.openPageFromLeftPartOfHeader(1);
        assertTrue(aboutUsPage.getPageURL().endsWith("about-us/"),"Incorrect page opened");
        assertTrue(aboutUsPage.isFound(),"Page not found");
        ContactUsPage contactUsPage=aboutUsPage.openContactUsPage();
        assertTrue(contactUsPage.getPageURL().endsWith("contact-us/"),"Incorrect page opened");
        assertTrue(contactUsPage.isFound(),"Page not found");
    }
}
