package hashCodeTesting.hashtable.linearZondingHashTable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Bucket<K, V> {
	private K key;
	private V value;
	private Condition condition;

	public Bucket(K key, V value) {
		this.key = key;
		this.value = value;
		condition = Condition.USED;
	}

	public void add(K key, V value) {
		this.key = key;
		this.value = value;
		condition = Condition.USED;
	} 
	public V delete() {
		condition = Condition.DELETED;
		return value;
	}
}
