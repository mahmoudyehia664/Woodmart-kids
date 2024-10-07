package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactUsPage extends AbstractPage{
    public ContactUsPage(WebDriver driver){
        super(driver);
    }

    public ContactUsPage getInTouch(String firstName,String lastName,String email,String message){
        driver.findElement(By.cssSelector("[placeholder='First name']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("[placeholder='Last name']")).sendKeys(lastName);
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[placeholder='Your Message']")).sendKeys(message);
        getActions().scrollByAmount(0,100).perform();
        synchronized (this) {
            try {
                wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        driver.findElement(By.cssSelector(".wpcf7-submit")).click();
        return this;
    }

    public String checkMessageSentOrNot(){
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wpcf7-response-output"))).getText();
//        return driver.findElement(By.cssSelector(".wpcf7-response-output")).getText();
    }
}
