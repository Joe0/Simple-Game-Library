import info.javacoding.sgl.Game;
import info.javacoding.sgl.GameEngine;
import info.javacoding.sgl.graphic.Model;
import info.javacoding.sgl.graphic.Scene;
import info.javacoding.sgl.input.KeyEvent;
import info.javacoding.sgl.input.KeyHook;
import info.javacoding.sgl.input.KeyListener;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class TimerExample {

	float x = 400, y = 300;
	float rotation = 0;
	long lastFrame;
	int fps;
	long lastFPS;

	public void start() throws LWJGLException {
		Game.create("Game", 800, 600);
		GameEngine.addLoopTask(new Runnable() {

			@Override
			public void run() {
				rotation += 0.15f * GameEngine.getTimeD();
				if(rotation > 360) {
					rotation -= 360;
				}
			}

		});
		final Scene s = new Scene();
		s.addModel(0, new Model() {
			@Override
			public void render() {
				GL11.glColor3f(0.5f, 0.5f, 1.0f);
				GL11.glPushMatrix();
				GL11.glTranslatef(x, y, 0);
				GL11.glRotatef(rotation, 0f, 0f, 1f);
				GL11.glTranslatef(-x, -y, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x - 50, y - 50);
				GL11.glVertex2f(x + 50, y - 50);
				GL11.glVertex2f(x + 50, y + 50);
				GL11.glVertex2f(x - 50, y + 50);
				GL11.glEnd();
				GL11.glPopMatrix();
			}
		});

		final Scene s1 = new Scene();
		s1.addModel(0, new Model() {
			@Override
			public void render() {
				GL11.glColor3f(1f, 1f, 0.5f);
				GL11.glPushMatrix();
				GL11.glTranslatef(x, y, 0);
				GL11.glRotatef(rotation, 0f, 0f, 1f);
				GL11.glTranslatef(-x, -y, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x - 50, y - 50);
				GL11.glVertex2f(x + 50, y - 50);
				GL11.glVertex2f(x + 50, y + 50);
				GL11.glVertex2f(x - 50, y + 50);
				GL11.glEnd();
				GL11.glPopMatrix();
			}
		});
		GameEngine.setCurrScene(s);
		info.javacoding.sgl.input.Keyboard.registerHook(new KeyHook() {

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
			private int val = 0;

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKey() == Keyboard.KEY_RBRACKET && e.getState()) {
					GameEngine.setCurrScene(val++ % 2 == 0 ? s : s1);
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