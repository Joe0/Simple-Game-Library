package info.javacoding.sgl.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows users to manage mouse listeners, and fires events when told to by the
 * GameEngine.
 * 
 * @author Joe Pritzel
 * 
 */
public class Mouse {

	private static List<MouseListener> listeners = new ArrayList<MouseListener>();

	/**
	 * Registers a MouseListener.
	 * 
	 * @param m
	 */
	public static void registerListener(final MouseListener m) {
		listeners.add(m);
	}

	/**
	 * Unregisters a MouseListener.
	 * 
	 * @param m
	 */
	public static void unregisterListener(final MouseListener m) {
		listeners.remove(m);
	}

	/**
	 * The last x and y position.<br>
	 * Used to determine if the mouse moved.
	 */
	private static int lastX = -1, lastY = -1;

	/**
	 * Fires events to all the listeners.
	 */
	public static void update() {
		final int x = org.lwjgl.input.Mouse.getEventX(), y = org.lwjgl.input.Mouse
				.getEventY(), button = org.lwjgl.input.Mouse.getEventButton();
		final boolean state = org.lwjgl.input.Mouse.getEventButtonState();
		int type = MouseEvent.MOUSE_WHEEL;
		if (lastX != x || lastY != y) {
			type = MouseEvent.MOUSE_MOVED;
		}
		if (button != -1) {
			type = MouseEvent.MOUSE_CLICKED;
		}
		final MouseEvent e = new MouseEvent(x, y, button, state);
		final int t = type;
		for (final MouseListener l : listeners) {
			switch (t) {
			case MouseEvent.MOUSE_MOVED:
				l.mouseMoved(e);
				break;
			case MouseEvent.MOUSE_CLICKED:
				l.mouseClicked(e);
				break;
			case MouseEvent.MOUSE_WHEEL:
				l.wheelChanged(e);
				break;
			}
		}
	}
}
