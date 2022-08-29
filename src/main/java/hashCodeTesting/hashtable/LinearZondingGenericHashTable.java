package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinearZondingGenericHashTable<K, V> {
//	private int length;
//	private int size;
//	private Bucket<K, V>[] buckets;
//
//	@SuppressWarnings("unchecked")
//	public LinearZondingGenericHashTable() {
//		length = 8;
//		buckets = new Bucket[length];
//		for (int i = 0; i < length; i++) {
//			buckets[i] = new Bucket<K, V>();
//		}
//	}
//
//	public int getHash(K key) {
//		return (key.hashCode() & 0x7fffffff) % length;
//	}
//
//	public Integer recursiveFind(int position, K key) {
//
//		if (position < length) {
//			if (!buckets[position].getCheckEmpty() && buckets[position].getKey().equals(key)) {
//					return position;
//			}
//			return recursiveFind(position+=1, key);
//		}
//		return null;
//		
//		
//	}
//
//	public void add(K key, V value) {
//		int hash = getHash(key);
//		while (!buckets[hash].getCheckEmpty()) {
//			if (buckets[hash].getKey().equals(key)) {
//				buckets[hash].setValue(value);
//				size -= 1;
//				break;
//			}
//			hash++;
//		}
//		buckets[hash].add(key, value);
//		size += 1;
//		if (1.0 * length / size < 1.2 || !buckets[length - 1].getCheckEmpty()) {
//			rebuildTable();
//		}
//
//	}
//
//	public String get(K key) {
//		int hash = getHash(key);
//		if (!buckets[hash].getCheckEmpty()) {
//			if (buckets[hash].getKey().equals(key)) {
//				return buckets[hash].getValue().toString();
//			}
//		}
//		Integer pos = recursiveFind(hash, key);
//		if( pos != null) {
//			return buckets[pos].getValue().toString(); 
//		} else {
//			return "not found!";
//		}
//	}
//
//	public void delete(K key) {
//		int hash = getHash(key);
//		for (int i = hash; i < length; i++) {
//			if (!buckets[i].getCheckEmpty()) {
//				if (buckets[i].getKey().equals(key)) {
//					buckets[i].delete();
//					size -= 1;
//					i = length;
//				}
//			}
//
//		}
//
//	}
//
//	public void rebuildTable() {
//		length *= 2;
//		@SuppressWarnings("unchecked")
//		Bucket<K, V>[] update = new Bucket[length];
//		for (int i = 0; i < length; i++) {
//			update[i] = new Bucket<K, V>();
//		}
//		for (int i = 0; i < length / 2; i++) {
//			if (!buckets[i].getCheckEmpty()) {
//				int hash = getHash(buckets[i].getKey());
//				while (!update[hash].getCheckEmpty()) {
//					hash++;
//				}
//				update[hash].add(buckets[i].getKey(), buckets[i].getValue());
//			}
//		}
//		this.buckets = update;
//		if (!buckets[length - 1].getCheckEmpty()) {
//			rebuildTable();
//		}
//	}
//
//	public void print() {
//		for (int i = 0; i < length; i++) {
//			if (!buckets[i].getCheckEmpty()) {
//				System.out.println(i + " " + buckets[i].getValue().toString());
//			}
//		}
//	}
//
//	public void getInfo() {
//		int num = 0;
//		int len = 0;
//		boolean dub = false;
//		Bucket<K, V>[] checkBuckets = buckets;
//		for (int i = 0; i < length; i++) {
//			if (!checkBuckets[i].getCheckEmpty()) {
//
//				for (int j = i + 1; j < length; j++) {
//					if (checkBuckets[j].getCheckEmpty()) {
//						j++;
//					} else if (getHash(checkBuckets[j].getKey()) == getHash(checkBuckets[i].getKey())) {
//						checkBuckets[j].setCheckEmpty(true);
//						num++;
//						if (!dub) {
//							len++;
//							num++;
//							dub = true;
//						}
//
//						// System.out.println("f");
//					}
//
//				}
//				dub = false;
//			}
//		}
//		System.out.println("Num of dublicates hashes = " + len);
//		System.out.println("Num of bad hashes = " + num);
//
//	}

}
