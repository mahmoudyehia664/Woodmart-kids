package lost.password;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;
import pages.LostPasswordPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LostPasswordTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(2)
    public void lostPassword(String userNameOrEmail,String expectedResult){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.isFound(), "Page Not Found");
        LostPasswordPage lostPasswordPage=loginAndRegisterPage.openLostPassword();
        assertTrue(lostPasswordPage.isFound(), "Page Not Found");
        assertEquals(lostPasswordPage.lostPassword(userNameOrEmail).checkLostPassword(),expectedResult,"Incorrect result");
    }
}
