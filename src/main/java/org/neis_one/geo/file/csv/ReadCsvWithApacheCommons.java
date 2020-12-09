/*
Copyright 2020 Pascal Neis <neis-one.org>.

This file is part of Examples4GisFunctions (https://github.com/pa5cal)

Examples4GisFunctions is free software: you can redistribute it and/or modify it under 
the terms of the GNU General Public License as published by the Free Software Foundation, 
either version 3 of the License, or (at your option) any later version.

Examples4GisFunctions is distributed in the hope that it will be useful, but WITHOUT 
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.
If not, see <http://www.gnu.org/licenses/>.
*/
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