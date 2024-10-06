package lost.password;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;
import pages.LostPasswordPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LostPasswordTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(2)
    public void lostPassword(String userNameOrEmail,String result){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        assertTrue(loginAndRegisterPage.checkPageFoundOrNot(), "Page Not Found");
        LostPasswordPage lostPasswordPage=loginAndRegisterPage.openLostPassword();
        assertTrue(lostPasswordPage.checkPageFoundOrNot(), "Page Not Found");
        assertEquals(lostPasswordPage.lostPassword(userNameOrEmail).checkLostPassword(),result,"Incorrect result");
    }
}
