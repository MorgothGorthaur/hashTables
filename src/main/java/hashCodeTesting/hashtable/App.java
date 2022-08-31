package hashCodeTesting.hashtable;



import hashCodeTesting.hashtable.key.GoodKey;

import java.util.HashMap;

/**
 * https://neerc.ifmo.ru/wiki/index.php?title=%D0%A5%D0%B5%D1%88-%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86%D0%B0
 *
 */ 
public class App {

    public static void main( String[] args ){
        CustomLinearZondingHashTable<String, String> table = new CustomLinearZondingHashTable<>();
        for(int i = 0; i < 10000000; i ++){
            table.add(String.valueOf(i), String.valueOf(i));
        }
        for(int i = 0; i < 10000000; i++){
            table.delete(String.valueOf(i));
        }
        System.out.println(table.getSize());
    } 
}
