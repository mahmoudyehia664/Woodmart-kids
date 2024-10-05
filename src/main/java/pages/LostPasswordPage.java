package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LostPasswordPage extends AbstractPage{
    By userNameInput=By.id("user_login");
    public LostPasswordPage(WebDriver driver){
        super(driver);
    }

    public LostPasswordPage lostPassword(String userNameOrEmail){
        driver.findElement(userNameInput).sendKeys(userNameOrEmail);
        driver.findElement(By.cssSelector("[value='Reset password']")).click();
        return this;
    }
}
