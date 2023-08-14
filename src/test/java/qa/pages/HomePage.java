package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static qa.base.Base.driver;

public class HomePage {
    // This element is used to locate the Accept all cookie button in Google
    public By CookiesAcceptAllButton = By.xpath("//*[@id=\"L2AGLb\"]/div");
    // This element is to locate the search bar in google home
    public By InputField = By.id("APjFqb");
    //This element is to find the first search result in results page
    public By SearchResult = By.xpath("//h3[contains(@class,'LC20lb')]");
    public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

}
