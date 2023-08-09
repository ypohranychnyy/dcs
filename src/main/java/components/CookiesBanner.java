package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiting.WaitForCondition;

public class CookiesBanner {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "a#wt-cli-accept-all-btn")
    WebElement acceptAllButton;

    public CookiesBanner(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = WaitForCondition.getInstance(driver);
    }

    public void clickAcceptAll() {
        this.wait.until(ExpectedConditions.elementToBeClickable(acceptAllButton));
        acceptAllButton.click();
        this.wait.until(ExpectedConditions.invisibilityOfAllElements(acceptAllButton));
    }
}