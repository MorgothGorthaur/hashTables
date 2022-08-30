package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomLinearZondingHashTable<K, V> {
    private Integer numOfUsedBuckets = 0;
    private Bucket<K, V>[] buckets = new Bucket[100000];

    private int hashFunction(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    private Integer getBucketIndex(K key) {
        int index = hashFunction(key);
        while (buckets[index] != null) {
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
        while (buckets[index] != null && !checkIfKeyExist(index, key)) {
            index++;
        }
        //updateClusterSize(index != hash);
        buckets[index] = new Bucket<K, V>(key, value);
        numOfUsedBuckets ++;
        checkIfTableNeedsToBeRebuild(buckets[buckets.length - 1] != null
                || numOfUsedBuckets * 1.0 / buckets.length > 0.7) ;
    }

    private void checkIfTableNeedsToBeRebuild(boolean bool) {
        if (bool) {
            rebuildTable();
        }
    }
    private boolean checkIfKeyExist(int index, K key){
        if(buckets[index].getCondition().equals(Condition.USED) && buckets[index].getKey().equals(key)){
            numOfUsedBuckets --;
            return true;
        }
        return false;
    }
    private void rebuildTable() {
        numOfUsedBuckets = 0;
        @SuppressWarnings("unchecked") Bucket<K, V>[] tmpBuckets = buckets;
        buckets = new Bucket[tmpBuckets.length *2];
        for (int i = 0; i < tmpBuckets.length; i++){
            if (tmpBuckets[i] != null){
                add(tmpBuckets[i].getKey(), tmpBuckets[i].getValue());
            }
        }
    }

    public int getSize() {
        return numOfUsedBuckets;
    }

    public V delete(K key) {
        Integer index = getBucketIndex(key);
        if (index != null) {
            V value = buckets[index].delete();
            if (index + 1 != buckets.length && buckets[index + 1] == null) {
                buckets[index] = null;
            }
            numOfUsedBuckets --;
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
}
