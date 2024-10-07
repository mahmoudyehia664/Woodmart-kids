package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.CategoryPage;

public class Categories {
    private final WebDriver driver;
    public Categories(WebDriver _driver){
        driver=_driver;
    }
    public CategoryPage openCategoryPage(String linkText){
        if (driver.getCurrentUrl().equals("https://test-iti-testing-project-v1.pantheonsite.io/")){
            new Actions(driver).scrollByAmount(0,730).perform();
//            new Actions(driver).scrollToElement(driver.findElement(By.cssSelector(".wd-fontsize-xs"))).perform();
        }
        driver.findElement(By.linkText(linkText)).click();
        return new CategoryPage(driver);
    }
}
