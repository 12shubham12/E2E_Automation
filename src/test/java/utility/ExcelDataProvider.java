package utility;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelDataProvider {

    XSSFWorkbook wb;

    public ExcelDataProvider(){
        File scr = new File("./TestData/InputData.xlsx");
        try{
            FileInputStream fis = new FileInputStream(scr);
            wb = new XSSFWorkbook(fis);
        }
        catch(Exception e){
            System.out.println("Unable to read Excel file"+e.getMessage());
        }

        }
    //method to read data from excel
    public String getUserName(String sheetName, int row, int column){
        return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }
    public String getPassword(String sheetName, int row, int column){
        return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }
}
