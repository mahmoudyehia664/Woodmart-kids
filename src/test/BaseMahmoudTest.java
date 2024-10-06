import annotation.DataProviderIndex;
import data.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginAndRegisterPage;

import java.util.Arrays;


public class BaseMahmoudTest {
    public WebDriver driver;
    public HomePage homePage;
    @Test
    public void base(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        homePage=new HomePage(driver,"https://test-iti-testing-project-v1.pantheonsite.io/");
        homePage.getAllPostTitles();
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
//        driver.quit();
    }
}
