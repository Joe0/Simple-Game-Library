import java.awt.Polygon;

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

	private float rotation = 0;

	public void start() throws LWJGLException {
		Game.create("Game", 800, 600);
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
		final SpinningSquare m2 = new SpinningSquare() {
			@Override
			public void render() {
				GL11.glColor3f(.5f, .5f, 1f);
				super.render();
			}
		};
		s.addModel(0, m2);

		final SpinningSquare m1 = new SpinningSquare() {
			@Override
			public void render() {
				GL11.glColor3f(1f, 1f, 0.5f);
				super.render();
			}
		};
		s.addModel(0, m1);
		GameEngine.setCurrScene(s);
		info.javacoding.sgl.input.Keyboard.registerHook(new Runnable() {

			@Override
			public void run() {
				float x = 0;
				float y = 0;
				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
					x -= 0.35f * GameEngine.getTimeD();
				if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
					x += 0.35f * GameEngine.getTimeD();
				if (Keyboard.isKeyDown(Keyboard.KEY_UP))
					y -= 0.35f * GameEngine.getTimeD();
				if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
					y += 0.35f * GameEngine.getTimeD();
				m2.translate(x, y);
				m1.translate(x, y);
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
		
		GameEngine.addLoopTask(new Runnable() {

			@Override
			public void run() {
				rotation += 0.15f * GameEngine.getTimeD();
				if (rotation > 360) {
					rotation -= 360;
				}
				m1.setRotation(rotation);
				m2.setRotation(rotation);
			}

		});
		Game.start();
	}

	public static void main(String[] argv) throws LWJGLException {
		TimerExample timerExample = new TimerExample();
		timerExample.start();
	}
}