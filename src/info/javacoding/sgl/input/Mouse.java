package info.javacoding.sgl.input;

import java.util.ArrayList;
import java.util.List;

public class Mouse {

	private static List<MouseListener> listeners = new ArrayList<MouseListener>();

	public static void registerListener(final MouseListener m) {
		listeners.add(m);
	}

	public static void unregisterListener(final MouseListener m) {
		listeners.remove(m);
	}

	private static int lastX = -1, lastY = -1;

	public static void updateListeners() {
		final int x = org.lwjgl.input.Mouse.getEventX(), y = org.lwjgl.input.Mouse
				.getEventY(), button = org.lwjgl.input.Mouse.getEventButton();
		int type = MouseEvent.MOUSE_WHEEL;
		if (lastX != x || lastY != y) {
			type = MouseEvent.MOUSE_MOVED;
		}
		if (button != -1) {
			type = MouseEvent.MOUSE_CLICKED;
		}
		final MouseEvent e = new MouseEvent(x, y, button);
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
