package hashCodeTesting.hashtable.linearZondingHashTable;


import lombok.Getter;
import lombok.Setter;


class Bucket<K, V> {
	K key;
	V value;
	Condition condition;

	Bucket(K key, V value) {
		this.key = key;
		this.value = value;
		condition = Condition.USED;
	}

	void add(K key, V value) {
		this.key = key;
		this.value = value;
		condition = Condition.USED;
	} 
	V delete() {
		condition = Condition.DELETED;
		return value;
	}
	boolean isUsed(){
		return this.condition.equals(Condition.USED);
	}
	public boolean containsKey(K key){
		return this.condition.equals(Condition.USED) && this.key.equals(key);
	}
}
