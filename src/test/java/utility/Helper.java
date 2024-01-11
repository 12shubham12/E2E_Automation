package utility;

import org.apache.commons.io.FileUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Helper extends BaseClass {

    static File screenshotFile;

    //Screenshot method - Capture screenshot Method-1
    public static String takeScreenshot() {

        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot to a file
        String screenshotPath = "./Screenshot_Folder/screenshot.png";
        try {
            Files.copy(screenshotFile.toPath(), new File(screenshotPath).toPath());
        } catch (IOException e) {
            System.out.println("Unable to capture screenshot "+e.getMessage());
        }
        return screenshotPath;
    }


    //Screenshot method - Capture screenshot Method-2
    public static void takeSS_URLtoExcel(String screenshotpath, String workbookName, String sheetName) {

        try {
            // Take a screenshot
            screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Save the screenshot to a specific directory
            String screenshotPath = screenshotpath;
            saveScreenshot(screenshotFile, screenshotPath);
            // Create an Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);
            Row row = sheet.createRow(0);
            // Create a hyperlink to the screenshot in the Excel file
            createHyperlink(workbook, row, 0, screenshotPath, "LoginSS");
            // Save the workbook to a specific directory
            String excelFilePath = workbookName;
            saveWorkbook(workbook, excelFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveScreenshot(File screenshotFile, String destinationPath) throws IOException {
        // Copy the screenshot to the specified destination
        FileUtils.copyFile(screenshotFile, new File(destinationPath));
    }
    public static void createHyperlink(Workbook workbook, Row row, int columnIndex, String hyperlinkAddress, String UrlName) {

        URI screenshotUri = screenshotFile.toURI();
        CreationHelper createHelper = workbook.getCreationHelper();
        Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.FILE);
        hyperlink.setAddress(hyperlinkAddress);
        // Create a cell with the hyperlink
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(UrlName);
        cell.setHyperlink(hyperlink);
    }
    private static void saveWorkbook(Workbook workbook, String destinationPath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
            workbook.write(outputStream);
        }
    }

    //Function to return the date in specified format
    public static String getCurrentDateTime(){
        DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
    }


}

