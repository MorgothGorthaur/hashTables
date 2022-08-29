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
		length = 16;
		clustersSize = 0;
		buckets = new Bucket[length];
		for (int i = 0; i < length; i++) {
			buckets[i] = new Bucket<K, V>();
		}
	}

	public int getHash(K key) {
		return (key.hashCode() & 0x7fffffff) % length;
	}

	public void add(K key, V value) {
		int hash = getHash(key);
		while (!buckets[hash].getCondition().equals(Condition.FREE) && !buckets[hash].getKey().equals(key)) {
			hash++;
		}
		if(hash != getHash(key)) {
			clustersSize ++;
		}
		buckets[hash].add(key, value);
		if (!buckets[length - 1].getCondition().equals(Condition.FREE) || getSize() * 1.0 / length > 0.7 || 1.0 * clustersSize / length > 0.1 ) {
			System.out.println(buckets[length - 1].getCondition());
			rebuildTable();

		}
	}

	private void rebuildTable() {
		System.out.println("clusters size before rebuild = " + clustersSize);
		length = (int) Math.pow(length, 2);
		clustersSize = 0;
		System.out.println("new length = " + length);
		@SuppressWarnings("unchecked")
		Bucket<K, V>[] update = new Bucket[length];
		for (int i = 0; i < length; i++) {
			update[i] = new Bucket<K, V>();
		}
		for(int i = 0; i < buckets.length; i++) {
			if(buckets[i].getCondition().equals(Condition.USED)) {
				int hash = getHash(buckets[i].getKey());
				while(!update[hash].getCondition().equals(Condition.FREE)) {
					hash ++;
				}
				if(hash!= getHash(buckets[i].getKey())) {
					clustersSize++;
				}
				update[hash].add(buckets[i].getKey(), buckets[i].getValue());
			}
		}
		
		buckets = update;
		if (!buckets[length - 1].getCondition().equals(Condition.FREE)) {
			rebuildTable();
		}
		System.out.println(buckets[length - 1].getCondition());
		System.out.println("clusters size after rebuild = " + clustersSize);
	}

	public int getSize() {
		int size = 0;
		for (int i = 0; i < length; i++) {
			if (!buckets[i].getCondition().equals(Condition.FREE)) {
				size++;
			}
		}
		
		return size;
	}
	
	public void delete(K key) {
		int hash = getHash(key);
		while (!buckets[hash].getCondition().equals(Condition.FREE)) {
			if (buckets[hash].getKey().equals(key) && buckets[hash].getCondition().equals(Condition.USED)) {
				buckets[hash].delete();
				if (buckets[hash + 1].getCondition().equals(Condition.FREE)) {
					buckets[hash].setCondition(Condition.FREE);
				}
			}
			hash++;
		}
		System.out.println("not found!");
	}

	public String get(K key) {
		int hash = getHash(key);
		while (!buckets[hash].getCondition().equals(Condition.FREE)) {
			if (buckets[hash].getKey().equals(key) && buckets[hash].getCondition().equals(Condition.USED)) {
				return buckets[hash].getKey().toString();
			}
			hash++;
		}
		return "not found!";
	}

	public void print() {
		for (int i = 0; i < length; i++) {
			if (buckets[i].getCondition().equals(Condition.USED)) {
				System.out.println(i + " " + buckets[i].getValue().toString());
			}
		}
	}
}
