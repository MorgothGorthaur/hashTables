package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;


public class CustomLinearZondingHashTable<K, V> {
    private Integer numOfUsedBuckets = 0;
    private Bucket<K, V>[] buckets = new Bucket[100000];

    private int hashFunction(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    private Integer getBucketIndex(K key) {
        int index = hashFunction(key);
        while (!checkIfBucketEmpty(index)) {
            Bucket<K, V> bucket = buckets[index];
            if (bucket.getKey().equals(key) && bucket.getCondition().equals(Condition.USED)) {
                return index;
            }
            index++;
        }
        return null;
    }

    public void add(K key, V value) {
        int hash = hashFunction(key);
        int index = hash;
        while (!checkIfBucketEmpty(index) && !checkIfKeyExist(index, key)) {
            index++;
        }
        buckets[index] = new Bucket<K, V>(key, value);
        numOfUsedBuckets++;
        if(!checkIfBucketEmpty(buckets.length -1) || numOfUsedBuckets * 1.0 / buckets.length > 0.7){
            rebuildTable();
        }
    }

    public V delete(K key) {
        Integer index = getBucketIndex(key);
        if (index != null) {
            V value = buckets[index].delete();
            if (checkIfBucketEmpty(index+1)) {
                buckets[index] = null;
            }
            numOfUsedBuckets--;
            return value;
        }
        return null;
    }

    public V get(K key) {
        Integer index = getBucketIndex(key);
        if (index != null) {
            return buckets[index].getValue();
        }
        return null;
    }

    private boolean checkIfKeyExist(int index, K key) {
        if (buckets[index].getCondition().equals(Condition.USED) && buckets[index].getKey().equals(key)) {
            numOfUsedBuckets--;
            return true;
        }
        return false;
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

    public int getSize() {
        return numOfUsedBuckets;
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
    private boolean checkIfBucketEmpty(int index){
        if(buckets[index] == null){
            return true;
        } else {
            return false;
        }
    }
}
