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

    private static <T> ArrayList<T> createTableWithCapacity(int capacity, T defaultValue) {
        ArrayList<T> newTable = new ArrayList<T>();
        for(int i = 0; i < capacity; i++)
            newTable.add(defaultValue);
        return newTable;
    }

    public void clear() {
        this.table = createTableWithCapacity(DEFAULT_CAPACITY, null);
        this.isDeletedTable = createTableWithCapacity(DEFAULT_CAPACITY, false);
    }

    public boolean containsKey(K key) {
        throw new UnsupportedOperationException();
    }

    public boolean containsValue(V value) {
        throw new UnsupportedOperationException();
    }

    public List<MapEntry<K, V>> entries() {
        throw new UnsupportedOperationException();
    }

    private int hash1(K key) {
        return Math.abs(key.hashCode()) % table.size();
    }

    private int hash2(K key) {
        int underPrime = 79;
        int selectedPrime = primeIndex == 0 ? underPrime : PRIMES[primeIndex - 1];
        return selectedPrime + (Math.abs(key.hashCode()) % selectedPrime);
    }

    private int doubleHash(K key, int index) {
        int hashFirst = hash1(key);
        int hashSecond = hash2(key);
        return (hashFirst + (index * hashSecond)) % table.size();
    }

    public V get(K key) {
        for (int i = 0; i != table.size(); i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = table.get(slot);

            if (entry == null) {
                return null;
            }

            boolean isDeleted = isDeletedTable.get(slot);
            if (! isDeleted && entry.getKey().equals(key)) {
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

        table = createTableWithCapacity(newSize, null);
        isDeletedTable = createTableWithCapacity(newSize, false);



        tableItems = 0;

    }

    private float loadFactor() {
        float filled = 0;
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                filled++;
            }
        }

        return filled / table.size();
    }

    public V put(K key, V value) {
        if (loadFactor() > MAX_LOAD_FACTOR) {
            rehash();
        }

        for (int i = 0; i < table.size(); i++) {
            int slot = doubleHash(key, i);
            MapEntry<K, V> entry = table.get(slot);

            if (entry == null || isDeletedTable.get(slot)) {
                table.set(slot, new MapEntry<>(key, value));
                isDeletedTable.set(slot, false);
                tableItems++;
                return null;
            }

            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        throw new IllegalStateException("Table is full!");
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return tableItems;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < table.size(); i++) {
            boolean isDeleted = isDeletedTable.get(i);
            MapEntry<K, V> entry = table.get(i);
            if (entry != null && !isDeleted) {
                sj.add(entry.toString());
            }
        }
        return "{" + sj + "}";
    }
}
