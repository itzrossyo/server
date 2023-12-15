package valius.model.entity.player.skills;

import org.apache.commons.lang3.RandomUtils;

import valius.clip.Region;
import valius.content.achievement_diary.falador.FaladorDiaryEntry;
import valius.content.achievement_diary.kandarin.KandarinDiaryEntry;
import valius.event.Event;
import valius.model.Location;
import valius.model.entity.player.Boundary;
import valius.model.entity.player.Player;
import valius.world.World;
import valius.world.objects.GlobalObject;

public final class FlaxPicking {

	/**
	 * The single instance of this class
	 */
	private static final FlaxPicking INSTANCE = new FlaxPicking();

	/**
	 * Attempts to pick a new flax on the map by creating an event that continues until there is no flax or the player has no more room.
	 * 
	 * @param player the player picking the flax
	 * @param location the location of the flax
	 */
	public final void pick(Player player, Location location) {
		player.getSkilling().stop();
		if (player.getItems().freeSlots() == 0) {
			player.sendMessage("You have run out of free slots.");
			return;
		}
		player.getSkilling().setSkill(Skill.CRAFTING);
		World.getWorld().getEventHandler().submit(new FlaxPickingEvent(player, 4, location));
	}

	/**
	 * The single {@link FlaxPicking} object that exists
	 * 
	 * @return the single instance
	 */
	public static final FlaxPicking getInstance() {
		return INSTANCE;
	}

	/**
	 * A class that is created to handle the flax picking event for a single player
	 * 
	 * @author Jason MacKeigan
	 * @date May 15, 2015, 2015, 10:54:44 AM
	 */
	private final class FlaxPickingEvent extends Event<Player> {

		/**
		 * The location of the flax
		 */
		private final Location location;

		/**
		 * Creates a new {@link FlaxPickingEvent} for a single player
		 * 
		 * @param attachment the player
		 * @param ticks the time the event is alive for
		 * @param location the location
		 */
		public FlaxPickingEvent(Player attachment, int ticks, Location location) {
			super("skilling", attachment, ticks);
			this.location = location;
		}

		@Override
		public void execute() {
			if (attachment == null || attachment.disconnected) {
				stop();
				return;
			}

			if (attachment.getItems().freeSlots() == 0) {
				attachment.sendMessage("You have run out of free slots.");
				stop();
				return;
			}

			boolean originalObject = Region.isWorldObject(14896, location.getX(), location.getY(), location.getZ());
			boolean spawnedObject = World.getWorld().getGlobalObjects().exists(14896, location.getX(), location.getY());

			if (!originalObject && !spawnedObject || World.getWorld().getGlobalObjects().exists(-1, location.getX(), location.getY())) {
				stop();
				return;
			}
			

			if (Boundary.isIn(attachment, Boundary.FALADOR_BOUNDARY)) {
				attachment.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.PICK_FLAX);
			}
			if (Boundary.isIn(attachment, Boundary.SEERS_BOUNDARY)) {
				attachment.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.PICK_FLAX_SEERS);
			}
			attachment.startAnimation(827);
			attachment.getItems().addItem(1779, 1);

			if (RandomUtils.nextInt(0, 3) == 1) {
				World.getWorld().getGlobalObjects().add(new GlobalObject(-1, location.getX(), location.getY(), location.getZ(), 0, 10, 50, 14896));
				stop();
			}
		}

		@Override
		public void stop() {
			super.stop();
			if (attachment != null && !attachment.disconnected) {
				attachment.stopAnimation();
			}
		}

	}

}
