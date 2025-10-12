package practice;

// Importing required classes from Java IO and Apache POI library
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws Throwable {

		// Step 1: Load the Excel file into Java program using FileInputStream
		// FileInputStream helps to read the file from the given path
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonDataExcel.xlsx");

		// Step 2: Create a Workbook instance from the input stream
		// Workbook is the representation of the entire Excel file (it can contain multiple sheets)
		Workbook wb = WorkbookFactory.create(fis);

		// Step 3: Access a particular sheet inside the workbook
		// "Campaign" is the sheet name in the Excel file
		Sheet sh = wb.getSheet("Campaign");

		// Step 4: Get a particular row inside the sheet
		// Here, row index 4 means 5th row (because index starts from 0)
		Row r = sh.getRow(4);

		// Step 5: Create a new cell in that row
		// Index 5 means the 6th cell (since index starts from 0)
		Cell c = r.createCell(5);

		// Step 6: Define the type of the cell
		// We are telling Excel that this cell will store String data
		c.setCellType(CellType.STRING);

		// Step 7: Write some value into that cell
		// Here, we are writing "Jatin" into the 5th row, 6th column of Campaign sheet
		c.setCellValue("Jatin");

		// Step 8: Open FileOutputStream to write changes back into the same Excel file
		FileOutputStream fos = new FileOutputStream("./src/test/resources/CommonDataExcel.xlsx");

		// Step 9: Save the workbook (write all modifications into the file)
		wb.write(fos);

		// Step 10: Close the workbook to free up memory/resources
		wb.close();

	}
}
