package org.neis_one.geo.quality.main;

import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.file.shp.ReadShapefile;

public class Completeness {

	public static void main(String[] args) {
		SimpleFeatureSource polygonFeatures = new ReadShapefile()
				.get("./src/test/resources/samplepolygons/SampleLines.shp");
	}
}