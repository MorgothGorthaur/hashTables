package hashCodeTesting.hashtable.linkedListHashTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class GenericElement<K, V> {

    K key;
    V value;
    GenericElement<K, V> next = null;

    public GenericElement(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
