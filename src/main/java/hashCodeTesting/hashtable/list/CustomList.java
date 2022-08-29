package hashCodeTesting.hashtable.list;

import javax.swing.plaf.basic.BasicTableHeaderUI;

import lombok.NoArgsConstructor;

public class CustomList {
	Element head = new Element();
	Element tail = new Element();

	public CustomList() {
		head.setNext(tail);
		tail.setLast(head);
	}

	public void add(int key, String data) {
//		Element elem = new Element(data, key);
//		elem.setNext(head.getNext());
//		head.setNext(elem);
		Element elem = new Element(data, key);
		Element last = tail.getLast();
		last.setNext(elem);
		elem.setNext(tail);
		elem.setLast(last);
		tail.setLast(elem);
	}

	public boolean contains(int key) {
		Element nextElem = head;
		Element lastElem = tail;

		//if (!nextElem.getNext().equals(lastElem)) {
			while (!nextElem.equals(lastElem) && nextElem.getNext() != null) {
				nextElem = nextElem.getNext();
				lastElem = lastElem.getLast();
				if (nextElem.getKey() == key) {
					return true;
				}
				if (lastElem.getKey() == key) {
					return true;
				}
			}
	//	}

		return false;
	}

	public void delete(int key) {
		Element nextElem = head;
		Element lastElem = tail;
		if (!nextElem.getNext().equals(lastElem)) {
			while (!nextElem.equals(lastElem)) {
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
	}

	public void print() {
		Element nextElem = head;
		while (!nextElem.getNext().equals(tail)) {
			nextElem = nextElem.getNext();
			System.out.println(nextElem.getData());
		}
	}

	public int size() {
		int size = 0;
		Element nextElem = head;
		while (!nextElem.getNext().equals(tail)) {
			nextElem = nextElem.getNext();
			size++;
		}
		return size;
	}

	public String getFirst() {
		return head.getNext().getData();
	}

	public String get(int key) {
		Element nextElem = head;
		Element lastElem = tail;
		if (!nextElem.getNext().equals(lastElem)) {
			while (!nextElem.equals(lastElem)) {
				nextElem = nextElem.getNext();
				lastElem = lastElem.getLast();
				if (nextElem.getKey() == key) {
					return nextElem.getData();
				}
				if (lastElem.getKey() == key) {
					return lastElem.getData();
				}
			}
		}
		return null;
	}
}
