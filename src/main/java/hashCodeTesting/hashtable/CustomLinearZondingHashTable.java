package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;


public class CustomLinearZondingHashTable<K, V> {
    private Integer numOfUsedBuckets = 0;
    private Bucket<K, V>[] buckets = new Bucket[100000];


    public void add(K key, V value) {
        Integer index =getBucketIndex(key);
        if(buckets[index] ==null){
            numOfUsedBuckets ++;
        }
        buckets[index] = new Bucket<K, V>(key, value);
        if(buckets[buckets.length-1] != null || numOfUsedBuckets * 1.0 / buckets.length > 0.7){
            rebuildTable();
        }
    }

    public V delete(K key) {
        Integer index = getBucketIndex(key);
        if (buckets[index] != null) {
            V value = buckets[index].delete();
            if (buckets[index+1] == null) {
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
            return buckets[index].getValue();
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
                add(tmpBuckets[i].getKey(), tmpBuckets[i].getValue());
            }
        }
    }

    public String toString() {
        var res = new StringBuilder();
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null && bucket.getCondition().equals(Condition.USED)) {
                res.append(bucket.getValue().toString());
                res.append(" ");
            }
        }
        return res.toString().trim();
    }
    private Integer getBucketIndex(K key) {
        int index = (key.hashCode() & 0x7fffffff) % buckets.length;
        while (buckets[index] != null) {
            Bucket<K, V> bucket = buckets[index];
            if(checkIfKeyExist(bucket, key)){
                return index;
            }
            index++;
        }
        return index;
    }
    private boolean checkIfKeyExist(Bucket<K,V> bucket, K key) {
        if (bucket.getKey().equals(key) && bucket.getCondition().equals(Condition.USED) ) {
            return true;
        }
        return false;
    }

}
