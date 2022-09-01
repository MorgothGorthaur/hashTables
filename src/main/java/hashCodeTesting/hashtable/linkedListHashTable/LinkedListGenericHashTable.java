package hashCodeTesting.hashtable.linkedListHashTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.ObjDoubleConsumer;

public class LinkedListGenericHashTable<K, V> {

    private int numOfUsedBuckets = 0;
    @SuppressWarnings("unchecked")
    private GenericList[] buckets = new GenericList[100000];

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

    private void rebuildTable() {
        GenericList<K,V>[] tmp = Arrays.stream(buckets).filter(Objects::nonNull).toArray(GenericList[] ::new);
        buckets = new GenericList[buckets.length * 2];
        for(GenericList<K,V> bucketList : tmp){
            KeyEndValue<K,V> elem = bucketList.deleteFirst();
            while (elem != null){
                add(elem.getKey(), elem.getValue());
                elem = bucketList.deleteFirst();
            }
        }
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                size += buckets[i].size();
            }
        }
        return size;
    }
}
