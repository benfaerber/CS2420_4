package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
    private static final int[] PRIMES = new int[] {
            101, 211, 431, 863, 1733, 3467, 6947, 13901, 27803,
            55609, 111227, 222461, 444929, 889871, 1779761
    };
    private static final int DEFAULT_CAPACITY = PRIMES[0];

    private int primeIndex = 0;

    private ArrayList<MapEntry<K, V>> table;
    private int tableItems = 0;

    public HashTable() {
        table = this.createTableWithCapacity(DEFAULT_CAPACITY);
    }

    private ArrayList<MapEntry<K, V>> createTableWithCapacity(int capacity) {
        table = new ArrayList<>();
        for(int i = 0; i < capacity; i++)
            table.add(null);
        return table;
    }

    public void clear() {
        this.table = this.createTableWithCapacity(DEFAULT_CAPACITY);
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

    public V get(K key) {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    private void rehash() {
        this.primeIndex++;
        if (primeIndex >= PRIMES.length) {
            throw new IllegalArgumentException("Hash map has max length of " + PRIMES[PRIMES.length - 1]);
        }
        int newSize = PRIMES[this.primeIndex];

        table = this.createTableWithCapacity(newSize);
        tableItems = 0;
    }

    public V put(K key, V value) {
        int hash = key.hashCode() % table.size();
        throw new UnsupportedOperationException();
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        throw new UnsupportedOperationException();
    }
}
