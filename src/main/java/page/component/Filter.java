package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Filter {
    WebDriver driver;
    WebElement baseElement;

    By baseElementLocator=By.cssSelector(".elementor-widget-wd_sidebar .elementor-widget-container");
    By appliedFilters=By.cssSelector(".wd-active-filters .widget");
    By maxPriceButton =By.xpath("(//span[contains(@class,'ui-slider-handle')])[2]");
    By minPriceButton =By.xpath("(//span[contains(@class,'ui-slider-handle')])[1]");
    By minPrice=By.id("min_price");
    By maxPrice=By.id("max_price");
    By priceFilterButton=By.tagName("button");


    public Filter(WebDriver _driver){
        driver=_driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            baseElement= driver.findElement(baseElementLocator);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private String getMinPrice(){
        return baseElement.findElement(minPrice).getAttribute("data-min");
    }
    private String getMaxPrice(){
        return baseElement.findElement(maxPrice).getAttribute("data-max");
    }
    public String getCurrentMinPrice(){
        return baseElement.findElement(minPrice).getAttribute("value");
    }
    public String getCurrentMaxPrice(){
        return baseElement.findElement(maxPrice).getAttribute("value");
    }
    private void setMaxPrice(int price){
        int max= Integer.parseInt(getMaxPrice());
//        int min= Integer.parseInt(getMinPrice());
//        if (price<min||price>max){
//            System.out.println("Incorrect price(not in the range)");
//            return null;
//        }
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
//        int max=Integer.parseInt(getMaxPrice());
//        if (price<min||price>max){
//            System.out.println("Incorrect price(not in the range)");
//            return null;
//        }
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
    public void applyPriceFilter(int minPrice,int maxPrice){
        setMinPrice(minPrice);
        setMaxPrice(maxPrice);
        baseElement.findElement(priceFilterButton).click();
        waitForMilliseconds(2500);
    }

    public void applyColorFilter(String color){
        baseElement.findElement(By.xpath("//a[contains(.,'"+color+"')]")).click();
        waitForMilliseconds(2000);
    }
    public int getColorCount(String color){
        return Integer.parseInt(baseElement.findElement(By.xpath("//a[contains(.,'"+color+"')]/ancestor::li/span")).getText());
    }
    public void applyBrandFilter(String brand){
        baseElement.findElement(By.xpath("//a[contains(.,'"+brand+"')]")).click();
        waitForMilliseconds(2000);
    }
    public int getBrandCount(String brand){
        return Integer.parseInt(baseElement.findElement(By.xpath("//a[contains(.,'"+brand+"')]/ancestor::li/span")).getText());
    }
    public String getAppliedFilters(){
        return driver.findElement(appliedFilters).getText().replace("\nMin","").replace("\nMax","").replace("\n",",");
    }
    private void clearMinPriceFilter(){
        driver.findElement(By.xpath("//a[contains(.,'Min')]")).click();
        waitForMilliseconds(3000);
    }
    private void clearMaxPriceFilter(){
        driver.findElement(By.xpath("//a[contains(.,'Max')]")).click();
        waitForMilliseconds(3000);
    }
    public void clearPriceFilter(){
        clearMaxPriceFilter();
        clearMinPriceFilter();
    }
    public void clearPriceFilter(String maxOrMin){
        switch (maxOrMin.toLowerCase()){
            case "max":
                clearMaxPriceFilter();
                break;
            case "min":
                clearMinPriceFilter();
                break;
            default:
                System.out.println("You should insert max or min");
        }
    }

}
