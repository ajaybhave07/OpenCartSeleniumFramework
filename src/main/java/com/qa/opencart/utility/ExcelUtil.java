package com.qa.opencart.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_PATH = ".\\src\\test\\recources\\test_data\\opencart_test_data.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;
		try {
			FileInputStream fis = new FileInputStream(TEST_DATA_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int row = 0; row < sheet.getLastRowNum(); row++) {
				for (int col = 0; col < sheet.getRow(0).getLastCellNum(); col++) {

					// filling 2D array with all the data in excel
					data[row][col] = sheet.getRow(row + 1).getCell(col).toString();

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

}
