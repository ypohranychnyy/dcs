package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoreDropDown {
    private WebDriver driver;

    public MoreDropDown(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-labelledby='mega-menu-1']")));
    }

    @FindBy(css = "a[href='https://useinsider.com/careers'].d-flex.flex-column.flex-fill")
    private WebElement careersLink;
}
