package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.component.Categories;
import page.component.ProductCart;
import page.component.QuickView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class AbstractShopAndCategoryPage extends AbstractPage{
    public Categories categories;
    public ProductCart productCart;
    WebElement baseElement;
    By baseElementLocator=By.cssSelector(".elementor-widget-wd_sidebar .elementor-widget-container");
    By appliedFilters=By.cssSelector(".wd-active-filters .widget");
    By maxPriceButton =By.xpath("(//span[contains(@class,'ui-slider-handle')])[2]");
    By minPriceButton =By.xpath("(//span[contains(@class,'ui-slider-handle')])[1]");
    By minPrice=By.id("min_price");
    By maxPrice=By.id("max_price");
    By priceFilterButton=By.tagName("button");
    By clearAllFiltersButton=By.cssSelector(".wd-clear-filters a");

    public AbstractShopAndCategoryPage(WebDriver driver){
        super(driver);
        categories=new Categories(driver);
        productCart=new ProductCart(driver);
    }
    public ProductCart dealWithProduct(){
        return new ProductCart(driver);
    }

    private WebElement getBaseElement(){
        try {
            if (baseElement.isDisplayed()){
                return baseElement;
            }
        } catch (Exception e) {
            baseElement= driver.findElement(baseElementLocator);
        }
        return baseElement;
    }
    private String getMinPrice(){
        return baseElement.findElement(minPrice).getAttribute("data-min");
    }
    private String getMaxPrice(){
        return baseElement.findElement(maxPrice).getAttribute("data-max");
    }
    public String getCurrentMinPrice(){
        return getBaseElement().findElement(minPrice).getAttribute("value");
    }
    public String getCurrentMaxPrice(){
        return getBaseElement().findElement(maxPrice).getAttribute("value");
    }
    private void setMaxPrice(int price){
        int max= Integer.parseInt(getMaxPrice());
        int currentMax=Integer.parseInt(getCurrentMaxPrice());
        if (price>currentMax){
            int i=Math.round((price-currentMax) / 10.0f);
            for (int j=0;j<i;j++){
                baseElement.findElement(maxPriceButton).sendKeys(Keys.ARROW_RIGHT);
            }
        }else {
            int i=Math.round((max-price) / 10.0f);
            for (int j=0;j<i;j++){
                baseElement.findElement(maxPriceButton).sendKeys(Keys.ARROW_LEFT);
            }
        }
    }
    private void setMinPrice(int price){
        int min=Integer.parseInt(getMinPrice());
        int currentMin=Integer.parseInt(getCurrentMinPrice());
        if (price<currentMin){
            int i=Math.round((currentMin - price) / 10.0f);
            for (int j=0;j<i;j++){
                baseElement.findElement(minPriceButton).sendKeys(Keys.ARROW_LEFT);
            }
        }else {
            int i=Math.round((price-min) / 10.0f);
            for (int j=0;j<i;j++){
                baseElement.findElement(minPriceButton).sendKeys(Keys.ARROW_RIGHT);
            }
        }
    }
    public AbstractShopAndCategoryPage applyPriceFilter(int minPrice, int maxPrice){
        getBaseElement();
        setMinPrice(minPrice);
        setMaxPrice(maxPrice);
        baseElement.findElement(priceFilterButton).click();
        waitForMilliseconds(2500);
        return this;
    }

    /**
     *
     * @param filter for (color or brand or size)
     * @return
     */
    public AbstractShopAndCategoryPage applyFilter(String filter){
        getBaseElement().findElement(By.xpath("//a[contains(.,'"+filter+"')]")).click();
        waitForMilliseconds(2000);
        return this;
    }
    public int getFilterCount(String filter){
        return Integer.parseInt(getBaseElement().findElement(By.xpath("//a[contains(.,'"+filter+"')]/ancestor::li/span")).getText());
    }
    public String getAppliedFilters(){
        try {
            return driver.findElement(appliedFilters).getText().replace("\nMin","").replace("\nMax","").replace("\n",",");
        } catch (Exception e) {
            return "There is no applied filters";
        }
    }
    public AbstractShopAndCategoryPage clearFilter(String filterName){
        driver.findElement(By.xpath("//*[@class='wd-active-filters']//a[contains(.,'"+filterName+"')]")).click();
        waitForMilliseconds(3000);
        return this;
    }
    public AbstractShopAndCategoryPage clearAllFilters(){
        driver.findElement(clearAllFiltersButton).click();
        waitForMilliseconds(3000);
        return this;
    }


    public TreeSet<Double> getPricesOfAllProductsInThePage(){
        TreeSet<Double> prices=new TreeSet<>();
        List<WebElement> products=driver.findElements(By.cssSelector(".wd-product"));
        for (WebElement product:products){
            /***/
            if(header.getCurrentCurrency().equals("USD")){
                prices.add(new BigDecimal(Double.parseDouble(new ProductCart(driver,product).getProductPrice().replaceAll("[,$]", ""))).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }else {
                prices.add(new BigDecimal(Double.parseDouble(new ProductCart(driver,product).getProductPrice().replaceAll("[.€EGP]", "").replace(",", "."))).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
        }
        return prices;
    }
    public TreeSet<String> getBrandsOfAllProductsInThePage(){
        TreeSet<String> brands=new TreeSet<>();
        List<WebElement> products=driver.findElements(By.cssSelector(".wd-product"));
        for (WebElement product:products){
            brands.add(new ProductCart(driver,product).getProductBrand());
        }
        return brands;
    }
    public TreeSet<String> getColorsOfAllProductsInThePage(){
        TreeSet<String> colors=new TreeSet<>();
        List<WebElement> products=driver.findElements(By.cssSelector(".wd-product"));
        for (WebElement product:products){
            colors.add(new QuickView(driver,product).getProductColor());
            getActions().sendKeys(Keys.ESCAPE).perform();
        }
        return colors;
    }
    public TreeSet<String> getSizesOfAllProductsInThePage(){
        TreeSet<String> sizes=new TreeSet<>();
        List<WebElement> products=driver.findElements(By.cssSelector(".wd-product"));
        for (WebElement product:products){
            sizes.add(new QuickView(driver,product).getProductSize());
            getActions().sendKeys(Keys.ESCAPE).perform();
        }
        return sizes;
    }



}
