package pages;

import components.CookiesBanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiting.WaitForCondition;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    CookiesBanner cookiesBanner;

    @FindBy(xpath = "//a[@class='nav-link dropdown-toggle' and contains(text(), 'Company')]")
    WebElement company;
    @FindBy(xpath = "//a[@class='dropdown-sub' and contains(@href, 'careers')]")
    WebElement careersOption;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.cookiesBanner = new CookiesBanner(driver);
        this.wait = WaitForCondition.getInstance(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickCompanyMenu() {
        this.company.click();
        this.wait.until(ExpectedConditions.visibilityOf(careersOption));
    }

    public CareersPage goToCareersPage() {
        this.clickCompanyMenu();
        this.careersOption.click();
        return new CareersPage(driver);
    }

    public void clickAcceptAllCookies() {
        this.cookiesBanner.clickAcceptAll();
    }
}
