package pages;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrivacyPage extends AbstractPage{
    public PrivacyPage(WebDriver driver){
        super(driver);
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        String lastWindowHandle = windowHandlesList.get(windowHandlesList.size() - 1);
        driver.switchTo().window(lastWindowHandle);
    }
}
