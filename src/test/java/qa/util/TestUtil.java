package qa.util;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import qa.base.Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends Base {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;
    static Workbook book;
    static Sheet sheet;
    static JavascriptExecutor js;
    static String searchText = "";


    public static String readExcel(String filePath, String fileName, String sheetName, int k, int l) throws IOException {
        File file = new File(filePath + "//" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook demoWorkBook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            demoWorkBook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            demoWorkBook = new HSSFWorkbook(inputStream);
        }
        Sheet demoSheet = demoWorkBook.getSheet(sheetName);
        int rowCount = demoSheet.getLastRowNum() - demoSheet.getFirstRowNum();

        Row row = null;
        for (int i = 1; i < rowCount + 1; i++) {
            row = demoSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                if (i == k && j == l) {
                    searchText = row.getCell(j).getStringCellValue();
                    break;
                }
            }
        }
        return searchText;
    }

    public static void takeScreenshotTest() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        try {
            FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


