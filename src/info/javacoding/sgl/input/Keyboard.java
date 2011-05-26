package info.javacoding.sgl.input;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

	private static List<KeyListener> listeners = new ArrayList<KeyListener>();
	private static List<Runnable> hooks = new ArrayList<Runnable>();

	/**
	 * Registers a KeyListener.
	 * @param m
	 */
	public static void registerListener(final KeyListener m) {
		listeners.add(m);
	}

	/**
	 * Unregisters a KeyListener.
	 * @param m
	 */
	public static void unregisterListener(final KeyListener m) {
		listeners.remove(m);
	}
	
	/**
	 * Calls the run method every time the keyboard is updated.
	 * @param m
	 */
	public static void registerHook(final Runnable m) {
		hooks.add(m);
	}

	/**
	 * Removes the hook.
	 * @param m
	 */
	public static void unregisterHook(final Runnable m) {
		hooks.remove(m);
	}

	/**
	 * Called by the Game Engine.<br>
	 * Runs all the KeyHooks, then fires all the events from the keyboard.
	 */
	public static void update() {
		for (final Runnable h : hooks) {
			h.run();
		}
		while (org.lwjgl.input.Keyboard.next()) {
			final int button = org.lwjgl.input.Keyboard.getEventKey();
			final char buttonChar = org.lwjgl.input.Keyboard
					.getEventCharacter();
			final boolean state = org.lwjgl.input.Keyboard.getEventKeyState();
			final KeyEvent e = new KeyEvent(button, buttonChar, state);
			for (final KeyListener l : listeners) {
				l.event(e);
			}
		}
	}
}
