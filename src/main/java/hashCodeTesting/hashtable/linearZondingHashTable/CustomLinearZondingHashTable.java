package hashCodeTesting.hashtable.linearZondingHashTable;

import hashCodeTesting.hashtable.linearZondingHashTable.Bucket;
import hashCodeTesting.hashtable.linearZondingHashTable.Condition;


public class CustomLinearZondingHashTable<K, V> {
    private Integer numOfUsedBuckets = 0;
    private Bucket<K, V>[] buckets = new Bucket[100000];


    public void add(K key, V value) {
        Integer index = getBucketIndex(key);
        if (buckets[index] == null) {
            numOfUsedBuckets++;
        }
        buckets[index] = new Bucket<K, V>(key, value);
        if (buckets[buckets.length - 1] != null || numOfUsedBuckets * 1.0 / buckets.length > 0.7) {
            rebuildTable();
        }
    }

    public V delete(K key) {
        Integer index = getBucketIndex(key);
        if (buckets[index] != null) {
            V value = buckets[index].delete();
            if (buckets[index + 1] == null) {
                buckets[index] = null;
            }
            numOfUsedBuckets--;
            return value;
        }
        return null;
    }

    public V get(K key) {
        Integer index = getBucketIndex(key);
        if (buckets[index] != null) {
            return buckets[index].value;
        }
        return null;
    }

    public int getSize() {
        return numOfUsedBuckets;
    }

    private void rebuildTable() {
        numOfUsedBuckets = 0;
        @SuppressWarnings("unchecked") Bucket<K, V>[] tmpBuckets = buckets;
        buckets = new Bucket[tmpBuckets.length * 2];
        for (int i = 0; i < tmpBuckets.length; i++) {
            if (tmpBuckets[i] != null) {
                add(tmpBuckets[i].key, tmpBuckets[i].value);
            }
        }
    }

    public String toString() {
        var res = new StringBuilder();
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null && bucket.isUsed()) {
                res.append(bucket.value.toString());
                res.append(" ");
            }
        }
        return res.toString().trim();
    }

    private Integer getBucketIndex(K key) {
        int index = (key.hashCode() & 0x7fffffff) % buckets.length;
        while (buckets[index] != null) {
            Bucket<K, V> bucket = buckets[index];
            if (bucket.containsKey(key)) {
                return index;
            }
            index++;
        }
        return index;
    }
}
