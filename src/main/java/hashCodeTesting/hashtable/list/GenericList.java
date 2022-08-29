package hashCodeTesting.hashtable.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericList<K, V> {
	private GenericElement<K, V> head;
	private GenericElement<K, V> tail;
	private int size;

	public String getListAsString() {
		var result = new StringBuilder();
		var p = head;
		var first = true;
		while (p != null) {
			if (!first) {
				result.append("---");
			}
			result.append("(").append(p.value).append(")");
			p = p.getNext();
			first = false;
		}
		return result.toString().trim();
	}

	public void add(K key, V value) {
		GenericElement<K, V> elem = new GenericElement<>(key, value);
		if (tail == null) {
			head = tail = elem;
		} else {
			tail.setNext(elem);
			tail = elem;
		}
		size++;
	}

	public boolean contains(K key) {
		for (GenericElement<K, V> p = head; p != null; p = p.getNext()) {
			if (p.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	public void delete(K key) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return size;
	}

	public V getFirst() {
		return head.getValue();
	}

	public GenericElement <K,V> deleteFirst(){
		GenericElement <K, V> elem = head.getNext();
		
		head.setNext(elem.getNext());
		return elem;
		
	}

	public V get(K key) {
		if (size() == 1) {
			return getFirst();
		}
		if (size() > 1) {
			System.out.println("collision! length = " + size());
			return getInternal(key);
		}
		throw new IllegalStateException();
	}

	public V getInternal(K key) {
		for (GenericElement<K, V> p = head; p != null; p = p.getNext()) {
			if (p.getKey().equals(key)) {
				return p.getValue();
			}
		}
		return null;
	}

	public void addAll(GenericList <K,V> lst) {
		GenericElement<K, V> last = tail.getLast();
		GenericElement<K, V> first = lst.getHead().getNext();
		last.setNext(first);
		first.setLast(last);
		tail = lst.getTail();
	}

	public boolean deleteFromList(K key) {
		if (size() == 0) {
			System.out.println("no found!");
			return false;
		}
		if (size() == 1) {
			deleteFirst();
		}
		if (size() > 1) {
			System.out.println("collision! length = " + size());
			delete(key);

		}
		return true;
	}

}
