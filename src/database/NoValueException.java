package database;

public class NoValueException extends Exception {
	private String m;

	public NoValueException(final String s) {
		m = s;
		getMessage(m);
	}

	public String getMessage(final String m2) {
		return m;
	}
}
