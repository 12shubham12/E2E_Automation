package testcases;
import org.apache.commons.io.FileUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class Screenshot_Program {

    static File screenshotFile;

    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        try {
            // Navigate to the webpage
            driver.get("https://example.com");
            // Take a screenshot
            screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Save the screenshot to a specific directory
            String screenshotPath = "./Screenshot_Folder/scrnshot.png";
            saveScreenshot(screenshotFile, screenshotPath);
            // Create an Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Screenshots");
            Row row = sheet.createRow(0);
            // Create a hyperlink to the screenshot in the Excel file
            createHyperlink(workbook, row, 0, screenshotPath);
            // Save the workbook to a specific directory
            String excelFilePath = "./screenshots.xlsx";
            saveWorkbook(workbook, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver
         //   driver.quit();
        }
    }
    public static void saveScreenshot(File screenshotFile, String destinationPath) throws IOException {
        // Copy the screenshot to the specified destination
        FileUtils.copyFile(screenshotFile, new File(destinationPath));
    }
    public static void createHyperlink(Workbook workbook, Row row, int columnIndex, String hyperlinkAddress) {

        URI screenshotUri = screenshotFile.toURI();
        CreationHelper createHelper = workbook.getCreationHelper();
        Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.FILE);
        hyperlink.setAddress(hyperlinkAddress);
        // Create a cell with the hyperlink
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue("Open Screenshot");
        cell.setHyperlink(hyperlink);

    }
    private static void saveWorkbook(Workbook workbook, String destinationPath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
            workbook.write(outputStream);

        }
    }
}
