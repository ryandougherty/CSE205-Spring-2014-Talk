import java.util.*;

public class Main {
	public static void main(String[] args) {
		int size = 448*304;
		MyObject m = new MyObject(size);
		long start = System.nanoTime();
		String s = m.toString();
		long end = System.nanoTime();
		long total = end-start;
		System.out.println("Took: " + total/1e9 + " sec");
	}
}
