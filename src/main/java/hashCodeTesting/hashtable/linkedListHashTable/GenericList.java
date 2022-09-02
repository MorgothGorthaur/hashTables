package hashCodeTesting.hashtable.linkedListHashTable;

class GenericList<K, V> {
    private GenericElement<K, V> head;
    private int size = 0;

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

    public void addOrReplace(K key, V value) {
        GenericElement[] elems = findElementByKeyAndElementBeforeItOrReturnLastElement(key);
        GenericElement<K, V> last = elems[0];
        GenericElement<K, V> next = elems[1];
        if (next != null && next.containsKey(key)) {
            next.setValue(value);
        } else if (last != null) {
            size++;
            last.setNext(new GenericElement<>(key, value));
        } else {
            size++;
            head = new GenericElement<>(key, value);
        }
    }

    public V delete(K key) {
        GenericElement[] elems = findElementByKeyAndElementBeforeItOrReturnLastElement(key);
        GenericElement<K, V> last = elems[0];
        GenericElement<K, V> next = elems[1];
        if (next != null && next.containsKey(key)) {
            size--;
            if (next == head) {
                head = next.getNext();
            } else {
                last.setNext(next.getNext());
            }
            return next.getValue();
        }
        return null;
    }

    public V get(K key) {
        GenericElement[] elems = findElementByKeyAndElementBeforeItOrReturnLastElement(key);
        GenericElement<K, V> next = elems[1];
        if (next != null && next.containsKey(key)) {
            return next.getValue();
        }
        return null;
    }

    private GenericElement[] findElementByKeyAndElementBeforeItOrReturnLastElement(K key) {
        GenericElement<K, V> last = null;
        GenericElement<K, V> next = head;
        while (next != null && !next.containsKey(key)) {
            last = next;
            next = next.getNext();
        }
        GenericElement[] elems = new GenericElement[2];
        elems[0] = last;
        elems[1] = next;
        return elems;
    }

    public KeyEndValue deleteFirst() {
        if (head != null) {
            size--;
            GenericElement<K, V> elem = head;
            head = elem.getNext();
            return new KeyEndValue(elem);
        }
        return null;
    }

    public void addAll(GenericList<K, V> lst) {
        if (lst != null) {
            GenericElement[] elems = lst.findElementByKeyAndElementBeforeItOrReturnLastElement(null);
            GenericElement<K, V> last = elems[0];
            last.setNext(head);
            head = lst.head;
            size += lst.size();
        }
    }

    public int size() {
        return size;
    }
}
