package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.CompareProductsPage;
import pages.ProductPage;
import pages.WishlistPage;

import java.util.List;

public class ProductCart {
    private final WebDriver driver;
    private WebElement productCart;
    public QuickView quickView;
    private Actions actions;
    WebElement addToWishlistButton;
    WebElement addToCompareButton;
    public ProductCart(WebDriver _driver){
        driver=_driver;
    }

    /**
     *
     * @param productId take the value of attribute (data-id)
     */
    public ProductCart selectProduct(int productId){
        actions=new Actions(driver);
        productCart=driver.findElement(By.cssSelector("div[data-id='"+productId+"']"));
        quickView=new QuickView(driver,productCart);
        actions.moveToElement(productCart).perform();
        return this;
    }
    public ProductPage openProductPage(){
        productCart.click();
        return new ProductPage(driver);
    }
    public void addToWishlist(){
        addToWishlistButton=productCart.findElement(By.cssSelector(".wd-wishlist-btn a"));
        actions=new Actions(driver);
        actions.moveToElement(addToWishlistButton).perform();
        if(productCart.findElement(By.cssSelector(".wd-wishlist-btn span")).getText().equals("Add to wishlist")){
            addToWishlistButton.click();
        }else {
            System.out.println("The product already in the wishlist");
        }
    }
    public WishlistPage addToWishlistThenOpenWishListPage(){
        addToWishlist();
        addToWishlistButton.click();
        return new WishlistPage(driver);
    }
    public void addToComparePage(){
        addToCompareButton=productCart.findElement(By.cssSelector(".wd-compare-btn a"));
        actions=new Actions(driver);
        actions.moveToElement(addToCompareButton).perform();
        if(productCart.findElement(By.cssSelector(".wd-compare-btn span")).getText().equals("Compare")){
            addToCompareButton.click();
        }else {
            System.out.println("The product already in the compare list");
        }
    }
    public CompareProductsPage addToCompareThenOpenComparePage(){
        addToComparePage();
        addToCompareButton.click();
        return new CompareProductsPage(driver);
    }

    /*******************************************/

    /*******************************************/
    public String getProductName(){
        return productCart.findElement(By.cssSelector(".wd-entities-title a")).getText();
    }
    public String[] getProductCategory(){
        List<WebElement> categoriesNames=productCart.findElements(By.cssSelector(".wd-product-cats a"));
        String[] categoryNames=new String[categoriesNames.size()];
        int i=0;
        for (WebElement categoryName:categoriesNames){
            categoryNames[i]=categoryName.getText();
            i++;
        }
        return categoryNames;
    }
    public String getProductBrand(){
        return productCart.findElement(By.cssSelector(".wd-product-brands-links a")).getText();
    }
    public String getProductPrice(){
        return productCart.findElement(By.cssSelector(".price bdi")).getText();
    }


}
