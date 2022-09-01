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
           GenericElement [] elem = getElementByKeyOrLastElem(key);
           GenericElement <K,V> last = elem[0];
           GenericElement <K,V> next = elem[1];
           if (checkIfElementExistKey(key, next)) {
               next.setValue(value);
           } else if(last != null) {
               size ++;
               last.setNext(new GenericElement<>(key, value));
           } else {
               size ++;
               head = new GenericElement<>(key,value);
           }

    }

    public V delete(K key) {
        GenericElement [] elem = getElementByKeyOrLastElem(key);
        GenericElement <K,V> last = elem[0];
        GenericElement <K,V> next = elem[1];
        if(checkIfElementExistKey(key,next)){
            size --;
            if(next == head) {
                head = next.getNext();
            } else {
                last.setNext(next.getNext());
            }
            return next.getValue();
        }
        return null;
    }

    private boolean checkIfElementExistKey(K key, GenericElement<K, V> elem) {
        return elem != null && elem.getKey().equals(key);
    }


    public V get(K key) {
        GenericElement [] elem = getElementByKeyOrLastElem(key);
        GenericElement <K,V> next = elem[1];
        if(checkIfElementExistKey(key,next)){
            return next.getValue();
        }
        return null;
    }

    private GenericElement[] getElementByKeyOrLastElem(K key) {
        GenericElement<K, V> last = null;
        GenericElement<K, V> next = head;
        while (next != null && !next.getKey().equals(key)) {
            last = next;
            next = next.getNext();
        }
        GenericElement [] elems = new GenericElement[2];
        elems[0] = last;
        elems[1] = next;
        return elems;
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
