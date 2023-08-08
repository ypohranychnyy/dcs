package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header {
    @FindBy(xpath = "a.nav-link.dropdown-toggle[href='javascript:void(0);']#mega-menu-1")
    WebElement moreMenu;
    private WebDriver driver;
    public Header(WebDriver driver){
        this.driver = driver;
    }

    public MoreDropDown clickMore(){
        this.moreMenu.click();
        return new MoreDropDown(this.driver);
    }
}
