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