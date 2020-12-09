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
package org.neis_one.geo.file.shp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeImpl;
import org.geotools.feature.type.GeometryDescriptorImpl;
import org.geotools.feature.type.GeometryTypeImpl;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.feature.type.GeometryType;

/**
 * Writes data to Shapefile.
 * 
 * @author pa5cal
 */
public class WriteShapefile {

	/**
	 * Schreibt eine {@link SimpleFeatureSource} in ein Shapefilee.
	 */
	public void saveToFile(SimpleFeatureSource simpleFeatureSource, String filename) {
		try {
			// File
			File file = new File(filename);
			Map<String, Serializable> params = new HashMap<>();
			params.put("url", file.toURI().toURL());

			// Datastore
			ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
			ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
			SimpleFeatureType schema = simpleFeatureSource.getSchema();
			GeometryDescriptor geom = schema.getGeometryDescriptor();

			// Create new Schema and Feature-Collection
			SimpleFeatureType shpType = createNewFeatureType(schema, geom);
			newDataStore.createSchema(shpType);
			List<SimpleFeature> features = createNewFeatureCollection(simpleFeatureSource, shpType);

			// Save Shapefile
			String typeName = newDataStore.getTypeNames()[0];
			SimpleFeatureSource featureSource = newDataStore.getFeatureSource(typeName);
			if (featureSource instanceof SimpleFeatureStore) {
				SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
				SimpleFeatureCollection collection = new ListFeatureCollection(shpType, features);
				Transaction transaction = new DefaultTransaction("create");
				featureStore.setTransaction(transaction);
				try {
					featureStore.addFeatures(collection);
					transaction.commit();
				} catch (IOException problem) {
					problem.printStackTrace();
					transaction.rollback();
				} finally {
					transaction.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Source:
	 * https://github.com/ianturton/geotools-cookbook/blob/master/modules/output/src/main/java/org/ianturton/cookbook/output/WriteShapefile.java
	 */
	private List<SimpleFeature> createNewFeatureCollection(SimpleFeatureSource simpleFeatureSource,
			SimpleFeatureType shpType) throws IOException {
		List<SimpleFeature> feats = new ArrayList<SimpleFeature>();
		FeatureIterator<SimpleFeature> features2 = simpleFeatureSource.getFeatures().features();
		while (features2.hasNext()) {
			SimpleFeature f = features2.next();
			SimpleFeature reType = SimpleFeatureBuilder.build(shpType, f.getAttributes(), "");

			feats.add(reType);
		}
		return feats;
	}

	/**
	 * Source:
	 * https://github.com/ianturton/geotools-cookbook/blob/master/modules/output/src/main/java/org/ianturton/cookbook/output/WriteShapefile.java
	 */
	private SimpleFeatureType createNewFeatureType(SimpleFeatureType schema, GeometryDescriptor geom) {
		/*
		 * The Shapefile format has a couple limitations: - "the_geom" is always
		 * first, and used for the geometry attribute name - "the_geom" must be
		 * of type Point, MultiPoint, MuiltiLineString, MultiPolygon - Attribute
		 * names are limited in length - Not all data types are supported
		 * (example Timestamp represented as Date)
		 *
		 * Because of this we have to rename the geometry element and then
		 * rebuild the features to make sure that it is the first attribute.
		 */
		List<AttributeDescriptor> attributes = schema.getAttributeDescriptors();
		GeometryType geomType = null;
		List<AttributeDescriptor> attribs = new ArrayList<AttributeDescriptor>();
		for (AttributeDescriptor attrib : attributes) {
			AttributeType type = attrib.getType();
			if (type instanceof GeometryType) {
				geomType = (GeometryType) type;

			} else {
				attribs.add(attrib);
			}
		}

		GeometryTypeImpl gt = new GeometryTypeImpl(new NameImpl("the_geom"), geomType.getBinding(),
				geomType.getCoordinateReferenceSystem(), geomType.isIdentified(), geomType.isAbstract(),
				geomType.getRestrictions(), geomType.getSuper(), geomType.getDescription());

		GeometryDescriptor geomDesc = new GeometryDescriptorImpl(gt, new NameImpl("the_geom"), geom.getMinOccurs(),
				geom.getMaxOccurs(), geom.isNillable(), geom.getDefaultValue());

		attribs.add(0, geomDesc);

		SimpleFeatureType shpType = new SimpleFeatureTypeImpl(schema.getName(), attribs, geomDesc, schema.isAbstract(),
				schema.getRestrictions(), schema.getSuper(), schema.getDescription());
		return shpType;
	}
}