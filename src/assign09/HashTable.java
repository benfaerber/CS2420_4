package assign09;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
        table = createTableWithCapacity(DEFAULT_CAPACITY, null);
        isDeletedTable = createTableWithCapacity(DEFAULT_CAPACITY, false);
    }

    private void initWithCapacity(int capacity) {
        table = createTableWithCapacity(capacity, null);
        isDeletedTable = createTableWithCapacity(capacity, false);
        tableItems = 0;
    }

    private static <T> ArrayList<T> createTableWithCapacity(int capacity, T defaultValue) {
        ArrayList<T> newTable = new ArrayList<T>();
        for(int i = 0; i < capacity; i++)
            newTable.add(defaultValue);
        return newTable;
    }

    public void clear() {
        initWithCapacity(DEFAULT_CAPACITY);
        primeIndex = 0;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

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

    private int hash1(K key) {
        return Math.abs(key.hashCode()) % table.size();
    }

    private int hash2(K key) {
        int hash = Math.abs(key.hashCode()) % (table.size() - 1);
        return hash + 1;
    }

    private int doubleHash(K key, int index) {
        int hashFirst = hash1(key);
        int hashSecond = hash2(key);
        return (hashFirst + (index * hashSecond)) % table.size();
    }

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

    public boolean isEmpty() {
        return tableItems == 0;
    }

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

    private float loadFactor() {
        return (float) tableItems / table.size();
    }

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

    public int size() {
        return tableItems;
    }

    private MapEntry<K, V> getEntry(int slot) {
        boolean isDeleted = isDeletedTable.get(slot);
        MapEntry<K, V> entry = table.get(slot);
        return isDeleted ? null : entry;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (MapEntry<K, V> entry : entries()) {
            sj.add(entry.toString());
        }
        return "{" + sj + "}";
    }
}
