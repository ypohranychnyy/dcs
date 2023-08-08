import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ApplyPage;
import pages.CareersPage;
import pages.HomePage;
import pages.QAPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class InsiderTest {
    WebDriver driver;
    String url = "https://useinsider.com/";

    @Parameters("browser")
    @BeforeMethod
    public void setUp() {
        String browser = System.getenv("BROWSER") == null ? "Chrome" : System.getenv("BROWSER");
        driver = selectBrowser(browser);
        driver.manage().window().maximize();
    }

    public WebDriver selectBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver-win32\\chromedriver.exe");
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Webdriver\\geckodriver-v0.33.0-win32\\geckodriver.exe");
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Unsupported broswer type. Please use Chrome or firefox");
    }

    @Test
    public void testInsiderCareerPage() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(url);
        assertTrue(driver.getCurrentUrl().contains(url), "Home page is not opened");

        homePage.clickAcceptAll();
        homePage.clickCompanyMenu();
        CareersPage careersPage = homePage.clickCareersOption();
        assertTrue(careersPage.isPageOpened(), "Career page is not opened");

        careersPage.scrollToSeeOurLocations();
        assertTrue(careersPage.isLocationsListVisible(), "Locations list is not visible");
        careersPage.scrollToSeeLifeAtTheInsider();
        assertTrue(careersPage.isLifeAtInsiderVisible(), "Life at Insider is not visible");
        careersPage.scrollToSeeAllTeams();
        careersPage.clickSeeAllTeams();
        careersPage.scrollToQualityAssurance();
        QAPage qaPage = careersPage.clickQualityAssurance();
        assertTrue(qaPage.isPageOpened(), "QA page is not opened");

        qaPage.clickSeeAllJobs();
        qaPage.selectLocation();
        assertTrue(qaPage.areAllJobsInIstanbul());
        String firstJobTitle = qaPage.getFirstJobTitle();
        ApplyPage applyPage = qaPage.clickViewRoleBtn(firstJobTitle);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        assertTrue(applyPage.getCurrentUrl().contains("jobs.lever.co/useinsider"), "Redirect to Lever application form failed");
        applyPage.verifyJobTitle(firstJobTitle);
        applyPage.clickApplyForThisJobButton();
        assertTrue(applyPage.applicationForm.isDisplayed(), "Application form is not displayed");

    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                // Take the screenshot
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Define the path to save the screenshot
                Path destination = Paths.get("screenshots", result.getName() + ".png");

                // Create the "screenshots" directory if it doesn't exist
                File screenshotsDir = new File("screenshots");
                if (!screenshotsDir.exists()) {
                    screenshotsDir.mkdir();
                } else {
                    // Clear the contents of the screenshots folder
                    File[] files = screenshotsDir.listFiles();
                    for (File file : files) {
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }

                // Move the screenshot to the destination path
                Files.move(screenshotFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

                // Print the path to the screenshot in the console
                System.out.println("Screenshot saved: " + destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tearDown();
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

