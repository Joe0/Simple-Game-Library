package info.javacoding.sgl.input;

public class MouseEvent {
	public static final int BUTTON1 = java.awt.event.MouseEvent.BUTTON1;
	public static final int BUTTON2 = java.awt.event.MouseEvent.BUTTON2;
	public static final int BUTTON3 = java.awt.event.MouseEvent.BUTTON3;
	public static final int NO_BUTTON = java.awt.event.MouseEvent.NOBUTTON;
	public static final int MOUSE_CLICKED = java.awt.event.MouseEvent.MOUSE_CLICKED;
	public static final int MOUSE_MOVED = java.awt.event.MouseEvent.MOUSE_MOVED;
	public static final int MOUSE_WHEEL = java.awt.event.MouseEvent.MOUSE_WHEEL;

	private final int x, y, button;
	private final boolean buttonState;

	/**
	 * Creates a MouseEvent with the given parameters.
	 * @param x
	 * @param y
	 * @param button
	 */
	public MouseEvent(final int x, final int y, final int button, final boolean buttonState) {
		this.x = x;
		this.y = y;
		this.button = button;
		this.buttonState = buttonState;
	}
	
	/**
	 * @return The absolute x position.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return The absolute y position.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return The event button.  -1 if no button changed state.
	 */
	public int getButton() {
		return button;
	}
	
	/**
	 * @return The current buttons state.
	 */
	public boolean getButtonState() {
		return buttonState;
	}
}
