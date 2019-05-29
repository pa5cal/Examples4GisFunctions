package org.neis_one.geo.osm.cache;

import java.util.HashMap;
import java.util.Map;

import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 * Stores {@link Node}'s in a {@link Map} as some kind of chache.
 * 
 * @author pa5cal
 */
public class Store<E extends Entity> {

	private final Map<Long, E> store;

	public Store() {
		store = new HashMap<>();
	}

	public E get(long id) {
		return store.get(id);
	}

	public void putIdToSave(long id) {
		if (!store.containsKey(id)) {
			store.put(id, null);
		}
	}

	public boolean saveIfContainsId(E e) {
		boolean saved = false;
		if (store.containsKey(e.getId())) {
			store.put(e.getId(), e);
			saved = true;
		}
		return saved;
	}
}