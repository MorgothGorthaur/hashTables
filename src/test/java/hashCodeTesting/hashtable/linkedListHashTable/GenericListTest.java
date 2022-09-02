package hashCodeTesting.hashtable.linkedListHashTable;


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
        String res = list.delete("key");
        //then
        assertNull(res);
    }

    @Test
    public void deleteShouldReturnValue_ifFound() {
        //given
        list.addOrReplace("key", "value");

        //when
        String res = list.delete("key");
        //then
        assertEquals(res, "value");
        assertNull(list.get("key"));

    }

    @Test
    public void getShouldReturnNull_ifNotFound() {
        //when
        String res = list.get("value");
        //then
        assertNull(res);
    }

    @Test
    public void getShouldReturnValue_ifFound() {
        //given
        list.addOrReplace("key", "value");
        //when
        String res = list.get("key");
        //then
        assertEquals(res, "value");
    }

    @Test
    public void deleteFirstShouldReturnNull_ifListIsNull() {
        //when
        KeyEndValue<String, String> res = list.deleteFirst();
        //given
        assertNull(res);
    }

    @Test
    public void deleteFirstShouldReturnValue_ifListIsNotEmpty() {
        //given
        list.addOrReplace("key", "value");
        //when
       KeyEndValue<String, String> res = list.deleteFirst();
        //given
        assertEquals(res.getKey(), "key");
    }

    @Test
    public void sizeShouldReturnZero_ifListIsEmpty(){
        //when
        int res = list.size();
        //then
        assertEquals(res,0);
    }
    @Test
    public void sizeShouldReturnNumOfElementsInList_ifListIsNotEmpty(){
        //given
        list.addOrReplace("key", "value");
        //when
        int res = list.size();
        //given
        assertEquals(res, 1);
    }

    @Test
    public void addAllShouldConcatLists(){
        //given
        list.addOrReplace("key", "value");
        list.addOrReplace("key2", "value2");
        GenericList <String, String> lst = new GenericList<>();
        lst.addOrReplace("key3", "value3");
        lst.addOrReplace("key4", "value4");
        //when
        list.addAll(lst);
        //then
        assertEquals(list.size(),4);
        assertEquals(list.get("key4"), "value4");
    }


}