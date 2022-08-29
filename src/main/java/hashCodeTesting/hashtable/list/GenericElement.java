package hashCodeTesting.hashtable.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericElement <K, V>{
	
	K key;
	V value;
	GenericElement <K, V> next = null;
	GenericElement <K, V> last = null;
	public GenericElement(K key, V value) {
		this.key = key;
		this.value = value; 
	}
	public GenericElement() {
		
	}
}
