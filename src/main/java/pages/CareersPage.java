package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import waiting.WaitForCondition;

import static org.testng.Assert.assertTrue;

public class CareersPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "a.btn.btn-outline-secondary.rounded.text-medium.mt-5.mx-auto.py-3.loadmore")
    private WebElement seeAllTeamsButton;
    @FindBy(xpath = "//h3[text()='Quality Assurance']")
    private WebElement qaTeamLink;
    @FindBy(xpath = "//h1[@class='big-title big-title-media mt-4 mt-lg-0']")
    private WebElement careersHeader;
    @FindBy(css = "h3.category-title-media.ml-0")
    private WebElement seeOurLocations;
    @FindBy(xpath = "//h2[@class='elementor-heading-title elementor-size-default' and contains(text(), 'Life at Insider')]")
    private WebElement seeLifeAtTheInsider;
    @FindBy(css = "p.mt-5")
    private WebElement seeOurLocationsText;
    @FindBy(xpath = "//div[@class='elementor-text-editor elementor-clearfix']//p[contains(text(), 'We’re here to grow and drive growth—as none of us did before.')]")
    private WebElement seeLifeAtTheInsiderText;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        wait = WaitForCondition.getInstance(driver);
        PageFactory.initElements(driver, this);
        Assert.assertTrue(this.isPageOpened());
    }

    public void scrollToSeeAllTeams() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({" + " block: 'center' });", seeAllTeamsButton);
        this.wait.until(ExpectedConditions.visibilityOf(seeAllTeamsButton));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickSeeAllTeams() {
        this.wait.until(ExpectedConditions.elementToBeClickable(seeAllTeamsButton));
        seeAllTeamsButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToQualityAssurance() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ " + "block: 'center' });", qaTeamLink);
        wait.until(ExpectedConditions.visibilityOf(qaTeamLink));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public QAOpenPositionsPage clickQualityAssurance() {
        wait.until(ExpectedConditions.elementToBeClickable(qaTeamLink));
        qaTeamLink.click();
        return new QAOpenPositionsPage(driver);
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(careersHeader));
        return careersHeader.isDisplayed();
    }

    public void scrollToSeeOurLocations() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({" + " block: 'center' });", seeOurLocations);
        this.wait.until(ExpectedConditions.visibilityOf(seeOurLocations));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToSeeLifeAtTheInsider() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({" + " block: 'center' });", seeLifeAtTheInsider);
        this.wait.until(ExpectedConditions.visibilityOf(seeLifeAtTheInsider));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLocationsListVisible() {
        wait.until(ExpectedConditions.visibilityOf(seeOurLocationsText));
        return seeOurLocationsText.isDisplayed();
    }

    public boolean isLifeAtInsiderVisible() {
        wait.until(ExpectedConditions.visibilityOf(seeLifeAtTheInsiderText));
        return seeLifeAtTheInsiderText.isDisplayed();
    }

    public QAOpenPositionsPage goToQAOpenPositionsPage() {
        this.scrollToSeeOurLocations();
        assertTrue(this.isLocationsListVisible(), "Locations list is not visible");
        this.scrollToSeeLifeAtTheInsider();
        assertTrue(this.isLifeAtInsiderVisible(), "Life at Insider is not visible");
        this.scrollToSeeAllTeams();
        this.clickSeeAllTeams();
        this.scrollToQualityAssurance();
        return this.clickQualityAssurance();
    }
}
