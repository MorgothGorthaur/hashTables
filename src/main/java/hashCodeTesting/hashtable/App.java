package hashCodeTesting.hashtable;


import hashCodeTesting.hashtable.test.TestLInkedListHashTable;
import hashCodeTesting.hashtable.test.TestLinearZondingGenericHashTable;

import java.util.HashMap;

/**
 * https://neerc.ifmo.ru/wiki/index.php?title=%D0%A5%D0%B5%D1%88-%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86%D0%B0
 *
 */ 
public class App {

    public static void main( String[] args ){
        HashMap <String, String> map = new HashMap<>();
        map.put("d", "d");
        System.out.println(map.get("d"));
        System.out.println(map.remove("s"));
    } 
}
