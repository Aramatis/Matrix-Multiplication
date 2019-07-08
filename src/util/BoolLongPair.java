package util;

public class BoolLongPair {

	private final boolean b;
	private final long l;

	/**
	 * A simple pair containing a boolean and a long.
	 *
	 * @param b The boolean in the pair.
	 * @param l The long in the pair.
	 */
	public BoolLongPair(boolean b, long l) {
		this.b = b;
		this.l = l;
	}

	/**
	 * Returns the boolean in the pair.
	 *
	 * @return The boolean in the pair.
	 */
	public boolean getBool() {
		return this.b;
	}

	/**
	 * Returns the long in the pair.
	 *
	 * @return The long in the pair.
	 */
	public long getLong() {
		return this.l;
	}

}
