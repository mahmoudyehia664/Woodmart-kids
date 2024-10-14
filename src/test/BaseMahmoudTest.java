import annotation.DataProviderIndex;
import com.google.common.io.Files;
import data.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginAndRegisterPage;

import java.io.File;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;


public class BaseMahmoudTest {
    public WebDriver driver;
    public HomePage homePage;

    @Test
    public void base(){
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        homePage=new HomePage(driver,"https://test-iti-testing-project-v1.pantheonsite.io/");
        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","mm|1729048011|ttSQ5zlnCJSjN5c2BYfMJ4aKEORIhwtbpSr2kHw8hw8|fd128b4881f74a308eb71e684c2c6edc2bcf0d2e70d7579951d005f2abd7452a"));
        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","mm%7C1729048565%7CJlfbhphy9YSgwKF83YANUOhtP6JWdUiEJgcCzD4F8xA%7Cafa497c86f54d2e4e5c11d82f9cca542fe1bf08f1c2249f7b47e17e72b2dc5e6"));
        driver.navigate().refresh();
        synchronized (this){
            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Clear local storage
        js.executeScript("window.localStorage.clear();");
        // Clear session storage
        js.executeScript("window.sessionStorage.clear();");
        // Clear cache
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
//        homePage.getAllPostTitles();
//        ShopPage shopPage=(ShopPage) homePage.header.openPageFromMiddlePartOfHeader("Shop");
//        shopPage.applyPriceFilter(15,423).applyFilter("Blue").applyFilter("MORI").applyFilter("0 - 3 Months")/*.header.openPageFromLeftPartOfHeader(1)*/;
//        System.out.println(shopPage.getAppliedFilters());
//        shopPage.categories.openCategoryPage("Toys");
//        LoginAndRegisterPage loginAndRegisterPage=(LoginAndRegisterPage)homePage.header.openMyAccountPage();
//        loginAndRegisterPage.login("mm","J9L16187asd+");
//        loginAndRegisterPage.header.logout();
//        System.out.println(loginAndRegisterPage.checkRegisterSuccess());
//        loginAndRegisterPage.openLostPassword().lostPassword("mb");
//        loginAndRegisterPage.register("mm","mm@gmail.com","J9L16187asd+");
//        System.out.println(driver.getCurrentUrl());
//        System.out.println(shopPage.filterSetMinPrice(100).filterSetMaxPrice(420).applyPriceFilter().applyColorFilter("Blue").getColorCount("Blue"));

//        homePage.productCart
//                .selectProduct(282)
//                .quickView
//                .openQuickView()
//                .closeQuickView();
//        System.out.println(homePage.productCart.selectProduct(226).getProductName());
//        System.out.println(Arrays.toString(homePage.productCart.selectProduct(282).getProductCategory()));
//        System.out.println(homePage.productCart.selectProduct(226).getProductBrand());
//        System.out.println(homePage.productCart.selectProduct(226).getProductPrice());
//        assertEquals(homePage.header.getWishlistContentNumberFromHeader(),"1","There is a problem in add to wishlist functionality");
//        homePage.productCart.selectProduct("3 Pack Baby Socks").openQueckView().setQuantity(10).addToCart().continueShopping();
//        homePage.productCart.selectProduct("3 Pack Baby Socks").addSimpleProductToCart().continueShopping();
//        homePage.productCart.selectProduct("Bunny Quilted Growsuit").addSimpleProductToCart().closPopupAddedToCart();
//        homePage.header.openDropDownCart().dealWithQuantity(2).plusQuantity();
//        homePage.header.openDropDownCart().dealWithQuantity(2).plusQuantity();
//        homePage.header.openDropDownCart().dealWithQuantity(2).minusQuantity();
//        homePage.header.openDropDownCart().dealWithQuantity(2).plusQuantity();
//        homePage.header.openDropDownCart().dealWithQuantity(2).minusQuantity();
//        homePage.header.openDropDownCart().openCheckoutPage();
//        System.out.println(homePage.header.openDropDownCart().dealWithQuantity(2).getQuantity());
//        System.out.println(homePage.header.openDropDownCart().getProductQuantity(2));
//        homePage.header.openDropDownCart().deleteProductFromCart(1);
//        homePage.header.openCartPage();
//        System.out.println(homePage.header.getCartContentNumberFromHeader());
//        System.out.println(homePage
//                .header
//                .openDropDownCart()
//                .getNumberOfProductsInTheCart());
//        System.out.println(homePage
//                .header
//                .openDropDownCart()
//                .getAllProductsDataFromMiniCart());
//        System.out.println(homePage
//                .header
//                .openDropDownCart()
//                .getAllProductsDataFromMiniCart());
//        homePage.closQuickView();
//        ShopPage shopPage= (ShopPage) homePage.header.openPageFromMiddlePartOfHeader("Shop");
//        shopPage.header.openPageFromMiddlePartOfHeader("Home");
//        homePage.categories.openCategoryPage("Toys");
//        homePage.header.changeCurrency(1);
//        System.out.println(homePage.header.sendTextToSearchFor("bunny"));
    }
}
