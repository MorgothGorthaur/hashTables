package hashCodeTesting.hashtable.linkedListHashTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.ObjDoubleConsumer;

public class LinkedListGenericHashTable<K, V> {

    private int numOfElements = 0;
    @SuppressWarnings("unchecked")
    private GenericList[] buckets = new GenericList[100000];

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void add(K key, V value) {
        GenericList<K, V> bucket = getBucketByKey(key);
        int listSizeBeforeAdd = bucket.size();
        bucket.addOrReplace(key, value);
        numOfElements += bucket.size() - listSizeBeforeAdd;
        if (1.0 * buckets.length / numOfElements < 1.2) {
            rebuildTable();
        }
    }

    private GenericList<K, V> getBucketByKey(K key) {
        int hash = getBucketIndex(key);
        if (buckets[hash] == null) {
            buckets[hash] = new GenericList<>();

        }
        return buckets[hash];
    }

    private GenericList<K, V> getBucketByKeyMaybe(K key) {
        int hash = getBucketIndex(key);
        return buckets[hash];
    }

    public V delete(K key) {
        GenericList<K, V> bucket = getBucketByKeyMaybe(key);
        if (bucket != null) {
            V deleted = bucket.delete(key);
            if (deleted != null) {
                numOfElements--;
            }
            return deleted;
        }
        return null;
    }

    public V get(K key) {
        GenericList<K, V> bucket = getBucketByKeyMaybe(key);
        return bucket == null ? null : bucket.get(key);
    }

    private void rebuildTable() {
        GenericList<K, V> tmp = new GenericList<>();
        for (int i = 0; i < buckets.length; i++) {
            tmp.addAll(buckets[i]);
        }
        buckets = new GenericList[buckets.length * 2];
        KeyEndValue<K, V> kvKeyEndValue = tmp.deleteFirst();
        while (kvKeyEndValue != null) {
            K key = kvKeyEndValue.getKey();
            V value = kvKeyEndValue.getValue();
            add(key, value);
            kvKeyEndValue = tmp.deleteFirst();
        }

    }

    public int size() {
        return numOfElements;
    }
}
