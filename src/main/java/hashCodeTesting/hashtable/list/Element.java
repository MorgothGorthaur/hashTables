package hashCodeTesting.hashtable.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Element {
	
	private String data;
	private int key;
	private Element next = null;
	private Element last = null;
	public Element(String data, int key) {
		this.data = data;
		this.key = key;
	}
	public Element() {
		
	}
}
