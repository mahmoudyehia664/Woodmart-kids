package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;
import java.util.List;
import java.util.StringTokenizer;

public class Header {
    private final WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private final By loginText=By.cssSelector("[title='My account'] .wd-tools-text");
    private final By wishlist=By.cssSelector("[title='Wishlist products']");
    private final By wishlistCount=By.cssSelector("[title='Wishlist products'] .wd-tools-count");
    private final By compare=By.cssSelector("[title='Compare products'] .wd-tools-count");
    private final By cartProduct =By.cssSelector("[title='Shopping cart']");
    private final By cartProductCount =By.cssSelector("[title='Shopping cart'] .wd-cart-number");

    public Header(WebDriver _driver){
        driver=_driver;
        actions=new Actions(driver);
    }

    public void openHomePage(){
        driver.findElement(By.cssSelector(".wd-main-logo")).click();
    }
    /**
     *
     * @param index start from 1 to 3
     * @return Object from class AboutUsPage when index = 1 or from class ContactUsPage when index = 2 or from class BlogPage when index = 3
     */
    public Object openPageFromLeftPartOfHeader(int index){
        switch (index){
            case 1:
                driver.findElement(By.xpath("(//ul[@id='menu-home-menu-kids-light-icon']//a)[1]")).click();
                return new AboutUsPage(driver);
            case 2:
                driver.findElement(By.xpath("(//ul[@id='menu-home-menu-kids-light-icon']//a)[2]")).click();
                return new ContactUsPage(driver);
            case 3:
                driver.findElement(By.xpath("(//ul[@id='menu-home-menu-kids-light-icon']//a)[3]")).click();
                return new BlogPage(driver);
        }
        System.out.println("index must equal one of this (1,2,3)");
        return null;
    }

    /**
     *
     * @param linkText receive the linkText for a page to open it
     * @return object of the page that the linkText refer to
     */
    public Object openPageFromMiddlePartOfHeader(String linkText){
//        String xpath="//ul[@id='menu-main-navigation']//span[.='"+linkText+"']";
//        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.linkText(linkText)).click();
        try {
            return Class.forName("pages."+linkText+"Page").getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void openSearchScreen(){
        driver.findElement(By.className("wd-header-search")).click();
    }
    private void closeSearchScreen(){
        driver.findElement(By.cssSelector("[aria-label='Close search form']")).click();
    }
    public boolean sendTextToSearchFor(String textToSearchFor){
        openSearchScreen();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("wd-search-inited"))).sendKeys(textToSearchFor);
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".suggestion-content strong")));
        for (WebElement element:elements){
            if(!element.getText().toLowerCase().contains(textToSearchFor)){
                closeSearchScreen();
                return false;
            }
        }
        closeSearchScreen();
        return true;
    }

    /**
     *
     * @param userName
     * @return true --> user logged in , false --> user not logged in
     */
    public boolean isUserLogin(String userName){
        if (userName.isEmpty()){
            return false;
        }
        return driver.findElement(loginText).getText().endsWith(userName);
    }
    public Object openMyAccountPage(){
        if (driver.findElement(loginText).getText().equals("Login / Register")){
            driver.findElement(loginText).click();
            return new LoginAndRegisterPage(driver);
        }else {
            return new DashboardPage(driver);
        }
    }

    /**
     *
     * @param partName the exact name of the page you want open that appear in the header
     * @return object from the page class that you select
     */
    public Object openOneOfMyAccountParts(String partName){
        if(driver.findElement(loginText).getText().equals("Login / Register")){
            System.out.println("You must login to show your account details");
            return null;
        }else{
            actions.moveToElement(driver.findElement(loginText)).perform();
            wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'wd-dropdown-my-account')]//span[.='"+partName+"']"))).click();
            StringBuilder test=new StringBuilder();
            StringTokenizer tokenizer = new StringTokenizer(partName, " ");
            while (tokenizer.hasMoreTokens()){
                StringBuilder capitalizedToken = new StringBuilder(tokenizer.nextToken());
                capitalizedToken.setCharAt(0, Character.toUpperCase(capitalizedToken.charAt(0)));
                test.append(capitalizedToken);
            }
            try {
                return Class.forName("pages."+test+"Page").getDeclaredConstructor(WebDriver.class).newInstance(driver);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public LoginAndRegisterPage logout(){
        if(driver.findElement(loginText).getText().equals("Login / Register")){
            System.out.println("You must login to be able to logout");
            return null;
        }else{
            actions.moveToElement(driver.findElement(loginText)).perform();
            wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'wd-dropdown-my-account')]//span[.='Logout']"))).click();
            return new LoginAndRegisterPage(driver);
        }
    }

    public WishlistPage openWishlistPage(){
        driver.findElement(wishlist).click();
        return new WishlistPage(driver);
    }
    public String getWishlistContentNumberFromHeader(){
        return driver.findElement(wishlistCount).getText();
    }

    public CompareProductsPage openCompareProductsPage(){
        driver.findElement(compare).click();
        return new CompareProductsPage(driver);
    }
    public String getCompareProductsContentNumberFromHeader(){
        return driver.findElement(compare).getText();
    }

    public CartPage openCartPage(){
        driver.findElement(cartProduct).click();
        return new CartPage(driver);
    }
    public String getCartContentNumberFromHeader(){
        return driver.findElement(cartProductCount).getText();
    }
    public DropdownCartFromHeader openDropDownCart(){
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.endsWith("/cart/") || currentUrl.endsWith("/checkout/")) {
            System.out.println("Mini cart not working in this page");
            return null;
        }
        return new DropdownCartFromHeader(driver);
    }


    /**
     *
     * @param index 0-->USD , 1-->EGP , 2-->EUR
     */
    public void changeCurrency(int index){
        Select currency=new Select(driver.findElement(By.cssSelector("[name='currency']")));
        currency.selectByIndex(index);
    }
}
