package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.list.CustomList;

public class CustomHashTable {
	private int length;
	private int size;
	private CustomList[] buckets;
	public CustomHashTable() {
		length = 1000;
		size = 0;
		buckets = new CustomList[length];
		for(int i = 0; i < length; i++) {
			buckets[i]= new CustomList();
		}
	}
	private int getHash(Integer key) {
		return key.hashCode() % length;
	}
	public void add(Integer key, String value) {
		int hash = getHash(key);
		if( !buckets[hash].contains(key)) {
			buckets[hash].add(key, value);
			size++;
		}
		if((long) length /size > 1.2) {
			CustomList[] updated = new CustomList[length *2];
			for (int i = 0; i < length; i++) {
				//updated 
			}
		}
		//buckets[hash].add(value, key);
	}
	public String get(Integer key) {
		int hash = getHash(key);
		if(buckets[hash].size() == 0) {
			return "no found!";
		}
		if(buckets[hash].size() == 1) {
			return buckets[hash].getFirst();
		} else {
			System.out.println("collision!" + buckets[hash].size());
			return buckets[hash].get(key);
		}
		
	}
	
}
