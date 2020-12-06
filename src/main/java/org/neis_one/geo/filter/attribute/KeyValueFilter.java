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