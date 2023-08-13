package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static qa.base.Base.driver;

public class HomePage {
    public By CookiesAcceptAllButton = By.xpath("//*[@id=\"L2AGLb\"]/div");
    public By InputField = By.id("APjFqb");
    public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

}
