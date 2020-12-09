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
package org.neis_one.geo.grid.main;

import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.neis_one.geo.file.shp.WriteShapefile;
import org.neis_one.geo.grid.Hexagon;

public class CreateHexGridShpFileByBbox {

	public static void main(String[] args) {
		ReferencedEnvelope gridBounds = new ReferencedEnvelope(8, 9, 49.5, 50.5, DefaultGeographicCRS.WGS84);
		double sideLen = 0.1;
		SimpleFeatureSource grid = Hexagon.create(gridBounds, sideLen);
		new WriteShapefile().saveToFile(grid, "./src/main/resources/hexgrid.shp");
	}
}