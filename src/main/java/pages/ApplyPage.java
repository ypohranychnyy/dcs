package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ApplyPage {
    private final WebDriverWait wait;
    @FindBy(css = "div > h2")
    WebElement h2JobTitle;
    @FindBy(css = "a.postings-btn.template-btn-submit.shamrock")
    WebElement applyForThisJobButton;
    @FindBy(xpath = "//h4[contains(text(), 'Submit your application')]")
    public WebElement applicationForm;


    WebDriver driver;

    public ApplyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyJobTitle(String firstJobTitle) {
        Assert.assertEquals(firstJobTitle, h2JobTitle.getText(), "The job titles do not match!");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public void clickApplyForThisJobButton() {
        applyForThisJobButton.click();
    }
}


