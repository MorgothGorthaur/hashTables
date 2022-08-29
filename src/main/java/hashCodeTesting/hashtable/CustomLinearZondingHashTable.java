package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomLinearZondingHashTable<K, V> {
    private Integer length;
    private Integer clustersSize;
    private Bucket<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public CustomLinearZondingHashTable() {
        length = 1000000;
        clustersSize = 0;
        buckets = new Bucket[length];
    }

    public int getHash(K key) {
        return (key.hashCode() & 0x7fffffff) % length;
    }

    public void add(K key, V value) {
        int hash = getHash(key);

        while (buckets[hash] != null && !buckets[hash].getKey().equals(key)) {
            hash++;
        }
        if (hash != getHash(key)) {

            clustersSize++;
        }

        buckets[hash] = new Bucket<K, V>(key, value);

        if (buckets[length - 1] != null || getSize() * 1.0 / length > 0.7 || 1.0 * clustersSize / length > 0.2) {
            rebuildTable();
        }
    }

    private void rebuildTable() {
        System.out.println("clusters size before rebuild = " + clustersSize);
        length *= 4;

        clustersSize = 0;
        System.out.println("new length = " + length);
        @SuppressWarnings("unchecked") Bucket<K, V>[] update = new Bucket[length];
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i].getCondition().equals(Condition.USED)) {
                int hash = getHash(buckets[i].getKey());
                while (update[hash] != null) {
                    hash++;
                }
                if (hash != getHash(buckets[i].getKey())) {
                    clustersSize++;
                }

                update[hash] = new Bucket<>(buckets[i].getKey(), buckets[i].getValue());
            }
        }

        buckets = update;
        if (buckets[length - 1] != null) {
            rebuildTable();
        }

        System.out.println("clusters size after rebuild = " + clustersSize);
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < length; i++) {
            if (buckets[i] != null) {
                size++;
            }
        }

        return size;
    }

    public void delete(K key) {
        int hash = getHash(key);
        while (buckets[hash] != null) {
            if (buckets[hash].getKey().equals(key) && buckets[hash].getCondition().equals(Condition.USED)) {
                buckets[hash].delete();
                if (buckets[hash + 1] == null) {
                    buckets[hash] = null;
                    break;
                }
            }
            hash++;
        }
        if(hash == length) {
            System.out.println("not found!");
        }
    }

    public String get(K key) {
        int hash = getHash(key);
        while (buckets[hash] != null) {
            if (buckets[hash].getKey().equals(key) && buckets[hash].getCondition().equals(Condition.USED)) {
                return buckets[hash].getKey().toString();
            }
            hash++;
        }
        return "not found!";
    }

    public void print() {
        for (int i = 0; i < length; i++) {
            if (buckets[i] != null && buckets[i].getCondition().equals(Condition.USED)) {
                System.out.println(i + " " + buckets[i].getValue().toString());
            }
        }
    }
}
