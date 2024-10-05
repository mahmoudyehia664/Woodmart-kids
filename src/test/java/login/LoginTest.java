package login;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(1)
    public void login(String userNameOrEmail, String password, String result){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        loginAndRegisterPage.login(userNameOrEmail,password);
        assertEquals(loginAndRegisterPage.checkRegisterOrLoginSuccess(),result,"Incorrect result");
        loginAndRegisterPage.header.logout();
    }
}
