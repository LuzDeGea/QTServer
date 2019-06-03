package database;

import java.util.ArrayList;
import java.util.List;

public class Example implements Comparable<Example> {
	private List<Object> example = new ArrayList<Object>();

	public void add(final Object o) {
		example.add(o);
	}

	public Object get(final int i) {
		return example.get(i);
	}

	@Override
	public int compareTo(final Example ex) {

		int i = 0;
		for (Object o : ex.example) {
			if (!o.equals(this.example.get(i))) {
				return ((Comparable) o).compareTo(example.get(i));
			}
			i++;
		}
		return 0;
	}

	@Override
	public String toString() {
		String str = "";
		for (Object o : example) {
			str += o.toString() + " ";
		}
		return str;
	}

}