package info.javacoding.sgl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

	private static final ExecutorService cachedThreadPool = Executors
			.newCachedThreadPool();
	
	public static void create(final String title, final int width,
			final int height) throws LWJGLException {
		create(title, width, height, false);
	}

	public static void create(final String title, final int width,
			final int height, final boolean fullscreen) throws LWJGLException {
		create(title, width, height, fullscreen, false);
	}

	public static void create(final String title, final int width,
			final int height, final boolean fullscreen, final boolean vsync)
			throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setFullscreen(fullscreen);
		Display.setVSyncEnabled(vsync);
		Display.setTitle(title);
		Display.create();
		Mouse.create();
		Keyboard.create();
	}

	public static void start() {

				GameEngine.start();
	}
	
	public static void stop() {
		GameEngine.stop();
		cachedThreadPool.shutdown();
	}
}
