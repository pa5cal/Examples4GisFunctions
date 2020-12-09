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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KeyValueFilter.
 * 
 * @author pa5cal
 */
public class KeyValueFilter {

	private final Map<String, Set<String>> expectedLowerKeyValues;

	public KeyValueFilter(Map<String, Set<String>> expectedKeyValues) {
		expectedLowerKeyValues = new HashMap<>();
		// Convert input map to lower case
		for (Map.Entry<String, Set<String>> entry : expectedKeyValues.entrySet()) {
			expectedLowerKeyValues.put(entry.getKey().toLowerCase(),
					entry.getValue().stream().map(v -> v.toLowerCase()).collect(Collectors.toSet()));
		}
	}

	public boolean containsIgnoreCase(String key, String value) {
		boolean contains = false;
		String lowerKey = key.toLowerCase();
		String lowerValue = value.toLowerCase();

		Set<String> values = expectedLowerKeyValues.get(lowerKey);
		if (Objects.nonNull(values)) {
			contains = values.contains(lowerValue);
		}
		return contains;
	}
}