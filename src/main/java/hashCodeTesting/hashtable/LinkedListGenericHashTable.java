package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.list.GenericElement;
import hashCodeTesting.hashtable.list.GenericList;

import java.util.ArrayList;

public class LinkedListGenericHashTable<K, V> {
    private int length = 128;
    private int numOfUsedBuckets = 0;
    private ArrayList<GenericList<K, V>> buckets = new ArrayList<>();

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % length;
    }

    public void add(K key, V value) {
        GenericList<K, V> bucket = getBucketByKey(key);
        if (!bucket.contains(key)) {
            bucket.add(key, value);
            numOfUsedBuckets++;
        }
        if ((long) length / numOfUsedBuckets < 1.2) {
            rebuildTable();
        }
    }

    private GenericList<K, V> getBucketByKey(K key) {
        int hash = getBucketIndex(key);
        return buckets.get(hash);
    }

    public void delete(K key) {
        GenericList<K, V> bucket = getBucketByKey(key);
        if (bucket.deleteFromList(key)) {
            numOfUsedBuckets -= 1;
        }
    }

    public String get(K key) {
        GenericList<K, V> bucket = getBucketByKey(key);
        return bucket.get(key);
    }

    public void getInfo() {
        int badBucketsSize = 0;
        int badBucketsLength = 0;
        for (int i = 0; i < numOfUsedBuckets; i++) {
            if (buckets.get(i).size() > 1) {
                badBucketsSize++;
                badBucketsLength += buckets.get(i).size();
            }
        }
        System.out.print("bad buckets num = " + badBucketsSize);
        System.out.println(" " + 100.0 * badBucketsSize / length + "%");
        System.out.print("bad buckets length = " + badBucketsLength);
        System.out.println(" " + 100.0 * badBucketsLength / numOfUsedBuckets + "%");
    }

    private void rebuildTable() {
        GenericList<K, V> elems = new GenericList<>();
        for (GenericList<K, V> bucket : buckets) {
            elems.addAll(bucket);
        }
        this.length *= 2;
        this.buckets = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            this.buckets.set(i, new GenericList<K, V>());
        }
        for (int i = 0; i < numOfUsedBuckets; i++) {
            GenericElement<K, V> elem = elems.deleteFirst();
            int hash = getBucketIndex(elem.getKey());
            buckets.get(hash).add(elem.getKey(), elem.getValue());
        }
    }
}
