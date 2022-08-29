package hashCodeTesting.hashtable.test;

import hashCodeTesting.hashtable.LinkedListGenericHashTable;
import hashCodeTesting.hashtable.key.BadEquals;
import hashCodeTesting.hashtable.key.BadKey;
import hashCodeTesting.hashtable.key.GoodKey;
import hashCodeTesting.hashtable.key.SecondBadKey;

public class TestLInkedListHashTable {
	private void testWithIntegerKey() {
		LinkedListGenericHashTable<Integer, String> table = new LinkedListGenericHashTable<>();
		for (int i = 0; i < 4000000; i++) {
			table.add(-i, "" + i);
		}
		table.getInfo();
		Long time = System.nanoTime();
		System.out.println("get value = " + table.get(-123456));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

		time = System.nanoTime();
		table.delete(-4933);
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

	}

	private void testWithStringKey() {
		LinkedListGenericHashTable<String, String> table = new LinkedListGenericHashTable<>();
		for (int i = 0; i < 100000; i++) {
			table.add("" + i, "" + i);
		}
		table.getInfo();

		Long time = System.nanoTime();
		System.out.println("value = " + table.get("54321"));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

		time = System.nanoTime();
		table.delete("33333");
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

	}

	private void testWithBadCustomKey() {
		LinkedListGenericHashTable<BadKey, String> table = new LinkedListGenericHashTable<>();
		for (int i = 0; i < 123456; i++) {
			table.add(new BadKey("" + i, i), "" + i);
		}
		table.getInfo();

		Long time = System.nanoTime();
		System.out.println("value = " + table.get(new BadKey("12345", 12345)));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

		time = System.nanoTime();
		table.delete(new BadKey("12345", 12345));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

	}

	private void testWithSecondBadCustomKey() {
		LinkedListGenericHashTable<SecondBadKey, String> table = new LinkedListGenericHashTable<>();
		for (int i = 0; i < 1000; i++) {
			table.add(new SecondBadKey("" + i, i), "" + i);
		}
		table.getInfo();
		Long time = System.nanoTime();
		System.out.println("value = " + table.get(new SecondBadKey("2", 2)));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);
		time = System.nanoTime();
		table.delete(new SecondBadKey("12345", 12345));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

	}

	private void testWithGoodCustomKey() {
		LinkedListGenericHashTable<GoodKey, String> table = new LinkedListGenericHashTable<>();
		for (int i = 0; i < 1000; i++) {
			table.add(new GoodKey("" + i, i), "" + i);
		}
		table.getInfo();
		Long time = System.nanoTime();
		System.out.println("value = " + table.get(new GoodKey("2", 2)));
		System.out.print("full time = ");
		System.out.println(System.nanoTime() - time);

	}

	private void testWithBadEquals() {
		LinkedListGenericHashTable<BadEquals, String> table = new LinkedListGenericHashTable<>();
		for (int i = 0; i < 1000; i++) {
			table.add(new BadEquals("" + i, i), "" + i);
		}
		table.getInfo();
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
