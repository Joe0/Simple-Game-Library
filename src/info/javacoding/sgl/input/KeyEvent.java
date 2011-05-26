package info.javacoding.sgl.input;

public class KeyEvent {
	private final int eventKey;
	private final char eventChar;
	private final boolean state;

	/**
	 * Creates a KeyEvent.
	 * 
	 * @param eventKey
	 * @param eventChar
	 * @param state
	 */
	public KeyEvent(final int eventKey, final char eventChar,
			final boolean state) {
		this.eventKey = eventKey;
		this.eventChar = eventChar;
		this.state = state;
	}

	/**
	 * @return The key that was changed.
	 */
	public int getKey() {
		return eventKey;
	}

	/**
	 * @return The character based on the key that was changed.
	 */
	public char getChar() {
		return eventChar;
	}

	/**
	 * @return The state of the key.<br>
	 *         True if down.
	 */
	public boolean getState() {
		return state;
	}
}
