package hashCodeTesting.hashtable.list;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericListTest {
    private  GenericList<String, String> list = new GenericList<>();
    @Test
    void name() {

        assertList(list, "");

        list.add("a", "a");
        assertList(list, "(a)");

        list.add("b", "b");
        assertList(list, "(a)---(b)");

        list.add("c", "c");
        assertList(list, "(a)---(b)---(c)");

        list.delete("a");
        assertList(list, "(b)---(c)");

        list.delete("b");
        assertList(list, "(c)");

        list.delete("c");
        assertList(list, "");

    }

    private void assertList(GenericList<String, String> list, String value) {
        assertEquals(value, list.getListAsString());
    }
    @Test
    public void deleteShouldReturnNull_ifNotFound(){
        //when
        String res = list.delete("key");
        //then
        assertNull(res);
    }
    @Test
    public void deleteShouldReturnValue_ifFound(){
        //given
        list.add("key", "value");
        //when
        String res = list.delete("key");
        //then
        assertEquals(res, "value");
        assertNull(list.get("key"));

    }
    @Test
    public void getShouldReturnNull_ifNotFound(){
        //when
        String res = list.get("value");
        //then
        assertNull(res);
    }
    @Test
    public void getShouldReturnValue_ifFound(){
        //given
        list.add("key", "value");
        //when
        String res = list.get("key");
        //then
        assertEquals(res, "value");
    }
}