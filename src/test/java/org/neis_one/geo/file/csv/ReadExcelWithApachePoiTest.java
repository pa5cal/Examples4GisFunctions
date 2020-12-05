package org.neis_one.geo.file.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Test {@link ReadExcelWithApachePoi}.
 * 
 * @author pa5cal
 */
public class ReadExcelWithApachePoiTest {

	/**
	 * Beschreibung: Liest Zeilen aus einer Excel Datei.<br>
	 * Erwartetes Ergebnis: Zeilen der Datei.
	 */
	@Test
	public void testGet() throws IOException {
		// Arrange testdata
		String filename = "./src/test/resources/sampleexcel/TestExcel.xlsx";
		// Act/test
		var rows = ReadExcelWithApachePoi.get(filename);
		// Assert
		assertEquals(3, rows.size());
	}
}