package genericutility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String readDataFromExcelFile(String sheetName,int rowNum,int cellNum) throws Throwable{
		
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonDataExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		
		return value;
	}
	
	public int getRowCount(String sheetName)throws Throwable{
		
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonDataExcel.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		
		wb.close();
		return rowCount;
	}
}
