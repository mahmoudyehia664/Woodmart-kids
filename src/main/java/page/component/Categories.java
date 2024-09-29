package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CategoryPage;

public class Categories {
    private final WebDriver driver;
    public Categories(WebDriver _driver){
        driver=_driver;
    }
    public CategoryPage openCategoryPage(String linkText){
        driver.findElement(By.linkText(linkText)).click();
        return new CategoryPage(driver);
    }
}
