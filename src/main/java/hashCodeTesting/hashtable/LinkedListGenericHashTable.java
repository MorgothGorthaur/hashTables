package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.list.GenericElement;
import hashCodeTesting.hashtable.list.GenericList;

public class LinkedListGenericHashTable<K, V> {
	private int length;
	private int size;
	private GenericList<K, V>[] buckets;

	@SuppressWarnings("unchecked")
	public LinkedListGenericHashTable() {
		length = 128;
		size = 0;
		buckets = new GenericList[length];
		for (int i = 0; i < length; i++) {
			buckets[i] = new GenericList<K, V>();
		}

	}

	private int getHash(K key) {
		return (key.hashCode() & 0x7fffffff) % length;
	}

	public void add(K key, V value) {
		int hash = getHash(key);
		if (!buckets[hash].contains(key)) {
			buckets[hash].add(key, value);
			size++;
		}
		if ((long) length / size < 1.2) {
			rebuildTable();
		}
	}

	public void delete(K key) {
		int hash = getHash(key);
		if (buckets[hash].size() == 0) {
			System.out.println("no found!");
		}
		if (buckets[hash].size() == 1) {
			buckets[hash].deleteFirst();

		}
		size -= 1;
		if (buckets[hash].size() > 1) {
			System.out.println("collision! length = " + buckets[hash].size());
			buckets[hash].delete(key);

		}

	}

	public String get(K key) {

		String val = "";
		int hash = getHash(key);
		if (buckets[hash].size() == 0) {
			val = "no found!";
		}
		if (buckets[hash].size() == 1) {
			val = buckets[hash].getFirst();

		}
		if (buckets[hash].size() > 1) {
			System.out.println("collision! length = " + buckets[hash].size());
			return buckets[hash].get(key);

		}
		return val;
	}

	public void getInfo() {
		int badBucketsSize = 0;
		int badBucketsLength = 0;
		for (int i = 0; i < size; i++) {
			if (buckets[i].size() > 1) {
				badBucketsSize++;
				badBucketsLength += buckets[i].size();
			}
		}
		System.out.print("bad buckets num = " + badBucketsSize);
		System.out.println(" " + 100.0 * badBucketsSize / length + "%");
		System.out.print("bad buckets length = " + badBucketsLength);
		System.out.println(" " + 100.0 * badBucketsLength / size + "%");
	}

	@SuppressWarnings("unchecked")
	private void rebuildTable() {
		GenericList<K, V> elems = new GenericList<>();
		for (GenericList<K, V> bucket : buckets) {
			elems.addAll(bucket);
		}
		this.length *= 2;
		this.buckets = new GenericList[length];

		for (int i = 0; i < length; i++) {
			this.buckets[i] = new GenericList<K, V>();
		}
		for (int i = 0; i < size; i++) {
			GenericElement<K, V> elem = elems.deleteFirst();
			int hash = getHash(elem.getKey());
			buckets[hash].add(elem.getKey(), elem.getValue());
		}
	}
}
