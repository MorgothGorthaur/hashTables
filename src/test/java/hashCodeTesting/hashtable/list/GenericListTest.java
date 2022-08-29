package hashCodeTesting.hashtable.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericListTest {

    @Test
    void name() {
        GenericList<String, String> list = new GenericList<>();
        assertList(list, "(null)---(null)");

        list.add("a", "a");
        assertList(list, "(null)---(a)---(null)");

        list.add("b", "b");
        assertList(list, "(null)---(a)---(b)---(null)");

        list.add("c", "c");
        assertList(list, "(null)---(a)---(b)---(c)---(null)");

        assertEquals("c", list.get("c"));
    }

    private void assertList(GenericList<String, String> list, String value) {
        assertEquals(value, list.getListAsString());
    }
}