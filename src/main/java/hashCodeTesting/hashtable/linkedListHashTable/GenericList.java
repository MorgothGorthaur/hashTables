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
        GenericElement<K, V> elem = getElementBeforeKeyOrLastElement(key);
        //if list is empty
        if(elem == null){
            head =  new GenericElement<>(key,value);
            size ++;
        }
        //if head contains key
        if(checkIfElementExistKey(key,elem)){
            elem.setValue(value);
        }
        //if one of the elements contains key
        if(elem != null && checkIfElementExistKey(key,elem.getNext())){
            GenericElement<K, V> next = elem.getNext();
            next.setValue(value);
        }
        //add element to end of the list
        if(elem != null && elem.getNext() == null){
            size ++;
            elem.setNext(new GenericElement<>(key,value));
        }

    }

    public V delete(K key) {
        GenericElement<K, V> elem = getElementBeforeKeyOrLastElement(key);
        if (checkIfElementExistKey(key, elem)) {
            size --;
            head = elem.getNext();
            return elem.getValue();
        }
        if (elem != null && checkIfElementExistKey(key, elem.getNext())) {
            size --;
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
        GenericElement<K, V> elem = getElementBeforeKeyOrLastElement(key);
        if (checkIfElementExistKey(key,elem)) {
            return elem.getValue();
        }
        if(elem != null && checkIfElementExistKey(key, elem.getNext())){
            return elem.getNext().getValue();
        }
        return null;
    }

    private GenericElement<K, V> getElementBeforeKeyOrLastElement(K key) {
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
            size --;
            GenericElement<K, V> elem = head;
            head = elem.getNext();
            return new KeyEndValue(elem);
        }
        return null;
    }

    public int size() {
        return size;
    }
}
