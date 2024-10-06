package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.component.Categories;
import page.component.ProductCart;

import java.util.Arrays;
import java.util.List;

public class HomePage extends AbstractPage{
    public Categories categories;
    public ProductCart productCart;
    public HomePage(WebDriver driver,String homePageLink){
        super(driver);
        driver.get(homePageLink);
        categories=new Categories(driver);
        productCart=new ProductCart(driver);
    }
    public ShopPage openShopPageFromBanner(){
        driver.findElement(By.cssSelector(".btn-icon-pos-right")).click();
        return new ShopPage(driver);
    }
    public CheckoutPage buyBundle(){
        getActions().moveToElement(driver.findElement(By.xpath("//a[contains(.,'Buy bundle now')]"))).click().perform();
        return new CheckoutPage(driver);
    }
    public void openInstagramPage(){
        getActions().moveToElement(driver.findElement(By.cssSelector(".wd-insta-item a"))).click().perform();
    }

    public PostPage openPostPage(String postName){
        if (postName.equals("Why choose organic cotton for your baby")){
            WebElement carousel = driver.findElement(By.xpath("(//div[@class='wd-carousel-wrap'])[5]"));
            getJs().executeScript("arguments[0].style.transform = 'translate3d(-333.25px, 0px, 0px)';",carousel);
        }
        getActions().moveToElement(driver.findElement(By.xpath("//a[contains(.,'"+postName+"')]"))).click().perform();
        return new PostPage(driver);
    }

    public String getAllPostTitles(){
        List<WebElement> posts=driver.findElements(By.cssSelector(".wd-posts .wd-carousel-item .post-title"));
        String[] postsNames = new String[posts.size()];
        int i=0;
        for (WebElement post:posts){
            postsNames[i]=post.getText().replace(" ","-");
            i++;
        }
        return Arrays.toString(postsNames);
    }
}
