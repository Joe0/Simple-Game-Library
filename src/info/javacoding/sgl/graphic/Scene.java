package info.javacoding.sgl.graphic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

/**
 * This contains everything that will be displayed on the screen, if it is set
 * to the current Scene.
 * 
 * @author Joe Pritzel
 * 
 */
public class Scene {

	/**
	 * All the models by layer.
	 */
	private final Map<Integer, List<Model>> models = new HashMap<Integer, List<Model>>();

	/**
	 * Renders all the models starting with lowest layer, then in the order they
	 * were added.
	 */
	public final void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		for (final List<Model> ms : models.values()) {
			for (final Model m : ms) {
				m.render();
			}
		}
	}

	/**
	 * Adds the model to the specified layer.
	 * 
	 * @param layer
	 * @param m
	 */
	public void addModel(final int layer, final Model m) {
		List<Model> ms = models.get(layer);
		if (ms == null) {
			ms = new ArrayList<Model>();
		}
		ms.add(m);
		models.put(layer, ms);
	}

	/**
	 * Removes the first model from the specified layer.
	 * 
	 * @param layer
	 * @param m
	 */
	public void removeModel(final int layer, final Model m) {
		final List<Model> ms = models.get(layer);
		if (ms != null) {
			ms.remove(m);
		}
	}

	/**
	 * Removes the first model from each layer..
	 * 
	 * @param m
	 */
	public void removeModel(final Model m) {
		for (final List<Model> ms : models.values()) {
			if (ms != null) {
				ms.remove(m);
			}
		}
	}

	/**
	 * Removes the first model on the first layer.
	 * 
	 * @param m
	 */
	public void removeFirstModel(final Model m) {
		for (final List<Model> ms : models.values()) {
			if (ms != null) {
				ms.remove(m);
				return;
			}
		}
	}

}
