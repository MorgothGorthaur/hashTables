package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.linkedListHashTable.LinkedListGenericHashTable;
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
        //when
        table.add("vitya", 21);
        //then
        assertEquals(21, table.get("vitya"));
    }

    @Test
    void shouldAddMultipleAndRetrieve() {
        //when
        table.add("vitya", 21);
        table.add("andrey", 38);
        //then
        assertEquals(38, table.get("andrey"));
    }

    @Test
    void shouldOverride_whenAdding() {
        //when
        table.add("vitya", 38);
        table.add("vitya", 21);
        //then
        assertEquals(21, table.get("vitya"));
    }

    @Test
    void deleteShouldReturnNull_ifNotFound() {
        //when
        var res = table.delete("key");
        //given
        assertNull(res);
    }

    @Test
    void deleteShouldReturnValue_ifFound() {
        //given
        table.add("key", 44);
        //when
        var res = table.delete("key");
        //given
        assertEquals(res, 44);
        assertNull(table.get("key"));
    }

    @Test
    void testGetAndDeleteForABigSizes() {
        //given
        for (int i = 0; i < 10000000; i++) {
            table.add(String.valueOf(i), i);
        }
        for (int i = 0; i < 10000000; i++) {
            assertEquals(table.get(String.valueOf(i)), i);
            assertEquals(table.delete(String.valueOf(i)), i);
        }
    }

    @Test
    void sizeShouldReturnZero_ifTableIsEmpty() {
        //when
        var res = table.size();
        //then
        assertEquals(res, 0);
    }

    @Test
    void sizeShouldReturnValue_ifTableIsNotEmpty() {
        //given
        table.add("key", 1);
        //when
        var res = table.size();
        //then
        assertEquals(res, 1);
    }


}