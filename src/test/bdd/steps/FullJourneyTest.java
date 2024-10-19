package bdd.steps;

import base.BaseTest;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.component.PopupAddedToCart;
import pages.LoginAndRegisterPage;

import static org.testng.Assert.*;

public class FullJourneyTest extends BaseTest{

    static Faker faker=new Faker();
    static String userName =faker.name().username();
    static String email=faker.internet().emailAddress();
    static String password=faker.regexify("[A-Za-z0-9_+=]{12}");
    LoginAndRegisterPage loginAndRegisterPage;
    PopupAddedToCart popupAddedToCart=null;


    @Given("the visitor in any page")
    public void the_visitor_in_any_page() {
        assertFalse(homePage.header.isUserLogin(),"This is a user and logged in");
        assertTrue(driver.getCurrentUrl().contains("https://test-iti-testing-project-v1.pantheonsite.io/"),"the website not opened");
    }
    @When("the visitor open login and register page from header")
    public void the_visitor_open_login_and_register_page_from_header() {
        if (!driver.getCurrentUrl().equals("https://test-iti-testing-project-v1.pantheonsite.io/my-account/")){
            loginAndRegisterPage= (LoginAndRegisterPage) homePage.header.openMyAccountPage();
        }
    }
    @When("register with valid data")
    public void register_with_valid_data() {
        loginAndRegisterPage.register(userName,email,password);
    }
    @Then("redirected to dashboard page")
    public void redirected_to_dashboard_page() {
        assertTrue(homePage.header.isUserLogin(),"Incorrect functionality ,Dashboard page not opened");
    }

    @Given("the user in the home page")
    public void the_user_in_the_home_page() {
        homePage.header.openHomePage();
    }
    @When("the user select a product and add it to cart")
    public void the_user_select_a_product_and_add_it_to_cart() {
        popupAddedToCart=homePage.productCart.selectProduct("Fruity Embroidered Dress").addSimpleProductToCart();
    }
    @Then("popup appear ask for open cart page or continue shopping")
    public void popup_appear_ask_for_open_cart_page_or_continue_shopping() {
        assertNotNull(popupAddedToCart, "Incorrect functionality , Popup up not appear");
        popupAddedToCart.continueShopping();
    }
    @Then("the product added to cart")
    public void the_product_added_to_cart() {
        String names=homePage.header.openDropDownCart().getProductName(1);
        assertEquals(names, "Fruity Embroidered Dress","Incorrect functionality , Product not added to cart");
    }

    @Given("the user in any page")
    public void the_user_in_any_page() {
        assertTrue(homePage.header.isUserLogin(),"This is a visitor not user");
        assertTrue(driver.getCurrentUrl().contains("https://test-iti-testing-project-v1.pantheonsite.io/"),"the website not opened");
    }
    @When("the user clicked logout from the header")
    public void the_user_clicked_logout_from_the_header() {
        homePage.header.logout();
    }
    @Then("user logged out")
    public void user_logged_out() {
        assertFalse(homePage.header.isUserLogin(),"User still logged in");
    }
    @Then("redirected to Login and Register page")
    public void redirected_to_login_and_register_page() {
        assertEquals(driver.getCurrentUrl(),"https://test-iti-testing-project-v1.pantheonsite.io/my-account/","Incorrect functionality , Login and register page not opened");
    }

    @When("login with valid data")
    public void login_with_valid_data() {
        if (loginAndRegisterPage==null){
            loginAndRegisterPage=new LoginAndRegisterPage(driver);
        }
        loginAndRegisterPage.login(userName,password);
    }

}
