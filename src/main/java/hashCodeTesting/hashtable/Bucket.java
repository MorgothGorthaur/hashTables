package hashCodeTesting.hashtable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Bucket<K, V> {
	private K key;
	private V value;
	private Condition condition;

	public Bucket() {
		condition = Condition.FREE;
	}

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
	public void delete() {
		condition = Condition.DELETED;
	}
}
