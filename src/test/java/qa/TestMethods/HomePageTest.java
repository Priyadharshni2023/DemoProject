package qa.TestMethods;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qa.base.Base;
import qa.util.TestUtil;
import qa.pages.HomePage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static qa.util.TestUtil.takeScreenshotTest;

public class HomePageTest extends Base
{
    HomePage homePageObj = new HomePage(driver);
    String filePath = "/Users/freshworks/IdeaProjects/DemoProject/src/test/java/qa/resources/testData";
    String fileName = "testdata.xlsx";
    String sheetName = "Demo";
    WebDriverWait wait;

    @BeforeTest
    public void launchBrowserAndAcceptCookies() throws IOException, InterruptedException
    {
        launchBrowser();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.findElement(homePageObj.CookiesAcceptAllButton).click();
    }

    //This method is to enter the search text from excel file and click on the first search result
    @Test
    public void enterSearchText() throws IOException {
        File file = new File(filePath + "//" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook demoWorkBook = new XSSFWorkbook(inputStream);
        Sheet demoSheet = demoWorkBook.getSheet(sheetName);
        int rowCount = demoSheet.getLastRowNum() - demoSheet.getFirstRowNum();
        for (int k = 1; k <= rowCount; k++)
        {
            String searchText = TestUtil.readExcel(filePath, fileName, sheetName, k, 0);
            driver.findElement(homePageObj.InputField).sendKeys(searchText);
            driver.findElement(homePageObj.InputField).sendKeys(Keys.RETURN);
            String homePageTitle = homePageObj.verifyHomePageTitle();
            Assert.assertTrue(homePageTitle.contains(searchText + " - Google Search"));
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(homePageObj.SearchResult));
            driver.findElement(homePageObj.SearchResult).click();
            takeScreenshotTest();
            driver.navigate().back();
            driver.findElement(homePageObj.InputField).clear();
        }
    }
        @AfterTest
        public void tearDown()
        {
        driver.quit();
        }
}
