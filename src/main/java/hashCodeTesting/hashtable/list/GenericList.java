package hashCodeTesting.hashtable.list;

import lombok.Getter;
import lombok.Setter;


public class GenericList<K, V> {
    private GenericElement<K, V> head;

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
        GenericElement<K, V> elem = getElementBeforeKey(key);
        if (elem == null) {
            head = new GenericElement<>(key, value);
        } else if (elem.getNext() == null && !elem.getKey().equals(key)) {
            elem.setNext(new GenericElement<>(key, value));
        } else {
            elem.setValue(value);
        }


    }

    public V delete(K key) {
        GenericElement<K, V> elem = getElementBeforeKey(key);
        if (elem == null) return null;
        if (elem.getKey().equals(key)) {
            head = elem.getNext();
            return elem.getValue();
        }
        GenericElement<K, V> next = elem.getNext();
        elem.setNext(next.getNext());
        return next.getValue();
    }

    public V get(K key) {
        GenericElement<K, V> elem = getElementBeforeKey(key);
        if (elem == null) return null;
        if (elem.getKey().equals(key)) {
            return elem.getValue();
        } else {
            return elem.getNext().getValue();
        }
    }

    private GenericElement<K, V> getElementBeforeKey(K key) {
        GenericElement<K, V> last = head;
        GenericElement<K, V> next = head;
        while (next != null && !next.getKey().equals(key)) {
            last = next;
            next = next.getNext();
        }
        return last;
    }

    public GenericElement<K, V> deleteFirst() {
        if (head != null) {
            GenericElement<K, V> elem = head;
            head = elem.getNext();
            return elem;
        }
        return null;
    }

    public int size() {
        int size = 0;
        GenericElement<K, V> elem = head;
        while (elem != null) {
            elem = elem.getNext();
            size++;
        }
        return size;
    }
}
