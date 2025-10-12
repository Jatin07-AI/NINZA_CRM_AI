package practice;

import genericutility.ExcelFileUtility;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws Throwable{
		
		
		ExcelFileUtility ex = new ExcelFileUtility();
		int rowCount = ex.getRowCount("Practice");
		System.out.println("Total Row Count" +rowCount);
		
		for(int i= 1;i<=rowCount;i++) {
			
			String brand = ex.readDataFromExcelFile("Practice", i, 0);
			String model = ex.readDataFromExcelFile("Practice", i, 1);
			
			System.out.println(brand+"==>"+model);
		}
		
//		
//		// ========================================
//		// 1. OPEN EXCEL FILE USING FILEINPUTSTREAM
//		// ========================================
//		// Replace "filepath" with the actual path of your Excel file
//		FileInputStream fis = new FileInputStream("./src/test/resources/CommonDataExcel.xlsx"); 
//		
//		// Create workbook object from Excel file (entire Excel file in memory)
//		Workbook wb = WorkbookFactory.create(fis); 
//		
//		// Access a particular sheet by name
//		Sheet sh = wb.getSheet("Practice"); 
//		
//		// ========================================
//		// 2. READ MULTIPLE ROWS OF DATA
//		// ========================================
//		// Loop through rows starting from index 1 (ignoring header row at index 0)
//		// sh.getLastRowNum() → gives the index of the last row (zero-based index)
//		for (int i = 1; i <= sh.getLastRowNum(); i++) {
//			
//			// Read cell value from 1st column (Brand name)
//			String Brand = sh.getRow(i).getCell(0).getStringCellValue();
//			
//			// Read cell value from 2nd column (Product name)
//			String Product = sh.getRow(i).getCell(1).getStringCellValue();
//			
//			// Print the data in "Brand-->Product" format
//			System.out.println(Brand + " --> " + Product);
//		}
//		
//		// ========================================
//		// 3. CLOSE WORKBOOK
//		// ========================================
//		wb.close(); // Always close workbook to free memory
	}
}
