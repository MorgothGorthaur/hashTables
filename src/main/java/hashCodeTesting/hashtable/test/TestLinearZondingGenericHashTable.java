package hashCodeTesting.hashtable.test;


import hashCodeTesting.hashtable.CustomLinearZondingHashTable;
import hashCodeTesting.hashtable.LinearZondingGenericHashTable;
import hashCodeTesting.hashtable.LinkedListGenericHashTable;
import hashCodeTesting.hashtable.key.BadEquals;
import hashCodeTesting.hashtable.key.BadKey;
import hashCodeTesting.hashtable.key.GoodKey;
import hashCodeTesting.hashtable.key.SecondBadKey;

public class TestLinearZondingGenericHashTable {
    private void testWithIntegerKey() {
        CustomLinearZondingHashTable<Integer, String> table = new CustomLinearZondingHashTable<>();
        for (int i = 0; i < 4000; i++) {
            table.add(i, "" + i);
        }
        //table.getInfo();
        Long time = System.nanoTime();
        System.out.println("value = " + table.get(3933));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        table.delete(3933);
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);
        //table.print();
    }

    private void testWithStringKey() {
        CustomLinearZondingHashTable<String, String> table = new CustomLinearZondingHashTable<>();
        for (int i = 0; i < 10000; i++) {
            table.add("" + i, "" + i);
        }
        //table.getInfo();
        Long time = System.nanoTime();
        System.out.println("value = " + table.get("1234"));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        table.delete("54321");
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

    }

    private void testWithBadCustomKey() {
        CustomLinearZondingHashTable<BadKey, String> table = new CustomLinearZondingHashTable<>();
        for (int i = 0; i < 1; i++) {
            table.add(new BadKey("" + i, i), "" + i);
        }
        //table.getInfo();
        System.out.println(table.getLength());
        Long time = System.nanoTime();
        System.out.println("value = " + table.get(new BadKey("2", 2)));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

//
//		time = System.nanoTime();
//		System.out.println("value=" + table.get(new BadKey("123", 211)));
//		System.out.print("full time = ");
//		System.out.println(System.nanoTime() - time);

    }

    private void testWithSecondBadCustomKey() {
        CustomLinearZondingHashTable<SecondBadKey, String> table = new CustomLinearZondingHashTable<>();
        for (int i = 0; i < 10000; i++) {
            table.add(new SecondBadKey("" + i, i), "" + i);
        }
        //table.getInfo();
        Long time = System.nanoTime();


        time = System.nanoTime();
        System.out.println("value = " + table.get(new SecondBadKey("555", 555)));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        table.delete(new SecondBadKey("123", 321));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

    }

    private void testWithGoodCustomKey() {


        /*
         * 0, 0 -> 6775 mod 32 = 23
         * 1,1 -> x mod 32 = 23
         */
        CustomLinearZondingHashTable<GoodKey, String> table = new CustomLinearZondingHashTable<>();
        for (int i = 0; i < 10000; i++) {
            table.add(new GoodKey("" + i, i), "" + i);
        }
        //table.getInfo();
        Long time = System.nanoTime();
        System.out.println("value = " + table.get(new GoodKey("2", 2)));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);

        time = System.nanoTime();
        table.delete(new GoodKey("666", 555));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);


    }

    private void testWithBadEquals() {
        CustomLinearZondingHashTable<BadEquals, String> table = new CustomLinearZondingHashTable<>();
        for (int i = 0; i < 10000; i++) {
            table.add(new BadEquals("" + i, i), "" + i);
        }
        ;
        //table.getInfo();
        Long time = System.nanoTime();
        System.out.println("value = " + table.get(new BadEquals("2", 2)));
        System.out.print("full time = ");
        System.out.println(System.nanoTime() - time);
    }

    public void test() {
        System.out.println("\nInteger");
        testWithIntegerKey();
        System.out.println("\nString");
        testWithStringKey();
        System.out.println("\nBadKey");
        testWithBadCustomKey();
        System.out.println("\nSecondBadKey");
        testWithSecondBadCustomKey();
        System.out.println("\nGoodKey");
        testWithGoodCustomKey();
        System.out.println("\nBadEquals");
        testWithBadEquals();
    }
}
