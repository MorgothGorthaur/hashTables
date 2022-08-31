package hashCodeTesting.hashtable;

import hashCodeTesting.hashtable.linearZondingHashTable.CustomLinearZondingHashTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinearZondingHashTableTest {
    private CustomLinearZondingHashTable<String, String> table = new CustomLinearZondingHashTable<>();

    @Test
    public void shouldIncreaseSizeByOneAfterAdding() {
        //given
        table.add("key", "value");
        table.add("key_2", "value");
        table.add("key_3", "value");
        //when
        int res = table.getSize();
        //then
        assertEquals(res, 3);
    }

    @Test
    public void shouldDecreaseSizeByOneAfterRemoving() {
        //given
        table.add("key", "value");
        table.add("key_2", "value");
        table.delete("key_2");
        //when
        int res = table.getSize();
        //then
        assertEquals(res, 1);
    }

    @Test
    public void getShouldReturnValue_ifFound() {
        //given
        table.add("key", "value");
        //when
        String res = table.get("key");
        //then
        assertEquals(res, "value");
    }

    @Test
    public void getShouldReturnNull_ifNotFound() {
        //when
        String res = table.get("key");
        //then
        assertNull(res);
    }

    @Test
    public void deleteShouldReturnValue_ifFound() {
        //given
        table.add("key", "value");
        //when
        String res = table.delete("key");

        //then
        assertEquals(res, "value");
        assertNull(table.get("key"));
    }

    @Test
    public void deleteShouldReturnNull_ifNotFound() {
        //when
        String res = table.delete("key");
        //then
        assertNull(res);
    }

    @Test
    public void shouldReturnString() {
        //given
        table.add("key", "value");
        table.add("key_2", "value_2");
        //when
        String res = table.toString();
        assertEquals(res, "value value_2");
    }

    @Test
    public void shouldIncreaseLengthOfArrayIfNumOfElementsIsGreaterThenIt() {
        //given
        for (int i = 0; i < 10000000; i++) {
            table.add(String.valueOf(i), String.valueOf(i));
        }
        //when
        int res = table.getSize();
        //then
        assertEquals(res, 10000000);
    }

    @Test
    public void testGetAndDeleteForABigSizes() {
        //given
        for (int i = 0; i < 10000000; i++) {
            table.add(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(table.getSize(), 10000000);
        for (int i = 0; i < 10000000; i++) {
            assertEquals(table.get(String.valueOf(i)), String.valueOf(i));
            assertEquals(table.delete(String.valueOf(i)), String.valueOf(i));
        }
        assertEquals(table.getSize(), 0);
    }

}