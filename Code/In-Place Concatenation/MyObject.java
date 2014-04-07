import java.util.*;

public class MyObject {
	private ArrayList<Float> array;
	private Random random;

	public MyObject(int size) {
		array = new ArrayList<Float>(size);
		random = new Random();
		for (int i = 0; i<array.size(); i++) {
			array.set(i, random.nextFloat());
		}
	}

	@Override
	public String toString() {
		String toReturn = "{";
		for (int i = 0; i<array.size()-1; i++) {
			toReturn += array.get(i) + ", ";
		}
		//toReturn += array.get(array.size()-1);
		toReturn += "}";
		return toReturn;
	}
}