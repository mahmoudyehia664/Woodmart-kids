package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostPage extends AbstractPage{
    public PostPage(WebDriver driver){
        super(driver);
    }
    public String postTitle(){
        return driver.findElement(By.tagName("h1")).getText();
    }
}
