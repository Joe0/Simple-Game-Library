package info.javacoding.sgl.util;

import info.javacoding.sgl.GameEngine;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * If put into a collection that uses the Delayed interface, it works.
 * 
 * @author Joe Pritzel
 * 
 */
public abstract class DelayedRunnable implements Delayed, Runnable {

	private final long delay;
	private final TimeUnit u;
	private final long insertTime;

	/**
	 * Creates a DelayedRunnable with a delay of 0ms.
	 */
	public DelayedRunnable() {
		this(0);
	}

	/**
	 * Creates a DelayedRunnable with the specified delay in ms.
	 * 
	 * @param i
	 */
	public DelayedRunnable(final long i) {
		this(i, TimeUnit.MILLISECONDS);
	}

	/**
	 * Creates a DelayedRunnable with the specified delay.
	 * 
	 * @param i
	 * @param unit
	 */
	public DelayedRunnable(final long i, final TimeUnit unit) {
		this.delay = i;
		this.u = unit;
		this.insertTime = GameEngine.getTime();
	}

	@Override
	public int compareTo(final Delayed d) {
		if (getDelay(u) < d.getDelay(u))
			return -1;
		else if (getDelay(u) == d.getDelay(u))
			return 0;
		return 1;
	}

	@Override
	public long getDelay(final TimeUnit unit) {
		return unit.convert(insertTime - GameEngine.getTime() + delay, u);
	}

	@Override
	public abstract void run();

}
