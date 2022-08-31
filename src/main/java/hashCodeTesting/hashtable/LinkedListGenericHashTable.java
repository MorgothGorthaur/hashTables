package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.list.GenericElement;
import hashCodeTesting.hashtable.list.GenericList;

public class LinkedListGenericHashTable<K, V> {

    private int numOfUsedBuckets = 0;
    @SuppressWarnings("unchecked")
    private GenericList<K, V>[] buckets = new GenericList[100000];

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void add(K key, V value) {
        GenericList<K, V> bucket = getBucketByKey(key);
        bucket.add(key, value);
        if (1.0 * buckets.length / numOfUsedBuckets < 1.2) {
            rebuildTable();
        }
    }

    private GenericList<K, V> getBucketByKey(K key) {
        int hash = getBucketIndex(key);
        if (buckets[hash] == null) {
            buckets[hash] = new GenericList<>();
            numOfUsedBuckets++;
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
                numOfUsedBuckets -= 1;
            }
            return deleted;
        }
        return null;
    }

    public V get(K key) {
        GenericList<K, V> bucket = getBucketByKeyMaybe(key);
        return bucket == null ? null : bucket.get(key);
    }

    public void getInfo() {
    }

    private void rebuildTable() {
        GenericList<K, V>[] tmp = buckets;
        buckets = new GenericList[buckets.length * 2];
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != null) {
                GenericElement<K, V> elem = tmp[i].deleteFirst();
                while (elem != null) {
                    add(elem.getKey(), elem.getValue());
                    elem = tmp[i].deleteFirst();

                }
            }
        }

    }
}
