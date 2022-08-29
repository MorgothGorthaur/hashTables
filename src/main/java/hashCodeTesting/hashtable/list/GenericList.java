package hashCodeTesting.hashtable.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericList<K, V> {
	private GenericElement<K, V> head = new GenericElement<>();
	private GenericElement<K, V> tail = new GenericElement<>();

	public GenericList() {
		head.setNext(tail);
		tail.setLast(head);
	}

	public void add(K key, V value) {
		GenericElement<K, V> elem = new GenericElement<>(key, value);
		GenericElement<K, V> last = tail.getLast();
		last.setNext(elem);
		elem.setNext(tail);
		elem.setLast(last);
		tail.setLast(elem);
	}

	public boolean contains(K key) {
		GenericElement<K, V> nextElem = head;
		GenericElement<K, V> lastElem = tail;
		while (!nextElem.equals(lastElem) && !nextElem.getNext().equals(lastElem)) {

			nextElem = nextElem.getNext();
			lastElem = lastElem.getLast();
			if (nextElem.getKey().equals(key)) {
				return true;
			}
			if (lastElem.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	public void delete(K key) {
		GenericElement<K, V> nextElem = head;
		GenericElement<K, V> lastElem = tail;

		while (!nextElem.equals(lastElem) && nextElem.getNext() != null) {
			nextElem = nextElem.getNext();
			lastElem = lastElem.getLast();
			if (nextElem.getKey() == key) {

				nextElem.getLast().setNext(nextElem.getNext());
				nextElem.getNext().setLast(nextElem.getLast());
			}
			if (lastElem.getKey() == key) {
				lastElem.getLast().setNext(lastElem.getNext());
				lastElem.getNext().setLast(lastElem.getLast());
			}

		}
	}

	public void print() {
		GenericElement<K, V> nextElem = head;
		while (!nextElem.getNext().equals(tail)) {
			nextElem = nextElem.getNext();
			System.out.println(nextElem.getValue());
		}
	}

	public int size() {
		int size = 0;
		GenericElement<K, V> nextElem = head;
		while (!nextElem.getNext().equals(tail)) {
			nextElem = nextElem.getNext();
			size++;
		}
		return size; 
	}

	public String getFirst() {
		return head.getNext().getValue().toString();
	}
	public GenericElement <K,V> deleteFirst(){
		GenericElement <K, V> elem = head.getNext();
		
		head.setNext(elem.getNext());
		return elem;
		
	}

	public String get(K key) {
		String val = "";
		if (size() == 0) {
			val = "no found!";
		}
		if (size() == 1) {
			val = getFirst();

		}
		if (size() > 1) {
			System.out.println("collision! length = " + size());
			return getInternal(key);

		}
		return val;
	}

	public String getInternal(K key) {
		GenericElement<K, V> nextElem = head;
		GenericElement<K, V> lastElem = tail;
		while (!nextElem.equals(lastElem) && !nextElem.getNext().equals(lastElem)) {
			nextElem = nextElem.getNext();
			lastElem = lastElem.getLast();
			if (nextElem.getKey().equals(key)) {
				return nextElem.getValue().toString();
			}
			if (lastElem.getKey().equals(key)) {
				return lastElem.getValue().toString();
			}
		}

		return "not found!";
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
