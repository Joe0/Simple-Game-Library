package info.javacoding.sgl.input;

public class MouseEvent {
	public static final int BUTTON1 = java.awt.event.MouseEvent.BUTTON1;
	public static final int BUTTON2 = java.awt.event.MouseEvent.BUTTON2;
	public static final int BUTTON3 = java.awt.event.MouseEvent.BUTTON3;
	public static final int NO_BUTTON = java.awt.event.MouseEvent.NOBUTTON;
	public static final int MOUSE_CLICKED = java.awt.event.MouseEvent.MOUSE_CLICKED;
	public static final int MOUSE_MOVED = java.awt.event.MouseEvent.MOUSE_MOVED;
	public static final int MOUSE_WHEEL = java.awt.event.MouseEvent.MOUSE_WHEEL;

	private int x, y, button;

	public MouseEvent(final int x, final int y, final int button) {
		this.x = x;
		this.y = y;
		this.button = button;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getButton() {
		return button;
	}
}
