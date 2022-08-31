package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.list.GenericElement;
import hashCodeTesting.hashtable.list.GenericList;

public class LinkedListGenericHashTable<K, V> {
    private int length = 128;
    private int numOfUsedBuckets = 0;
    private int tableSize = 0;
    @SuppressWarnings("unchecked")
    private GenericList<K, V>[] buckets = new GenericList[length];

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % length;
    }

    public int size() {
        return tableSize;
    }

    public void add(K key, V value) {
        GenericList<K, V> bucket = getBucketByKey(key);

        bucket.add(key, value);
        if ((long) length / numOfUsedBuckets < 1.2) {
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
        if (bucket != null ) {
            V deleted = bucket.delete(key);
            if(deleted != null) {
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
//        GenericList<K, V> elems = new GenericList<>();
//        for (GenericList<K, V> bucket : buckets) {
//            elems.addAll(bucket);
//        }
//        this.length *= 2;
//        this.buckets = new GenericList[this.length];
//
//        for (int i = 0; i < length; i++) {
//            this.buckets[i] = new GenericList<>();
//        }
//        for (int i = 0; i < numOfUsedBuckets; i++) {
//            GenericElement<K, V> elem = elems.deleteFirst();
//            getBucketByKeyMaybe(elem.getKey()).add(elem.getKey(), elem.getValue());
//        }
    }
}
