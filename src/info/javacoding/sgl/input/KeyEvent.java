package info.javacoding.sgl.input;

public class KeyEvent {
	private final int eventKey;
	private final char eventChar;
	private final boolean state;
	
	public KeyEvent(final int eventKey, final char eventChar, final boolean state) {
		this.eventKey = eventKey;
		this.eventChar = eventChar;
		this.state = state;
	}

	public int getKey() {
		return eventKey;
	}
	
	public char getChar() {
		return eventChar;
	}
	
	public boolean getState() {
		return state;
	}
}
