import java.awt.Polygon;
import java.awt.geom.PathIterator;

import info.javacoding.sgl.Game;
import info.javacoding.sgl.GameEngine;
import info.javacoding.sgl.graphic.Model;
import info.javacoding.sgl.graphic.Scene;
import info.javacoding.sgl.input.KeyEvent;
import info.javacoding.sgl.input.KeyListener;
import info.javacoding.sgl.util.DelayedRunnable;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class TimerExample {

	private float x = 400, y = 300;
	private float rotation = 0;

	public void start() throws LWJGLException {
		Game.create("Game", 800, 600);
		GameEngine.addLoopTask(new Runnable() {

			@Override
			public void run() {
				rotation += 0.15f * GameEngine.getTimeD();
				if (rotation > 360) {
					rotation -= 360;
				}
			}

		});
		final Scene s = new Scene();
		Game.scheduleFixedDelay(new Runnable() {
			@Override
			public void run() {
				GameEngine.addSingleTask(new DelayedRunnable() {
					@Override
					public void run() {
						Display.setTitle("FPS: " + GameEngine.getFPS());
						s.addModel(1, new Model() {
							private float scalex = .5f, scaley = .5f;
							private float x = (float) (Math.round(Math.random() * 800))
									* (1 / scalex),
									y = 0;

							@Override
							public void render() {
								GL11.glColor3f(1f, 0f, 0f);
								GL11.glPushMatrix();
								GL11.glTranslatef(x,
										y += (.1 * GameEngine.getTimeD()), 0);
								GL11.glTranslatef(-x, -y, 0);
								GL11.glScalef(.5f, .5f, .5f);
								GL11.glBegin(GL11.GL_QUADS);
								GL11.glVertex2f(x - 50, y - 50);
								GL11.glVertex2f(x + 50, y - 50);
								GL11.glVertex2f(x + 50, y + 50);
								GL11.glVertex2f(x - 50, y + 50);
								GL11.glEnd();
								GL11.glPopMatrix();
								if ((y * scaley > 600 || y * scaley < 0)) {
									s.removeModel(1, this);
								}
							}

							@Override
							public Polygon getBounds() {
								final int x = Math.round(this.x), y = Math
										.round(this.y);
								return new Polygon(new int[] { x - 50, x + 50,
										x + 50, x - 50 }, new int[] { y - 50,
										y - 50, y + 50, y + 50 }, 4);
							}

						});
					}
				});
			}
		}, 0, 1000);
		s.addModel(0, new Model() {
			private float scalex = .5f, scaley = .5f;

			@Override
			public void render() {
				GL11.glColor3f(.5f, .5f, 1f);
				GL11.glPushMatrix();
				GL11.glScalef(scalex, scaley, 0);
				GL11.glTranslatef(x * (1 / scalex), y * (1 / scaley), 0);
				GL11.glRotatef(rotation, 0f, 0f, 1f);
				GL11.glTranslatef(-x * (1 / scalex), -y * (1 / scaley), 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x * (1 / scalex) - 50, y * (1 / scaley) - 50);
				GL11.glVertex2f(x * (1 / scalex) + 50, y * (1 / scaley) - 50);
				GL11.glVertex2f(x * (1 / scalex) + 50, y * (1 / scaley) + 50);
				GL11.glVertex2f(x * (1 / scalex) - 50, y * (1 / scaley) + 50);
				GL11.glEnd();
				GL11.glPopMatrix();
			}

			@Override
			public Polygon getBounds() {
				final float rotation1 = rotation;
				final float x1 = x;
				final float y1 = y;
				int correctX = (int) Math.round(Math.cos(rotation1
						* (Math.PI / 180))
						* x1 - Math.sin(rotation1 * (Math.PI / 180)) * y1);
				int correctY = (int) Math.round(Math.sin(rotation1
						* (Math.PI / 180))
						* x1 + Math.cos(rotation1 * (Math.PI / 180)) * y1);
				return new Polygon(new int[] { correctX - 50, correctX + 50,
						correctX + 50, correctX - 50 }, new int[] {
						correctY - 50, correctY - 50, correctY + 50,
						correctY + 50 }, 4);
			}
		});

		final Model m1 = new Model() {
			private float scalex = .5f, scaley = .5f;

			@Override
			public void render() {
				GL11.glColor3f(1f, 1f, 0.5f);
				GL11.glPushMatrix();
				GL11.glScalef(scalex, scaley, 0);
				GL11.glTranslatef(x * (1 / scalex), y * (1 / scaley), 0);
				GL11.glRotatef(rotation, 0f, 0f, 1f);
				GL11.glTranslatef(-x * (1 / scalex), -y * (1 / scaley), 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x * (1 / scalex) - 50, y * (1 / scaley) - 50);
				GL11.glVertex2f(x * (1 / scalex) + 50, y * (1 / scaley) - 50);
				GL11.glVertex2f(x * (1 / scalex) + 50, y * (1 / scaley) + 50);
				GL11.glVertex2f(x * (1 / scalex) - 50, y * (1 / scaley) + 50);
				GL11.glEnd();
				GL11.glPopMatrix();
			}

			@Override
			public Polygon getBounds() {
				final float rotation1 = rotation;
				final float x1 = x;
				final float y1 = y;
				int correctX = (int) Math.round(Math.cos(rotation1
						* (Math.PI / 180))
						* x1 - Math.sin(rotation1 * (Math.PI / 180)) * y1);
				int correctY = (int) Math.round(Math.sin(rotation1
						* (Math.PI / 180))
						* x1 + Math.cos(rotation1 * (Math.PI / 180)) * y1);
				return new Polygon(new int[] { correctX - 50, correctX + 50,
						correctX + 50, correctX - 50 }, new int[] {
						correctY - 50, correctY - 50, correctY + 50,
						correctY + 50 }, 4);
			}
		};
		s.addModel(0, m1);
		GameEngine.setCurrScene(s);
		info.javacoding.sgl.input.Keyboard.registerHook(new Runnable() {

			@Override
			public void run() {
				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
					x -= 0.35f * GameEngine.getTimeD();
				if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
					x += 0.35f * GameEngine.getTimeD();
				if (Keyboard.isKeyDown(Keyboard.KEY_UP))
					y -= 0.35f * GameEngine.getTimeD();
				if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
					y += 0.35f * GameEngine.getTimeD();
				if (x < 0)
					x = 0;
				if (x > 800)
					x = 800;
				if (y < 0)
					y = 0;
				if (y > 600)
					y = 600;
			}
		});

		info.javacoding.sgl.input.Keyboard.registerListener(new KeyListener() {
			private boolean showM1 = true;

			@Override
			public void event(KeyEvent e) {
				if (e.getKey() == Keyboard.KEY_RBRACKET && e.getState()) {
					showM1 = !showM1;
					if (showM1) {
						s.addModel(0, m1);
					} else {
						s.removeModel(m1);
					}
				}
			}

		});
		Game.start();
	}

	public static void main(String[] argv) throws LWJGLException {
		TimerExample timerExample = new TimerExample();
		timerExample.start();
	}
}