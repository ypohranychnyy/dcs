package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class QAPage {
    private final WebDriverWait wait;
    WebDriver driver;
    @FindBy(xpath = "//a[contains(text(), 'See all QA jobs')]")
    WebElement seeAllQAJobsButton;
    @FindBy(css = "span.select2-selection[aria-labelledby='select2-filter-by-department-container']")
    WebElement departmentFilter;
    @FindBy(css = "li[id*='Quality Assurance']")
    WebElement qaOption;
    @FindBy(css = "div.col-12.d-flex.flex-column.flex-lg-row.justify-content-lg-between.align-items-lg-end")
    WebElement jobsList;
    @FindBy(css = ".position-list-item-wrapper")
    List<WebElement> positionItems;
    @FindBy(xpath = "//*[contains(text(), 'View Role')]")
    WebElement viewRole;
    @FindBy(css = "h1.big-title.big-title-media")
    WebElement qaHeader;
    @FindBy(xpath = "//*[@id='jobs-list']/div[1]/div/p")
    WebElement jobTitle;
    @FindBy(css = "div.position-list-item-wrapper p.position-title")
    List<WebElement> jobTitles;
    @FindBy(css = "div.position-list-item-wrapper div.position-location")
    List<WebElement> jobLocations;
    @FindBy(css = "ul.select2-results__options li")
    List<WebElement> locationOptions;
    @FindBy(id = "filter-by-location")
    WebElement filterByLocationDropDown;

    public QAPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(this.driver);

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void clickSeeAllJobs() {
        seeAllQAJobsButton.click();
        isCorrectUrl();
        this.wait.until(ExpectedConditions.visibilityOf(jobTitle));
        scrollToSeeAllPositions();
    }

    public List<String> getJobLocations() {
        List<String> locations = new ArrayList<>();
        for (WebElement location : jobLocations) {
            locations.add(location.getText());
        }
        return locations;
    }

    public void selectLocation() {
        Select select = new Select(filterByLocationDropDown);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals("Istanbul, Turkey")) {
                option.click();
                break;
            }
        }
        scrollToSeeAllPositions();
        waitForLocationsUpdate();
        areAllJobsInIstanbul();
    }

    public void scrollToSeeAllPositions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({" +
                " block: 'center' });", jobTitle);
        wait.until(ExpectedConditions.visibilityOf(jobTitle));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForLocationsUpdate() {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(jobLocations));

        boolean allMatch = jobLocations.stream()
                .allMatch(option -> option.getText().contains("Istanbul, Turkey"));

        if (!allMatch) {
            throw new AssertionError("Not all location options contain 'Istanbul, Turkey'");
        }
    }

    public boolean areAllJobsInIstanbul() {
        List<String> locations = getJobLocations();
        return locations.stream().allMatch(location -> location.contains("Istanbul, Turkey"));
    }

    public String getFirstJobTitle() throws Exception {
        if (jobTitles.isEmpty()) {
            throw new Exception("Job titles list is empty");
        }
        return jobTitles.get(0).getText();
    }

    public ApplyPage clickViewRoleBtn(String partialPositionDescription) {
        for (WebElement positionItem : positionItems) {
            if (positionItem.getText().contains(partialPositionDescription)) {
                Actions action = new Actions(driver);
                action.moveToElement(positionItem).perform();
                this.wait.until(ExpectedConditions.visibilityOf(viewRole));
                this.wait.until(ExpectedConditions.elementToBeClickable(viewRole));
                viewRole.click();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return new ApplyPage(driver);
            }
        }
        throw new NotFoundException(String.format("I have not found an element with %s description", partialPositionDescription));
    }

    public boolean isCorrectUrl() {
        String expectedUrl = "https://useinsider.com/careers/open-positions/?department=qualityassurance";
        String actualUrl = driver.getCurrentUrl();
        return expectedUrl.equals(actualUrl);
    }

    // Define isPageOpened() method
    public boolean isPageOpened() {
        return qaHeader.isDisplayed();
    }
}
