package hashCodeTesting.hashtable.linkedListHashTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class KeyEndValue <K,V> {
    private K key;
    private V value;
    public KeyEndValue (GenericElement <K,V> elem){
        this.key = elem.getKey();
        this.value = elem.getValue();
    }
}
