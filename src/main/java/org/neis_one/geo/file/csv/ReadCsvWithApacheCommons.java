package org.neis_one.geo.file.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 * Reads data from a csv file with Apache Commons CSV library.
 * 
 * @author pa5cal
 */
public class ReadCsvWithApacheCommons {

	/**
	 * Returns content of the csv file.
	 */
	public static List<List<String>> get(String filename) {
		var rows = new ArrayList<List<String>>();
		try {
			// Read file
			var csvFile = new FileInputStream(new File(filename));
			var parser = CSVParser.parse(csvFile, StandardCharsets.UTF_8, CSVFormat.EXCEL);
			// Add content rows
			for (var row : parser.getRecords()) {
				var columns = new ArrayList<String>();
				row.forEach(c -> columns.add(c.toString()));
				rows.add(columns);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
}