package org.neis_one.geo.quality.main;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.calculation.Length;
import org.neis_one.geo.file.shp.ReadShapefile;

/**
 * Example: Calculate the total length of all shapefile features. <br>
 * "Datenüberschuss (commission): Datensatz hat zusätzliche Informationen.
 * Datenmangel (omission): Datensatz enthält weniger Daten als angegeben." vgl.
 * https://de.wikipedia.org/wiki/Geodaten#Vollst%C3%A4ndigkeit
 * 
 * @author pa5cal
 */
public class Completeness {

	public static void main(String[] args) {
		// Read Shapefile
		SimpleFeatureSource lineFeatures = new ReadShapefile().get("./src/test/resources/samplelines/SampleLines.shp");

		// Calculate total length
		double totalLength = 0;
		try {
			totalLength = Length.calculate(lineFeatures.getFeatures().features());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println("Total length [m]: " + totalLength);
	}
}