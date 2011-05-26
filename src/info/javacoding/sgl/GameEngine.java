package info.javacoding.sgl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

import info.javacoding.sgl.graphic.Scene;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GameEngine {

	private static volatile boolean running = false;
	private static volatile long time = (Sys.getTime() * 1000)
			/ Sys.getTimerResolution();
	private static volatile long timeP = time, timeD = time - timeP;
	private static int fps;
	private static volatile int lastFPSVal;
	private static long lastFPS = time;
	private static AtomicReference<Scene> currScene = new AtomicReference<Scene>(
			null);
	private static final List<Runnable> loopTasks = new CopyOnWriteArrayList<Runnable>();

	protected static void start() {
		running = true;
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		loop();
	}

	protected static void stop() {
		running = false;
	}

	private static void loop() {
		while (!Display.isCloseRequested() && running) {
			updateTime();
			info.javacoding.sgl.input.Mouse.updateListeners();
			info.javacoding.sgl.input.Keyboard.update();
			updateFPS();
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

	private static void updateTime() {
		timeP = time;
		time = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		timeD = time - timeP;
	}

	public static long getTime() {
		return time;
	}

	public static long getTimeD() {
		return timeD;
	}

	public static void updateFPS() {
		if (time - lastFPS > 1000) {
			lastFPSVal = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	public static int getFPS() {
		return lastFPSVal;
	}

	public static void addLoopTask(final Runnable r) {
		loopTasks.add(r);
	}

	public static void removeLoopTask(final Runnable r) {
		loopTasks.remove(r);
	}

	public static void setCurrScene(final Scene s) {
		currScene.set(s);
	}
}
