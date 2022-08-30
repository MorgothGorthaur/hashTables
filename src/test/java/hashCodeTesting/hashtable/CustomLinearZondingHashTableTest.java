package hashCodeTesting.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinearZondingHashTableTest {
    private CustomLinearZondingHashTable<String, String> table = new CustomLinearZondingHashTable<>();

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
        assertEquals(res, null);
    }

    @Test
    public void deleteShouldReturnValue_ifFound() {
        //given
        table.add("key", "value");
        //when
        String res = table.delete("key");

        //then
        assertEquals(res, "value");
        assertEquals(table.get("key"), null);
    }
    @Test
    public void deleteShouldReturnNull_ifNotFound(){
        //when
        String res = table.delete("key");
        //then
        assertEquals(res, null);
    }
}