package valius.content.godwars;

import valius.content.instances.InstancedAreaManager;
import valius.content.instances.MultiInstancedArea;

public class GodwarsIronmanInstance extends MultiInstancedArea {

	/**
	 * Godwars boss instance for ironman.
	 */
	public GodwarsIronmanInstance() {
		super(Godwars.GODWARS_AREA, InstancedAreaManager.GODWARS_IRONMAN_HEIGHT);
	}

	@Override
	public void onDispose() {
		InstancedAreaManager.getSingleton().add(InstancedAreaManager.GODWARS_IRONMAN_HEIGHT, new GodwarsIronmanInstance());
		throw new IllegalStateException("Godwars ironman instance has been disposed of");
	}
}
