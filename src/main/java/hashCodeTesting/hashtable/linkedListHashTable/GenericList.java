package hashCodeTesting.hashtable.linkedListHashTable;


class GenericList<K, V> {
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
        if (head == null) {
            head = new GenericElement<>(key, value);
        } else {
            GenericElement<K, V> elem = getElementBeforeKey(key);
            if (checkIfElementExistKey(key,elem)) {
                elem.setValue(value);
            } else if (elem.getNext() == null) {
                elem.setNext(new GenericElement<>(key, value));
            } else {
                GenericElement<K, V> next = new GenericElement<>(key, value);
                next.setValue(value);
            }
        }

    }

    public V delete(K key) {
        GenericElement<K, V> elem = getElementBeforeKey(key);
        if (checkIfElementExistKey(key, elem)) {
            head = elem.getNext();
            return elem.getValue();
        }
        if (elem != null && checkIfElementExistKey(key, elem.getNext())) {
            GenericElement<K, V> next = elem.getNext();
            elem.setNext(next.getNext());
            return next.getValue();
        }
        return null;
    }

    private boolean checkIfElementExistKey(K key, GenericElement<K, V> elem) {
        return elem != null && elem.getKey().equals(key);
    }


    public V get(K key) {
        GenericElement<K, V> elem = getElementBeforeKey(key);
        if (checkIfElementExistKey(key,elem)) {
            return elem.getValue();
        }
        if(elem != null && checkIfElementExistKey(key, elem.getNext())){
            return elem.getNext().getValue();
        }
        return null;
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

    public KeyEndValue deleteFirst() {
        if (head != null) {
            GenericElement<K, V> elem = head;
            head = elem.getNext();
            return new KeyEndValue(elem);
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
