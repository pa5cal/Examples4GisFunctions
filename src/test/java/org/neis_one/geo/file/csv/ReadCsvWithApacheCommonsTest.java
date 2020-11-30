package org.neis_one.geo.file.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Test {@link ReadCsvWithApacheCommons}.
 * 
 * @author pa5cal
 */
public class ReadCsvWithApacheCommonsTest {

	/**
	 * Beschreibung: Liest Zeilen aus einer CSV Datei.<br>
	 * Erwartetes Ergebnis: Zeilen der Datei.
	 */
	@Test
	public void testGetGeojson() throws IOException {
		// Arrange testdata
		String filename = "./src/test/resources/sampleexcel/TestExcel.csv";
		// Act/test
		var rows = ReadCsvWithApacheCommons.get(filename);
		// Assert
		assertEquals(3, rows.size());
	}
}