import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;


public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    @Test
    public void base(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://test-iti-testing-project-v1.pantheonsite.io/");
        homePage=new HomePage(driver);
        homePage.productCart
                .selectProduct(282)
                .quickView
                .openQuickView()
                .closeQuickView();
//        System.out.println(homePage.productCart.selectProduct(226).getProductName());
//        System.out.println(Arrays.toString(homePage.productCart.selectProduct(282).getProductCategory()));
//        System.out.println(homePage.productCart.selectProduct(226).getProductBrand());
//        System.out.println(homePage.productCart.selectProduct(226).getProductPrice());
//        assertEquals(homePage.header.getWishlistContentNumberFromHeader(),"1","There is a problem in add to wishlist functionality");
//        homePage.productCart.selectProduct(226).addToWishlist();
//        ShopPage shopPage= (ShopPage) homePage.header.openPageFromMiddlePartOfHeader("Shop");
//        shopPage.header.openPageFromMiddlePartOfHeader("Home");
//        homePage.categories.openCategoryPage("Toys");
//        homePage.header.changeCurrency(1);
//        System.out.println(homePage.header.sendTextToSearchFor("bunny"));
//        driver.quit();
    }
}
