package info.javacoding.sgl.graphic;

import java.awt.Polygon;

/**
 * This is anything that should be rendered in a Scene.
 * 
 * @author Joe Pritzel
 * 
 */
public interface Model {
	/**
	 * This method is called to render the model.
	 */
	public void render();
	
	public Polygon getBounds();
}
