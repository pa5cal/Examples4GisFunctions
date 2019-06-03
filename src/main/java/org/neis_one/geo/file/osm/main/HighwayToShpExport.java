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
		OsmFeaturesToShp writer = new OsmFeaturesToShp(result);
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