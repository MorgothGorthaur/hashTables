package hashCodeTesting.hashtable.linkedListHashTable;

import lombok.Getter;

class GenericList<K, V> {
    GenericElement<K, V> head;
    int size = 0;

    String getListAsString() {
        var result = new StringBuilder();
        var p = head;
        var first = true;
        while (p != null) {
            if (!first) {
                result.append("---");
            }
            result.append("(").append(p.value).append(")");
            p = p.next;
            first = false;
        }
        return result.toString().trim();
    }

    void addOrReplace(K key, V value) {
        GenericElement <K,V> last = null;
        GenericElement <K,V> curr = head;
        while (curr != null){
            if(curr.containsKey(key)){
                curr.value = value;
            }
            last = curr;
            curr = curr.next;
        }
        if(head == null){
            head = new GenericElement<>(key,value);
            size ++;
        }else if(curr == null) {
            last.next = new GenericElement<>(key,value);
            size ++;
        }
    }

    V delete(K key) {
       GenericElement <K,V> last = null;
       GenericElement <K,V> curr = head;
       while (curr != null){
           if(curr.containsKey(key)){
               if(last != null){
                   last.next = curr.next;
               }else {
                   head = curr.next;
               }
               size --;
               return curr.value;
           }
           last = curr;
           curr = curr.next;
       }
       return null;
    }

    V get(K key) {
        GenericElement <K,V> curr = head;
        while (curr != null){
            if(curr.containsKey(key)){
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }
}
