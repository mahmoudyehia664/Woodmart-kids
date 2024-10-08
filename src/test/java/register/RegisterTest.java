package register;

import annotation.DataProviderIndex;
import base.BaseTest;
import com.google.common.io.Files;
import data.Data;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import pages.LoginAndRegisterPage;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "getData",dataProviderClass = Data.class)
    @DataProviderIndex(0)
    public void register(String userName,String email,String password,String expectedResult){
        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
        loginAndRegisterPage.register(userName,email,password);
        String actualResult=loginAndRegisterPage.checkRegisterOrLoginSuccess();
        assertEquals(actualResult,expectedResult,"Incorrect result");
        loginAndRegisterPage.header.logout();
    }
}
