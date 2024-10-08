package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginAndRegisterPage extends AbstractPage{
    By login_userNameInput=By.id("username");
    By login_passwordInput=By.id("password");
    By login_button=By.cssSelector(".col-login button");
    By lostPassword=By.className("lost_password");
    By register_userNameInput=By.id("reg_username");
    By register_emailInput=By.id("reg_email");
    By register_passwordInput=By.id("reg_password");
    By register_button=By.cssSelector(".col-register button");
    By switch_button=By.cssSelector(".wd-switch-to-register");
    public LoginAndRegisterPage(WebDriver driver){
        super(driver);
    }

    private void switchTo(String text){
        if(!driver.findElement(By.cssSelector(".col-"+text)).isDisplayed()){
            driver.findElement(switch_button).click();
        }
    }
    public DashboardPage login(String userNameOrEmail,String password){
        switchTo("login");
        driver.findElement(login_userNameInput).sendKeys(userNameOrEmail);
        driver.findElement(login_passwordInput).sendKeys(password);
        driver.findElement(login_button).click();
        return new DashboardPage(driver);
    }
    public LostPasswordPage openLostPassword(){
        switchTo("login");
        driver.findElement(lostPassword).click();
        return new LostPasswordPage(driver);
    }
    public DashboardPage register(String userName,String email,String password){
        switchTo("register");
        driver.findElement(register_userNameInput).sendKeys(userName);
        driver.findElement(register_emailInput).sendKeys(email);
        driver.findElement(register_passwordInput).sendKeys(password);
        driver.findElement(register_button).click();
        return new DashboardPage(driver);
    }
    public String checkRegisterOrLoginSuccess(){
        try {
            return driver.findElement(By.cssSelector(".woocommerce-error li")).getText().replace("\n"," ");
        } catch (NoSuchElementException e) {
            return "Success";
        }
    }

    public PrivacyPage openPrivacyPage(){
        switchTo("register");
        driver.findElement(By.cssSelector(".woocommerce-privacy-policy-link")).click();
        return new PrivacyPage(driver);
    }
}