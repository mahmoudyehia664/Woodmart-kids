package privacy.page;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertTrue;

public class OpenPrivacyPageWhileRegisterTest extends BaseTest {
    @Test
    public void openPrivacyPageWhileRegister(){
        SoftAssert softAssert = new SoftAssert();
        String originalWindow = driver.getWindowHandle();
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        softAssert.assertTrue(loginAndRegisterPage.openPrivacyPage().isFound(),"Page Not Found");
        driver.close();
        driver.switchTo().window(originalWindow);
        softAssert.assertAll();
    }
}
