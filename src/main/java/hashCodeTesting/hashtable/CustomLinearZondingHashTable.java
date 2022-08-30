package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CustomLinearZondingHashTable<K, V> {
    private Integer clustersSize = 0;
    private Bucket<K, V>[] buckets = new Bucket[100000];
    //private ArrayList<Bucket<K,V>> buckets = new ArrayList<>();
    private  int hashFunction(K key){
        return  (key.hashCode() & 0x7fffffff) % buckets.length;
    }
    private Integer getBucketIndex(K key) {
       int index =  hashFunction(key);
        while (buckets[index] != null) {
            if (buckets[index].getKey().equals(key) && buckets[index].getCondition().equals(Condition.USED)) {
                return index;
            }
            index++;
        }
        return null;
    }

    public void add(K key, V value) {
        Integer index = hashFunction(key);
        while (buckets[index] != null && !buckets[index].getKey().equals(key)) {
            index++;
        }
        updateClusterSize(index != getBucketIndex(key));
        buckets[index] = new Bucket<K, V>(key, value);
        checkIfTableNeedsToBeRebuild(buckets[buckets.length- 1] != null || getSize() * 1.0 / buckets.length > 0.7 || 1.0 * clustersSize / buckets.length > 0.2);
    }

    private void checkIfTableNeedsToBeRebuild(boolean buckets) {
        if (buckets) {
            rebuildTable();
        }
    }

    private void updateClusterSize(boolean hash) {
        if (hash) {
            clustersSize++;
        }
    }
    private void rebuildTable() {
        clustersSize = 0;
        @SuppressWarnings("unchecked") Bucket<K, V>[] update = new Bucket[buckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i].getCondition().equals(Condition.USED)) {
                int hash = getBucketIndex(buckets[i].getKey());
                while (update[hash] != null) {
                    hash++;
                }
                updateClusterSize(hash != getBucketIndex(buckets[i].getKey()));
                update[hash] = new Bucket<>(buckets[i].getKey(), buckets[i].getValue());
            }
        }
        buckets = update;
        checkIfTableNeedsToBeRebuild(buckets[buckets.length - 1] != null);
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                size++;
            }
        }
        return size;
    }
    public V delete(K key) {
       Integer index = getBucketIndex(key);
       if(index != null){
           V value = buckets[index].delete();
           if (buckets[index+1] == null){
               buckets[index] = null;
           }
           return value;
       }
       return null;
    }
    public V get(K key) {
        Integer index = getBucketIndex(key);
        if(index != null){
            return buckets[index].getValue();
        }
        return null;
    }

    public void print() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i].getCondition().equals(Condition.USED)) {
                System.out.println(i + " " + buckets[i].getValue().toString());
            }
        }
    }
}
