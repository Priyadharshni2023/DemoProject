package qa.TestMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import qa.base.Base;
import qa.util.TestUtil;
import qa.pages.HomePage;

import java.util.concurrent.TimeUnit;

public class HomePageTest extends Base
{
    HomePage homePageObj = new HomePage(driver);
    @Test
    public void launchBrowserAndAcceptCookies()
    {
        launchBrowser();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));
        driver.findElement(homePageObj.CookiesAcceptAllButton).click();
        driver.findElement(homePageObj.InputField).sendKeys("Active Sync");
        driver.findElement(homePageObj.InputField).sendKeys(Keys.RETURN);
        String homePageTitle = homePageObj.verifyHomePageTitle();
        Assert.assertEquals(homePageTitle, "Active Sync - Google Search","Home page title not matched");
    }

}
