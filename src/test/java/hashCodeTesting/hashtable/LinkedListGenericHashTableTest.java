package hashCodeTesting.hashtable;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListGenericHashTableTest {


    private LinkedListGenericHashTable<String, Integer> table = new LinkedListGenericHashTable<>();

    @Test
    void shouldReturnNull_ifNotFound() {
        assertNull(table.get("vitya"));
    }

    @Test
    void shouldAddAndRetrieve() {
        table.add("vitya", 21);

        assertEquals(21, table.get("vitya"));
    }

    @Test
    @Disabled
    void shouldSetSizeToZeroInitially() {
        assertEquals(0, table.size());
    }

    @Test
    @Disabled
    void shouldIncrementSize_whenElementIsAdded() {
        table.add("vitya", 21);

        assertEquals(1, table.size());
    }

    @Test
    @Disabled
    void shouldIncrementSize_whenMultipleElementIsAdded() {
        table.add("vitya", 21);

        assertEquals(1, table.size());
    }

    @Test
    @Disabled
    void shouldNotIncrementSize_whenExistingElementIsAdded() {
        table.add("vitya", 21);
        table.add("vitya", 21);

        assertEquals(1, table.size());
    }

    @Test
    void shouldAddMultipleAndRetrieve() {
        table.add("vitya", 21);
        table.add("andrey", 38);

        assertEquals(38, table.get("andrey"));
    }

    @Test
    void shouldOverride_whenAdding() {
        table.add("vitya", 38);
        table.add("vitya", 21);

        assertEquals(21, table.get("vitya"));
    }

    @Test
    void deleteShouldReturnNull_ifNotFound(){
        //when
        Integer res = table.delete("key");
        //given
        assertNull(res);
    }
    @Test
    void deleteShouldReturnValue_ifFound(){
        //given
        table.add("key",44);
        //when
        Integer res = table.delete("key");
        //given
        assertEquals(res, 44);
        assertNull(table.get("key"));
    }
}