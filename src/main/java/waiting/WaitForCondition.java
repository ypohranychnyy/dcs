package waiting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForCondition {
    private static WebDriverWait wait;

    public static WebDriverWait getInstance(WebDriver driver) {
        if (WaitForCondition.wait == null) {
            WaitForCondition.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        // Otherwise it's already initialized and we don't need to initialize it again
        return WaitForCondition.wait;
    }
}
