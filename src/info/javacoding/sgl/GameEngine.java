package info.javacoding.sgl;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

import info.javacoding.sgl.graphic.Scene;
import info.javacoding.sgl.util.DelayedRunnable;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GameEngine {

	/**
	 * Set this to false to stop the GameEngine so you don't need to desroy Display.
	 */
	private static volatile boolean running = false;
	
	/**
	 * The current time.
	 */
	private static volatile long time = (Sys.getTime() * 1000)
			/ Sys.getTimerResolution();
	
	/**
	 * The previous value for time.
	 */
	private static volatile long timeP = time;
	
	/**
	 * Time delta.
	 */
	private static volatile long timeD = time - timeP;
	
	/**
	 * The current FPS.
	 */
	private static int fps;
	
	/**
	 * The last value for FPS before it was reset.
	 */
	private static volatile int lastFPSVal;
	
	/**
	 * The last time the FPS was set.
	 */
	private static long lastFPS = time;
	
	/**
	 * The current Scene.
	 */
	private static AtomicReference<Scene> currScene = new AtomicReference<Scene>(
			null);
	
	/**
	 * A list of loop tasks.
	 */
	private static final List<Runnable> loopTasks = new CopyOnWriteArrayList<Runnable>();
	
	/**
	 * Tasks to do once.
	 */
	private static final Queue<DelayedRunnable> singleTask = new DelayQueue<DelayedRunnable>();

	/**
	 * Starts the GameEngine, and 
	 */
	protected static void start() {
		running = true;
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		loop();
	}

	/**
	 * Stops the GameEngine, but doesn't destroy the Display.
	 */
	protected static void stop() {
		running = false;
	}

	/**
	 * The main loop.
	 */
	private static void loop() {
		while (!Display.isCloseRequested() && running) {
			updateTime();
			info.javacoding.sgl.input.Mouse.update();
			info.javacoding.sgl.input.Keyboard.update();
			updateFPS();
			while(!singleTask.isEmpty()) {
				singleTask.poll().run();
			}
			for (final Runnable r : loopTasks) {
				r.run();
			}
			if (currScene.get() != null) {
				currScene.get().render();
			}
			Display.update();
		}
		Display.destroy();
	}

	/**
	 * Updates the current time.
	 */
	private static void updateTime() {
		timeP = time;
		time = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		timeD = time - timeP;
	}

	/**
	 * @return The current time in MS
	 */
	public static long getTime() {
		return time;
	}

	/**
	 * @return Δtime
	 */
	public static long getTimeD() {
		return timeD;
	}

	/**
	 * Updates the FPS.
	 */
	public static void updateFPS() {
		if (time - lastFPS > 1000) {
			lastFPSVal = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	/**
	 * @return The current FPS.
	 */
	public static int getFPS() {
		return lastFPSVal;
	}

	/**
	 * Adds a task to the game loop.
	 * @param r
	 */
	public static void addLoopTask(final Runnable r) {
		loopTasks.add(r);
	}

	/**
	 * Removes a task from the game loop.
	 * @param r
	 */
	public static void removeLoopTask(final Runnable r) {
		loopTasks.remove(r);
	}

	/**
	 * Sets the current Scene.
	 * @param s
	 */
	public static void setCurrScene(final Scene s) {
		currScene.set(s);
	}
	
	/**
	 * Adds a task that is executed once.
	 * @param r
	 */
	public static void addSingleTask(final DelayedRunnable r) {
		singleTask.add(r);
	}
}
