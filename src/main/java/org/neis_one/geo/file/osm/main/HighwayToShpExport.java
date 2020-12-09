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
package org.neis_one.geo.file.osm.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.utils.Sets;
import org.neis_one.geo.file.osm.OsmFeature;
import org.neis_one.geo.file.osm.OsmFeaturesToShp;
import org.neis_one.geo.file.osm.ReadPbf;
import org.neis_one.geo.filter.attribute.KeyValueFilter;

import crosby.binary.osmosis.OsmosisReader;

/**
 * Example: Read OSM PBF file and export all highways as a shapefile.
 * 
 * @author pa5cal
 */
public class HighwayToShpExport {

	private Collection<OsmFeature> read(String osmFilePath, Map<String, Set<String>> filteringKeyValues) {
		// Processing
		processOsm(osmFilePath, filteringKeyValues);
		processOsm(osmFilePath, filteringKeyValues);
		return processOsm(osmFilePath, filteringKeyValues);
	}

	private Collection<OsmFeature> processOsm(final String osmFilePath,
			final Map<String, Set<String>> filteringKeyValues) {
		Collection<OsmFeature> result = new ArrayList<>();
		try {
			OsmosisReader reader = new OsmosisReader(new FileInputStream(osmFilePath));
			ReadPbf sink = new ReadPbf(new KeyValueFilter(filteringKeyValues));
			reader.setSink(sink);
			reader.run();
			result.addAll(sink.getResult());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void save(String osmFilePath, Collection<OsmFeature> result) {
		OsmFeaturesToShp writer = new OsmFeaturesToShp();
		writer.init(result);
		writer.save(osmFilePath);
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Start...");

		// Configuration
		String osmFilePath = "/Path/To/Your/read.osm.pbf";
		String shpFilePath = "/Path/To/Your/save.shp";
		Map<String, Set<String>> filteringKeyValues = new HashMap<>();
		filteringKeyValues.put("highway", Sets.newHashSet("motorway", "motorway_link", "secondary", "path"));

		// Processing
		HighwayToShpExport highways = new HighwayToShpExport();
		Collection<OsmFeature> result = highways.read(osmFilePath, filteringKeyValues);
		highways.save(shpFilePath, result);

		System.out.println("done.");
	}
}