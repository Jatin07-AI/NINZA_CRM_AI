package practice;

import genericutility.ExcelFileUtility;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws Throwable {
//		// Main method - program execution starts here
//
//		// Step 1: Open the Excel file using FileInputStream
//		// (Path should point to your Excel file; give full path with extension like .xlsx or .xls)
//		FileInputStream fis = new FileInputStream("./src/test/resources/CommonDataExcel.xlsx");
//
//		// Step 2: Create a Workbook instance (represents the Excel file in memory)
//		Workbook wb = WorkbookFactory.create(fis);
//
//		// Step 3: Access the sheet by its name from the workbook
//		Sheet sh = wb.getSheet("Campaign");
//
//		// Step 4: Get the specific row (index starts from 0, so row 1 means 2nd row in Excel)
//		Row r = sh.getRow(1);
//
//		// Step 5: Get the specific cell from that row (index starts from 0, so cell 2 = 3rd column)
//		Cell c = r.getCell(2);
//
//		// Step 6: Read the cell value as a String (only works if the cell contains text)
//		String campaignName = c.getStringCellValue();
//
//		// Step 7: Print the value in the console
//		System.out.println(campaignName);
//
//		// Step 8: Close the workbook to free resources
//		wb.close();
		
		ExcelFileUtility ex = new ExcelFileUtility();
		String CampaignName = ex.readDataFromExcelFile("Campaign", 1, 2);
		System.out.println(CampaignName);
	}
}
