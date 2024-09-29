package pages;

import org.openqa.selenium.WebDriver;
import page.component.Categories;
import page.component.ProductCart;

public class HomePage extends AbstractPage{
    public Categories categories;
    public ProductCart productCart;
    public HomePage(WebDriver driver){
        super(driver);
        categories=new Categories(driver);
        productCart=new ProductCart(driver);
    }
}
