package page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuantityController {
    WebDriver driver;
    WebElement baseElement;
    private final By quantity=By.className("qty");
    private final By plusButton=By.className("plus");
    private final By minusButton=By.className("minus");

    public QuantityController(WebDriver _driver, WebElement element){
        driver=_driver;
        baseElement=element;
    }

    public void setQuantity(Integer wantedQuantity){
        baseElement.findElement(quantity).clear();
        baseElement.findElement(quantity).sendKeys(wantedQuantity.toString());
    }
    public String getQuantity(){
        return baseElement.findElement(quantity).getAttribute("value");
    }
    public void plusQuantity(){
        baseElement.findElement(plusButton).click();
    }
    public void minusQuantity(){
        baseElement.findElement(minusButton).click();
    }
}
