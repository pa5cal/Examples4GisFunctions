package org.neis_one.geo.grid.main;

import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.neis_one.geo.file.shp.WriteShapefile;
import org.neis_one.geo.grid.Hexagon;

public class CreateHexGridShpFileByShpFile {

	public static void main(String[] args) {
		SimpleFeatureSource featureCollection = new ReadShapefile().get("./Path/To/Your/Shapefile");
		SimpleFeatureSource grid = Hexagon.create(featureCollection);
		new WriteShapefile().put("./src/main/resources/hexgrid.shp", grid);
	}
}