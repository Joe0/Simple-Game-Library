import info.javacoding.sgl.graphic.Model;

import java.awt.Polygon;

import org.lwjgl.opengl.GL11;


public class SpinningSquare implements Model {
	private float x = 400, y = 300;
	private float rotation = 0;
	private float scalex = .5f, scaley = .5f;

	@Override
	public void render() {
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
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
		if (x < 0)
			x = 0;
		if (x > 800)
			x = 800;
		if (y < 0)
			y = 0;
		if (y > 600)
			y = 600;
	}
}
