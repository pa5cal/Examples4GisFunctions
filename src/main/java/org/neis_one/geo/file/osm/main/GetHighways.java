package org.neis_one.geo.file.osm.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.utils.Sets;
import org.neis_one.geo.file.osm.ReadPbf;
import org.neis_one.geo.filter.attribute.KeyValueFilter;

import crosby.binary.osmosis.OsmosisReader;

public class GetHighways {

	public void save(String osmFilePath, Map<String, Set<String>> filteringKeyValues) {

		processOsm(osmFilePath, filteringKeyValues);
		processOsm(osmFilePath, filteringKeyValues);
		processOsm(osmFilePath, filteringKeyValues);

		// #TODO
		// WriteDroneMapElements droneMapWriter = new
		// WriteDroneMapElements(resultFilePath);
		// droneMapWriter.writeResults(readOsmFilterElements.getDroneMapElementsResult());
	}

	private void processOsm(final String osmFilePath, final Map<String, Set<String>> filteringKeyValues) {
		try {
			OsmosisReader reader = new OsmosisReader(new FileInputStream(osmFilePath));
			reader.setSink(new ReadPbf(new KeyValueFilter(filteringKeyValues)));
			reader.run();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Start...");
		String osmFilePath = "/Path/To/Your/read.osm.pbf";
		Map<String, Set<String>> filteringKeyValues = new HashMap<>();
		filteringKeyValues.put("highway", Sets.newHashSet("motorway", "path"));

		GetHighways result = new GetHighways();
		result.save(osmFilePath, filteringKeyValues);
		System.out.println("done.");
	}
}