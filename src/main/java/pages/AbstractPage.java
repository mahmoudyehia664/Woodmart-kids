package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.component.Header;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
    public Header header;

    public AbstractPage(WebDriver _driver){
        driver=_driver;
        header=new Header(driver);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected WebDriverWait getWait(){
        return wait;
//        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected JavascriptExecutor getJs(){
        return (JavascriptExecutor)driver;
    }
    protected Actions getActions(){
        return new Actions(driver);
    }
    public void closQuickView(){
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
    }

    /**
     *
     * @return true --> page found      false --> page not found
     */
    public boolean isFound(){
        try {
            return !driver.findElement(By.cssSelector(".page-header h3")).getText().equals("NOT FOUND");
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    public String getPageTitle(){
        return driver.getTitle();
    }
    public String getPageURL(){
        return driver.getCurrentUrl();
    }
    public void waitForMilliseconds(long milliseconds) {
        synchronized (this) {
            try {
                wait(milliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loginUsingCookie(){
//        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","iti%1729074502|erhsWt4iHC34cp23cfnm21TSDX7Wivbn13zlWY1M3Ir|e95818810815e4213725fb206bf985b0b40c4c50c88a3b1fe9a017c53b1ba9c3"));
//        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","iti%1729074502|erhsWt4iHC34cp23cfnm21TSDX7Wivbn13zlWY1M3Ir|7b429f6ec05f0211d5058be1ef656a200d34a5b5d4c9ffe8b1551edac02c5473"));
        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","iti%7C1729269768%7CWD9LSYI9wzagynGD0D6BH2roLVip7JlbaIIRVsKAIXs%7Cd3e9e771dd0e1ba23c7dc2f161753c64799796ae0f4b9884b40047f89fad32c1"));
        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","iti%7C1729269768%7CWD9LSYI9wzagynGD0D6BH2roLVip7JlbaIIRVsKAIXs%7C593f4d7f2684fc108a8f0d74d6835f678eeefacac515c507990092404f7abaf9"));
    }
    public void loginUsingCookiesBvn(){
        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","bvn%7C1729101809%7Cu6CPAAxb7I2imn149JAgWdVZi4JnDL7Ww1EaWPIvIbR%7Cd472a08d13128650e3ef68604b089f7b560368f72cff3c33d0114aa053296c37"));
        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","bvn%7C1729101809%7Cu6CPAAxb7I2imn149JAgWdVZi4JnDL7Ww1EaWPIvIbR%7C8e0caaa74f9b3716ab0f2df2fb4974c24426f6ac6fc6f22d771bc21971555c63"));
    }
    public void loginUsingCookiesAhmed1(){
        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","ahmed2%7C1729222407%7COr2uRdLUZ6K3KGmUCmcYyULw9H7ydBYddDOTEbjA7po%7C5bc86d58114e68d3d85b2971c3c1c05a6f805c33d5d906102db30bb4b4ffd7d5"));
        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","ahmed2%7C1729222407%7COr2uRdLUZ6K3KGmUCmcYyULw9H7ydBYddDOTEbjA7po%7C410a48562f6e8bd2177c86fe2337c3da0d3f29cddb3caf7217fbabc38e58be52"));

//        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","ahmed1%7C1729221383%7C5d1tzqFfknOKeNIUkAWkVbxaq1DNl3K7WkTPvaalplf%7C213404506e1ef89466e07fdabf04af86d9aacfc0d5ae72264e1751e2563705aa"));
//        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","ahmed1%7C1729221383%7C5d1tzqFfknOKeNIUkAWkVbxaq1DNl3K7WkTPvaalplf%7C16c7942bcf3bea7e002bb284a3597546bdbaf98e6667a41730e25015a5391bfc"));
    }
    public void loginUsingCookiesAhmed2(){
        driver.manage().addCookie(new Cookie("wordpress_logged_in_8f950562d6567b9e4af60820f404d3c0","ahmed2%7C1729222407%7COr2uRdLUZ6K3KGmUCmcYyULw9H7ydBYddDOTEbjA7po%7C5bc86d58114e68d3d85b2971c3c1c05a6f805c33d5d906102db30bb4b4ffd7d5"));
        driver.manage().addCookie(new Cookie("wordpress_sec_8f950562d6567b9e4af60820f404d3c0","ahmed2%7C1729222407%7COr2uRdLUZ6K3KGmUCmcYyULw9H7ydBYddDOTEbjA7po%7C410a48562f6e8bd2177c86fe2337c3da0d3f29cddb3caf7217fbabc38e58be52"));
    }
}
