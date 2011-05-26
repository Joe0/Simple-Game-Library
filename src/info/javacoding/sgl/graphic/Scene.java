package info.javacoding.sgl.graphic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

public class Scene {

	private final Map<Integer, List<Model>> models = new HashMap<Integer, List<Model>>();

	public final void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT
				| GL11.GL_DEPTH_BUFFER_BIT);
		for (final List<Model> ms : models.values()) {
			for (final Model m : ms) {
				m.render();
			}
		}
	}

	public void addModel(final int layer, final Model m) {
		List<Model> ms = models.get(layer);
		if (ms == null) {
			ms = new ArrayList<Model>();
		}
		ms.add(m);
		models.put(layer, ms);
	}

	public void removeModel(final int layer, final Model m) {
		final List<Model> ms = models.get(layer);
		if (ms != null) {
			ms.remove(m);
		}
	}

	public void removeModel(final Model m) {
		for (final List<Model> ms : models.values()) {
			if (ms != null) {
				ms.remove(m);
			}
		}
	}
	
	public void removeFirstModel(final Model m) {
		for (final List<Model> ms : models.values()) {
			if (ms != null) {
				ms.remove(m);
				return;
			}
		}
	}

}
