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

//		while (!nextElem.getNext().equals(lastElem.getLast()) && nextElem.getNext() != null && lastElem.getLast() != null) {
//			nextElem = nextElem.getNext();
//			lastElem = lastElem.getLast();
//			if (nextElem.getKey().equals(key)) {
//				return true;
//			}
//			if (lastElem.getKey().equals(key)) {
//				return true;
//			}
//		}
		while (!nextElem.equals(lastElem) && !nextElem.getNext().equals(lastElem)) {

			nextElem = nextElem.getNext();
			lastElem = lastElem.getLast();
			if (nextElem.getKey().equals(key)) {
				return true;
			}
			if (lastElem.getKey().equals(key)) {
				return true;
			}
			// System.out.println(nextElem.getValue().toString() + " "+
			// lastElem.getValue().toString());

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
}
