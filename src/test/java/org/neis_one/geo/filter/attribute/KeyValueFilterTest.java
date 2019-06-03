package org.neis_one.geo.filter.attribute;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.utils.Sets;
import org.junit.Test;

/**
 * Test {@link KeyValueFilter}.
 * 
 * @author pa5cal
 */
public class KeyValueFilterTest {

	/**
	 * Beschreibung: Prüft ob der Key/Value im Filter enthalten ist.<br>
	 * Erwartetes Ergebnis: Key/Value ist enhalten.
	 */
	@Test
	public void testContainsIgnoreCase() {
		// Arrange Testdata
		Map<String, Set<String>> expectedKeyValues = createHighway();
		KeyValueFilter filter = new KeyValueFilter(expectedKeyValues);
		// Act/Test
		boolean contains = filter.containsIgnoreCase("highway", "motorway");
		// Assert
		assertTrue(contains);
	}

	/**
	 * Beschreibung: Prüft ob der Key/Value im Filter enthalten ist.<br>
	 * Erwartetes Ergebnis: Key/Value ist enhalten.
	 */
	@Test
	public void testContainsIgnoreCaseUpper() {
		// Arrange Testdata
		Map<String, Set<String>> expectedKeyValues = createHighway();
		KeyValueFilter filter = new KeyValueFilter(expectedKeyValues);
		// Act/Test
		boolean contains = filter.containsIgnoreCase("Highway", "motorway");
		// Assert
		assertTrue(contains);
	}

	/**
	 * Beschreibung: Prüft ob der Key/Value im Filter nicht enthalten ist.<br>
	 * Erwartetes Ergebnis: Key/Value ist nicht enhalten.
	 */
	@Test
	public void testContainsIgnoreCaseFalse() {
		// Arrange Testdata
		Map<String, Set<String>> expectedKeyValues = createHighway();
		KeyValueFilter filter = new KeyValueFilter(expectedKeyValues);
		// Act/Test
		boolean contains = filter.containsIgnoreCase("landuse", "path");
		// Assert
		assertFalse(contains);
	}

	private Map<String, Set<String>> createHighway() {
		Map<String, Set<String>> expectedKeyValues = new HashMap<>();
		expectedKeyValues.put("highway", Sets.newHashSet("motorway", "path"));
		return expectedKeyValues;
	}
}