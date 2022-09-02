package hashCodeTesting.hashtable.linkedListHashTable;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericListTest {
    private GenericList<String, String> list = new GenericList<>();

    @Test
    void name() {

        assertList(list, "");

        list.addOrReplace("a", "a");
        assertList(list, "(a)");

        list.addOrReplace("b", "b");
        assertList(list, "(a)---(b)");

        list.addOrReplace("c", "c");
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
    public void deleteShouldReturnNull_ifNotFound() {
        //when
        var res = list.delete("key");
        //then
        assertNull(res);
    }

    @Test
    public void deleteShouldReturnValue_ifFound() {
        //given
        list.addOrReplace("key", "value");

        //when
        var res = list.delete("key");
        //then
        assertEquals(res, "value");
        assertNull(list.get("key"));

    }

    @Test
    public void getShouldReturnNull_ifNotFound() {
        //when
        var res = list.get("value");
        //then
        assertNull(res);
    }

    @Test
    public void getShouldReturnValue_ifFound() {
        //given
        list.addOrReplace("key", "value");
        //when
        var res = list.get("key");
        //then
        assertEquals(res, "value");
    }

    @Test
    public void sizeShouldReturnZero_ifListIsEmpty(){
        //when
        var res = list.size;
        //then
        assertEquals(res,0);
    }
    @Test
    public void sizeShouldReturnNumOfElementsInList_ifListIsNotEmpty(){
        //given
        list.addOrReplace("key", "value");
        //when
        var res = list.size;
        //given
        assertEquals(res, 1);
    }

}