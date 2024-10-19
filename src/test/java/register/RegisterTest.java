package register;

import annotation.DataProviderIndex;
import base.BaseTest;
import data.ExcelData;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.assertEquals;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = ExcelData.class)
    @DataProviderIndex(0)
    public void register(String userName,String email,String password,String expectedResult){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        loginAndRegisterPage.register(userName,email,password);
        String actualResult=loginAndRegisterPage.checkRegisterOrLoginSuccess();
        assertEquals(actualResult,expectedResult,"Incorrect result");
        loginAndRegisterPage.header.logout();
    }
}
