package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriverWait wait;
    WebDriver driver;

    @FindBy(xpath = "//nav//li[5]/a")
    WebElement company;

    @FindBy(xpath = "//nav//li[5]//div[2]/a[2]")
    WebElement careersOption;


    @FindBy(xpath = "//title[text()='#1 Leader in Individualized, Cross-Channel CX — Insider']")  // assuming there's an  tag with this text
    WebElement homeHeader;
    @FindBy(css = "a#wt-cli-accept-all-btn")
    WebElement acceptAllButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickCompanyMenu() {
        company.click();
        this.wait.until(ExpectedConditions.visibilityOf(careersOption));
    }

    public CareersPage clickCareersOption() {
        careersOption.click();
        return new CareersPage(driver);
    }

    public void clickAcceptAll() {
        this.wait.until(ExpectedConditions.elementToBeClickable(acceptAllButton));
        acceptAllButton.click();
        this.wait.until(ExpectedConditions.invisibilityOfAllElements(acceptAllButton));
    }

}
