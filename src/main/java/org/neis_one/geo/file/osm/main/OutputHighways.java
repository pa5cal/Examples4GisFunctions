package org.neis_one.geo.file.osm.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.neis_one.geo.file.osm.ReadPbf;

import crosby.binary.osmosis.OsmosisReader;

public class OutputHighways {

	public static void main(String[] args) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream("/Path/To/Your/read.osm.pbf");
		OsmosisReader reader = new OsmosisReader(inputStream);
		reader.setSink(new ReadPbf());
		reader.run();
	}
}