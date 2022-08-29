package hashCodeTesting.hashtable;


import hashCodeTesting.hashtable.test.TestLInkedListHashTable;
import hashCodeTesting.hashtable.test.TestLinearZondingGenericHashTable;

/**
 * https://neerc.ifmo.ru/wiki/index.php?title=%D0%A5%D0%B5%D1%88-%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86%D0%B0
 *
 */ 
public class App {

    public static void main( String[] args ){
//    	TestLInkedListHashTable test = new TestLInkedListHashTable();
//    	test.test();
    	TestLinearZondingGenericHashTable secondTest =  new TestLinearZondingGenericHashTable ();
     	secondTest.test();
//    	CustomLinearZondingHashTable <Integer, String> t = new CustomLinearZondingHashTable <>();
//    	for (int i = 0; i < 10000; i++) {
//    		t.add(i, ""+i);
//    	}
//    	Long time = System.nanoTime();
//    	System.out.println(t.get(123));
//    	System.out.println(System.nanoTime() - time);
//    	System.out.println(t.getClustersSize());
//    	System.out.println(t.getSize());
    } 
}
