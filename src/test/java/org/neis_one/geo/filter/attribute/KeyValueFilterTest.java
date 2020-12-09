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
package org.neis_one.geo.filter.attribute;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.utils.Sets;
import org.junit.jupiter.api.Test;

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