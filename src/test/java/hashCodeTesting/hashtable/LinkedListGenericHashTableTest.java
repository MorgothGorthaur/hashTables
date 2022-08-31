package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.list.GenericList;
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
    void deleteShouldReturnNull_ifNotFound() {
        //when
        Integer res = table.delete("key");
        //given
        assertNull(res);
    }

    @Test
    void deleteShouldReturnValue_ifFound() {
        //given
        table.add("key", 44);
        //when
        Integer res = table.delete("key");
        //given
        assertEquals(res, 44);
        assertNull(table.get("key"));
    }

    @Test
    public void testGetAndDeleteForABigSizes() {
        //given
        for (int i = 0; i < 10000000; i++) {
            table.add(String.valueOf(i), i);
        }
        for (int i = 0; i < 10000000; i++) {
            assertEquals(table.get(String.valueOf(i)), i);
            assertEquals(table.delete(String.valueOf(i)), i);
        }
    }


}