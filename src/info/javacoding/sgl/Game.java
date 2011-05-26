package info.javacoding.sgl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

	private static final int cores = Runtime.getRuntime().availableProcessors();

	private static final ExecutorService cachedThreadPool = Executors
			.newCachedThreadPool();
	private static final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(cores);

	/**
	 * Creates a display with the given parameters.<br>
	 * Initializes the keyboard and mouse
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @throws LWJGLException
	 */
	public static void create(final String title, final int width,
			final int height) throws LWJGLException {
		create(title, width, height, false);
	}

	/**
	 * Creates a display with the given parameters.<br>
	 * Initializes the keyboard and mouse
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param fullscreen
	 * @throws LWJGLException
	 */
	public static void create(final String title, final int width,
			final int height, final boolean fullscreen) throws LWJGLException {
		create(title, width, height, fullscreen, false);
	}

	/**
	 * Creates a display with the given parameters.<br>
	 * Initializes the keyboard and mouse
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param fullscreen
	 * @param vsync
	 * @throws LWJGLException
	 */
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

	/**
	 * Starts the GameEngine
	 */
	public static void start() {
		GameEngine.start();
	}

	/**
	 * Stops the GameEngine, the main thread pool, and the scheduled thread pool
	 * gracefully.
	 */
	public static void stop() {
		Display.destroy();
		GameEngine.stop();
		cachedThreadPool.shutdown();
		scheduler.shutdown();
	}

	/**
	 * Stops the GameEngine, the main thread pool, and the scheduled thread
	 * pool.
	 */
	public static void stopNow() {
		Display.destroy();
		GameEngine.stop();
		cachedThreadPool.shutdownNow();
		scheduler.shutdownNow();
	}

	/**
	 * Schedules a task at a fixed rate.<br>
	 * The TimeUnit is milliseconds.
	 * 
	 * @param runnable
	 * @param delay
	 * @param period
	 */
	public static void scheduleFixedRate(final Runnable runnable,
			final long delay, final long period) {
		scheduler.scheduleAtFixedRate(runnable, delay, period,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * Schedules a task at a fixed rate.
	 * 
	 * @param runnable
	 * @param delay
	 * @param period
	 * @param unit
	 */
	public static void scheduleFixedRate(final Runnable runnable,
			final long delay, final long period, final TimeUnit unit) {
		scheduler.scheduleAtFixedRate(runnable, delay, period, unit);
	}

	/**
	 * Schedules a task at a fixed delay.<br>
	 * The TimeUnit is milliseconds.
	 * 
	 * @param runnable
	 * @param initDelay
	 * @param delay
	 */
	public static void scheduleFixedDelay(final Runnable runnable,
			final long initDelay, final long delay) {
		scheduler.scheduleWithFixedDelay(runnable, initDelay, delay,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * Schedules a task at a fixed delay.
	 * 
	 * @param runnable
	 * @param initDelay
	 * @param delay
	 * @param unit
	 */
	public static void scheduleFixedDelay(final Runnable runnable,
			final long initDelay, final long delay, final TimeUnit unit) {
		scheduler.scheduleWithFixedDelay(runnable, initDelay, delay, unit);
	}

	/**
	 * Runs the task immediately.
	 * 
	 * @param runnable
	 */
	public static void schedule(final Runnable runnable) {
		scheduler.execute(runnable);
	}

	/**
	 * Schedules the task to run once after the delay.
	 * 
	 * @param runnable
	 * @param delay
	 * @param period
	 * @param unit
	 */
	public static void schedule(final Runnable runnable, final long delay,
			final long period, final TimeUnit unit) {
		scheduler.schedule(runnable, delay, unit);
	}

	/**
	 * Schedules a task to run every 1,000ms.
	 * 
	 * @param runnable
	 */
	public static void addOneSecondScheduledTask(Runnable runnable) {
		scheduleFixedRate(runnable, 0, 1000);
	}
}
