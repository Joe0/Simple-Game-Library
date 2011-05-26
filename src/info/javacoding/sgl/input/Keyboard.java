package info.javacoding.sgl.input;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

	private static List<KeyListener> listeners = new ArrayList<KeyListener>();
	private static List<KeyHook> hooks = new ArrayList<KeyHook>();

	public static void registerListener(final KeyListener m) {
		listeners.add(m);
	}

	public static void unregisterListener(final KeyListener m) {
		listeners.remove(m);
	}
	
	public static void registerHook(final KeyHook m) {
		hooks.add(m);
	}

	public static void unregisterHook(final KeyHook m) {
		hooks.remove(m);
	}

	public static void update() {
		for (final KeyHook h : hooks) {
			h.run();
		}
		while (org.lwjgl.input.Keyboard.next()) {
			final int button = org.lwjgl.input.Keyboard.getEventKey();
			final char buttonChar = org.lwjgl.input.Keyboard
					.getEventCharacter();
			final boolean state = org.lwjgl.input.Keyboard.getEventKeyState();
			final KeyEvent e = new KeyEvent(button, buttonChar, state);
			for (final KeyListener l : listeners) {
				l.keyTyped(e);
			}
		}
	}
}
