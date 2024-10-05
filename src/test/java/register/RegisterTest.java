package register;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.Data;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertEquals;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(0)
    public void register(String userName,String email,String password,String result){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        loginAndRegisterPage.register(userName,email,password);
        assertEquals(loginAndRegisterPage.checkRegisterOrLoginSuccess(),result,"Incorrect result");
        loginAndRegisterPage.header.logout();
    }
}
