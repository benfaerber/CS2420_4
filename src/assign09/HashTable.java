package assign09;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * A HashTable that utilizes the double hashing strategy
 * and prime number resizing
 * @param <K> The key of the hashmap (make sure to add hashCode())
 * @param <V> The value of the hashmap
 * @author Benjamin Faerber & David Chen
 */
public class HashTable<K, V> implements Map<K, V> {
    private static final int[] PRIMES = new int[] {
            101, 211, 431, 863, 1733, 3467, 6947, 13901, 27803,
            55609, 111227, 222461, 444929, 889871, 1779761
    };
    private static final int DEFAULT_CAPACITY = PRIMES[0];

    private static final float MAX_LOAD_FACTOR = 0.75f;

    private int primeIndex = 0;

    private ArrayList<MapEntry<K, V>> table;
    private ArrayList<Boolean> isDeletedTable;
    private int tableItems = 0;

    public HashTable() {
        initWithCapacity(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the tables. Useful when rehashing, clearing and constructing
     * @param capacity the size to fill
     */
    private void initWithCapacity(int capacity) {
        table = createTableWithCapacity(capacity, null);
        isDeletedTable = createTableWithCapacity(capacity, false);
        tableItems = 0;
    }

    /**
     * Create an array list with a certain length and default value
     * @param capacity the size to fill
     * @param defaultValue the default value to fill with
     * @return the filled ArrayList
     * @param <T> the value contained in the array list
     */
    private static <T> ArrayList<T> createTableWithCapacity(int capacity, T defaultValue) {
        ArrayList<T> newTable = new ArrayList<T>();
        for(int i = 0; i < capacity; i++)
            newTable.add(defaultValue);
        return newTable;
    }

    /**
     * Removes all mappings from this map.
     *
     * O(table length)
     */
    public void clear() {
        initWithCapacity(DEFAULT_CAPACITY);
        primeIndex = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     *s
     * O(1)
     *
     * @param key - the key being searched for
     * @return true if this map contains the key, false otherwise
     */
    public boolean containsKey(K key) {
        for (int i = 0; i < tableItems; i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = getEntry(slot);

            if (entry == null) {
                return false;
            }

            boolean isKeyEqual = entry.getKey().equals(key);
            if (isKeyEqual) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     *
     * O(table length)
     *
     * @param value - the value being searched for
     * @return true if this map contains one or more keys to the specified value,
     *         false otherwise
     */
    public boolean containsValue(V value) {
        for (int i = 0; i < table.size(); i++) {
            MapEntry<K, V> entry = getEntry(i);
            if (entry == null) {
                continue;
            }

            if (value.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list view of the mappings contained in this map, where the ordering of
     * mappings in the list is insignificant.
     *
     * O(table length)
     *
     * @return a list containing all mappings (i.e., entries) in this map
     */
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> result = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            MapEntry<K, V> entry = getEntry(i);
            if (entry == null) {
                continue;
            }

            result.add(entry);
        }

        return result;
    }

    /**
     * Part one of the double hash
     * @param key the key to hash
     * @return the first hash value
     */
    private int hash1(K key) {
        return Math.abs(key.hashCode()) % table.size();
    }

    /**
     * Part two of the double hash
     * @param key the key to hash
     * @return the second hash value
     */
    private int hash2(K key) {
        int hash = Math.abs(key.hashCode()) % (table.size() - 1);
        return hash + 1;
    }

    /**
     * Perform the double hash operation for a certain index (aka slot)
     * @param key the key to hash
     * @param index the index / slot to use
     * @return the double hash
     */
    private int doubleHash(K key, int index) {
        int hashFirst = hash1(key);
        int hashSecond = hash2(key);
        return (hashFirst + (index * hashSecond)) % table.size();
    }

    /**
     * Gets the value to which the specified key is mapped.
     *
     * O(1)
     *
     * @param key - the key for which to get the mapped value
     * @return the value to which the specified key is mapped, or null if this map
     *         contains no mapping for the key
     */
    public V get(K key) {
        for (int i = 0; i != table.size(); i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = getEntry(slot);
            if (entry == null) {
                return null;
            }

            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     *
     * O(1)
     *
     * @return true if this map contains no mappings, false otherwise
     */
    public boolean isEmpty() {
        return tableItems == 0;
    }

    /**
     * Increase the prime index and approximately double the map
     * This is a heavy operation but luckily it happens rarely
     */
    private void rehash() {
        this.primeIndex++;
        if (primeIndex >= PRIMES.length) {
            throw new IllegalArgumentException("Hash map has max length of " + PRIMES[PRIMES.length - 1]);
        }
        int newSize = PRIMES[this.primeIndex];

        ArrayList<MapEntry<K, V>> oldTable = table;
        ArrayList<Boolean> oldDeleted = isDeletedTable;

        initWithCapacity(newSize);

        for (int i = 0; i < oldTable.size(); i++) {
            MapEntry<K, V> oldEntry = oldTable.get(i);
            boolean isOldDeleted = oldDeleted.get(i);
            if (oldEntry != null && ! isOldDeleted) {
                put(oldEntry.getKey(), oldEntry.getValue());
            }
        }
    }

    /**
     * Calculate the load factor of the hashtable (lambda)
     * @return the load factor 0-1 range
     */
    private float loadFactor() {
        return (float) tableItems / table.size();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     *
     * O(1)
     *
     * @param key - the key for which to update the value (if exists)
     *              or to be added to the table
     * @param value - the value to be mapped to the key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V put(K key, V value) {
        if (loadFactor() > MAX_LOAD_FACTOR) {
            rehash();
        }

        Integer deletedSlot = null;
        for (int i = 0; i < table.size(); i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = table.get(slot);
            boolean isDeleted = isDeletedTable.get(slot);

            if (entry == null) {
                // Use deleted slot if we found one earlier
                int targetSlot = (deletedSlot != null) ? deletedSlot : slot;
                MapEntry<K, V> newEntry = new MapEntry<>(key, value);
                table.set(targetSlot, newEntry);
                isDeletedTable.set(targetSlot, false);
                tableItems++;
                return null;
            }

            if (isDeleted) {
                if (deletedSlot == null) {
                    deletedSlot = slot;
                }
                continue;
            }

            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        throw new IllegalStateException("Table is full! Size: " + table.size() + ", Items: " + tableItems + ", Load: " + loadFactor());
    }


    /** Annoying version I had to make for analysis */
    public int putWithCollisionCount(K key, V value) {
        if (loadFactor() > MAX_LOAD_FACTOR) {
            rehash();
        }

        int collisions = 0;
        Integer deletedSlot = null;

        for (int i = 0; i < table.size(); i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = table.get(slot);
            boolean isDeleted = isDeletedTable.get(slot);

            if (entry == null) {
                int targetSlot = (deletedSlot != null) ? deletedSlot : slot;
                table.set(targetSlot, new MapEntry<>(key, value));
                isDeletedTable.set(targetSlot, false);
                tableItems++;
                return collisions;
            }

            if (isDeleted) {
                if (deletedSlot == null) {
                    deletedSlot = slot;
                }
                collisions++;
                continue;
            }

            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return collisions;
            }

            collisions++;
        }

        throw new IllegalStateException("Table is full! Size: " + table.size() + ", Items: " + tableItems + ", Load: " + loadFactor());
    }


    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * O(1)
     *
     * @param key - the key to be removed
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V remove(K key) {
        for (int i = 0; i != table.size(); i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = getEntry(slot);
            if (entry == null) {
                return null;
            }

            boolean isKeyEqual = entry.getKey().equals(key);
            if (isKeyEqual) {
                isDeletedTable.set(slot, true);
                tableItems--;
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Determines the number of mappings in this map.
     *
     * O(1)
     *
     * @return the number of mappings in this map
     */
    public int size() {
        return tableItems;
    }

    /**
     * Get an entry while respecting lazy deletion
     * @param slot the slot to fetch
     * @return the entry, if available, else null
     */
    private MapEntry<K, V> getEntry(int slot) {
        boolean isDeleted = isDeletedTable.get(slot);
        MapEntry<K, V> entry = table.get(slot);
        return isDeleted ? null : entry;
    }

    /**
     * Create a string from a hashmap
     * @return A string formatted like {(key, value), (key2, value)}
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (MapEntry<K, V> entry : entries()) {
            sj.add(entry.toString());
        }
        return "{" + sj + "}";
    }
}
