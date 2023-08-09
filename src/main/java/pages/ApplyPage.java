package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplyPage {
    @FindBy(xpath = "//h4[contains(text(), 'Submit your application')]")
    public WebElement applicationForm;
    @FindBy(css = "div > h2")
    WebElement h2JobTitle;
    @FindBy(css = "a.postings-btn.template-btn-submit.shamrock")
    WebElement applyForThisJobButton;
    WebDriver driver;

    public ApplyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getJobTitle() {
        return h2JobTitle.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickApplyForThisJobButton() {
        applyForThisJobButton.click();
    }
}


