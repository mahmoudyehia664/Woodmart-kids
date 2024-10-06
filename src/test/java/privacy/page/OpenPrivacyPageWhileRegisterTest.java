package privacy.page;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertTrue;

public class OpenPrivacyPageWhileRegisterTest extends BaseTest {
    @Test
    public void openPrivacyPageWhileRegister(){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.openPrivacyPage().checkPageFoundOrNot(),"Page Not Found");
    }
}
