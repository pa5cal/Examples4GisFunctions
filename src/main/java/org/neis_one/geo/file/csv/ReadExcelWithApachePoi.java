package org.neis_one.geo.file.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Reads data from a excel file with Apache POI library.
 * 
 * @author pa5cal
 */
public class ReadExcelWithApachePoi {

	/**
	 * Returns content of the excel file.
	 */
	public static List<List<String>> get(String filename) {
		var rows = new ArrayList<List<String>>();
		try (var excelFile = new FileInputStream(new File(filename));
				var workbook = new XSSFWorkbook(excelFile);) {
			// Read only first sheet
			var datatypeSheet = workbook.getSheetAt(0);
			// Read each row with all cells
			datatypeSheet.forEach(r -> {
				var columns = new ArrayList<String>();
				r.forEach(c -> {
					columns.add(c.toString());
				});
				rows.add(columns);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
}