package valius.content;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

import valius.model.entity.player.GlobalMessages;
import valius.model.entity.player.Player;
import valius.util.Misc;

public class Tutorial {

	/**
	 * The stage of the tutorial
	 */
	private Stage stage = null;

	/**
	 * The final stage of the tutorial
	 */
	private static final Stage FINAL_STAGE = Stage.END;

	/**te
	 * The player in this tutorial
	 */
	private Player player;

	/**
	 * The interface for iron man selection
	 */
	private final IronManInterface ironManInterface;

	/**
	 * h Creates a new tutorial for the player
	 * 
	 * @param player the player the tutorial is created for
	 */
	public Tutorial(Player player) {
		this.player = player;
		this.ironManInterface = new IronManInterface(player);
	}

	/**
	 * A method used to continue the tutorial to the next stage
	 */
	public void proceed() {
		if (stage == Stage.END) {
			return;
		}
		stage = stage.next();
		if (stage == Tutorial.FINAL_STAGE) {
			stop();
			return;
		}
		refresh();
	}

	/**
	 * Refreshes the current stage so that it shows properly for the player. In some cases a player may accidently close an interface, in which case that interface should be
	 * re-opened.
	 */
	public void refresh() {
		if (stage == null) {
			return;
		}
		if (stage == Stage.END) {
			return;
		}
		stage.getScene().display(player);
	}

	/**
	 * Stops the tutorial for the player and finalizes it in some fashion.
	 */
	private void stop() {

	}

	/**
	 * Automatically finishes the tutorial in some fashion without completing it properly.
	 */
	public void autoComplete() {
		stage = Stage.END;
	}

	/**
	 * Determines if the tutorial is still active
	 * 
	 * @return true if the event is active, otherwise false
	 */
	public boolean isActive() {
		return stage != Stage.END;
	}

	/**
	 * The stage for the tutorial.
	 * 
	 * @return the stage the player is on
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Modifies the stage
	 * 
	 * @param stage the new stage
	 * @return the stage
	 */
	public Stage setStage(Stage stage) {
		return this.stage = stage;
	}

	public IronManInterface getIronmanInterface() {
		return ironManInterface;
	}

	/**
	 * An interface used to display information to the player about a particular scene. Each stage has a scene that displays information to the player.
	 */
	private interface Scene {

		/**
		 * Displays some information about the stage to the player
		 * 
		 * @param player the player
		 */
		void display(Player player);
	}

	/**
	 * The stages of the tutorial
	 */
	public enum Stage {
		START(0, new Scene() {

			@Override
			public void display(Player player) {
				player.getDH().sendDialogues(673, 311);
			}

		}), MODE_SELECTION(1, new Scene() {

			@Override
			public void display(Player player) {
				player.getPA().showInterface(42400);
				player.getTutorial().getIronmanInterface().refresh();
			}
		}), MODE_DIALOGUE(2, new Scene() {

			@Override
			public void display(Player player) {
				player.getDH().sendDialogues(647, 311);
			}
		}), APPEND_STARTER(3, new Scene() {

			@Override
			public void display(Player player) {
				if (player.getMode().isIronman()) {
					player.getItems().wearItem(12810, 1, player.playerHat);
					player.getItems().wearItem(12811, 1, player.playerChest);
					player.getItems().wearItem(12812, 1, player.playerLegs);
					player.getItems().wearItem(33893, 1, player.playerWeapon);
					player.getItems().wearItem(33890, 1, player.playerCape);
					player.getItems().wearItem(33892, 1, player.playerShield);
					player.getItems().addItem(33269, 1);
					player.getItems().addItem(33498, 1);
					player.getItems().addItem(33486, 1);
					player.getItems().addItem(33487, 1);
					player.getItems().addItem(33488, 1);
					player.getItems().addItem(33489, 1);
					player.getItems().addItem(33490, 1);
					player.getItems().addItem(33894, 1);
					player.getItems().addItem(33891, 1);
					player.getItems().addItem(1171, 1);
					player.getItems().addItem(841, 1);
					player.getItems().addItem(882, 100);
					player.getItems().addItem(556, 100);
					player.getItems().addItem(558, 100);
					player.getItems().addItem(555, 100);
					player.getItems().addItem(557, 100);
					player.getItems().addItem(559, 100);
					player.getItems().addItem(554, 100);
					player.getItems().addItem(563, 100);
					player.getItems().addItem(1381, 1);
					player.bonusXpTime50 = 3000;
					player.sendMessage("@blu@You now have " + player.bonusXpTime50 / 100 + " Minutes of 50% BONUS XP! ::Vote to receive 30 more minutes!");
					GlobalMessages.send(Misc.capitalizeJustFirst(player.playerName) + " has logged in for the first time! Welcome!", GlobalMessages.MessageType.NEWS);
					player.getPA().sendString("https://discord.gg/UQABRTC", 12000);
				} else if (player.getMode().isUltimateIronman()) {
					player.getItems().wearItem(12813, 1, player.playerHat);
					player.getItems().wearItem(12814, 1, player.playerChest);
					player.getItems().wearItem(12815, 1, player.playerLegs);
					player.getItems().wearItem(33893, 1, player.playerWeapon);
					player.getItems().wearItem(33890, 1, player.playerCape);
					player.getItems().wearItem(33892, 1, player.playerShield);
					player.getItems().addItem(33269, 1);
					player.getItems().addItem(33498, 1);
					player.getItems().addItem(33486, 1);
					player.getItems().addItem(33487, 1);
					player.getItems().addItem(33488, 1);
					player.getItems().addItem(33489, 1);
					player.getItems().addItem(33490, 1);
					player.getItems().addItem(33894, 1);
					player.getItems().addItem(33891, 1);
					player.getItems().addItem(1171, 1);
					player.getItems().addItem(841, 1);
					player.getItems().addItem(882, 100);
					player.getItems().addItem(556, 100);
					player.getItems().addItem(558, 100);
					player.getItems().addItem(555, 100);
					player.getItems().addItem(557, 100);
					player.getItems().addItem(559, 100);
					player.getItems().addItem(554, 100);
					player.getItems().addItem(563, 100);
					player.getItems().addItem(1381, 1);
					player.bonusXpTime50 = 3000;
					player.sendMessage("@blu@You now have " + player.bonusXpTime50 / 100 + " Minutes of 50% BONUS XP! ::Vote to receive 30 more minutes!");
					GlobalMessages.send(Misc.capitalizeJustFirst(player.playerName) + " has logged in for the first time! Welcome!", GlobalMessages.MessageType.NEWS);
					player.getPA().sendString("https://discord.gg/UQABRTC", 12000);
				} else if (player.getMode().isHcIronman()) {
					player.getItems().wearItem(20792, 1, player.playerHat);
					player.getItems().wearItem(20794, 1, player.playerChest);
					player.getItems().wearItem(20796, 1, player.playerLegs);
					player.getItems().wearItem(33893, 1, player.playerWeapon);
					player.getItems().wearItem(33890, 1, player.playerCape);
					player.getItems().wearItem(33892, 1, player.playerShield);
					player.getItems().addItem(33269, 1);
					player.getItems().addItem(33498, 1);
					player.getItems().addItem(33486, 1);
					player.getItems().addItem(33487, 1);
					player.getItems().addItem(33488, 1);
					player.getItems().addItem(33489, 1);
					player.getItems().addItem(33490, 1);
					player.getItems().addItem(33894, 1);
					player.getItems().addItem(33891, 1);
					player.getItems().addItem(1171, 1);
					player.getItems().addItem(841, 1);
					player.getItems().addItem(882, 100);
					player.getItems().addItem(556, 100);
					player.getItems().addItem(558, 100);
					player.getItems().addItem(555, 100);
					player.getItems().addItem(557, 100);
					player.getItems().addItem(559, 100);
					player.getItems().addItem(554, 100);
					player.getItems().addItem(563, 100);
					player.getItems().addItem(1381, 1);
					player.bonusXpTime50 = 3000;
					player.sendMessage("@blu@You now have " + player.bonusXpTime50 / 100 + " Minutes of 50% BONUS XP! ::Vote to receive 30 more minutes!");
					GlobalMessages.send(Misc.capitalizeJustFirst(player.playerName) + " has logged in for the first time! Welcome!", GlobalMessages.MessageType.NEWS);
					player.getPA().sendString("https://discord.gg/UQABRTC", 12000);
				} else if (player.getMode().isGroupIronman()) {
					player.getItems().wearItem(33601, 1, player.playerHat);
					player.getItems().wearItem(33602, 1, player.playerChest);
					player.getItems().wearItem(33603, 1, player.playerLegs);
					player.getItems().wearItem(33893, 1, player.playerWeapon);
					player.getItems().wearItem(33890, 1, player.playerCape);
					player.getItems().wearItem(33892, 1, player.playerShield);
					player.getItems().addItem(33269, 1);
					player.getItems().addItem(33498, 1);
					player.getItems().addItem(33486, 1);
					player.getItems().addItem(33487, 1);
					player.getItems().addItem(33488, 1);
					player.getItems().addItem(33489, 1);
					player.getItems().addItem(33490, 1);
					player.getItems().addItem(33894, 1);
					player.getItems().addItem(33891, 1);
					player.getItems().addItem(1171, 1);
					player.getItems().addItem(841, 1);
					player.getItems().addItem(882, 100);
					player.getItems().addItem(556, 100);
					player.getItems().addItem(558, 100);
					player.getItems().addItem(555, 100);
					player.getItems().addItem(557, 100);
					player.getItems().addItem(559, 100);
					player.getItems().addItem(554, 100);
					player.getItems().addItem(563, 100);
					player.getItems().addItem(1381, 1);
					player.bonusXpTime50 = 3000;
					player.sendMessage("@blu@You now have " + player.bonusXpTime50 / 100 + " Minutes of 50% BONUS XP! ::Vote to receive 30 more minutes!");
					GlobalMessages.send(Misc.capitalizeJustFirst(player.playerName) + " has logged in for the first time! Welcome!", GlobalMessages.MessageType.NEWS);
					player.getPA().sendString("https://discord.gg/UQABRTC", 12000);
				} else {
					GlobalMessages.send(Misc.capitalizeJustFirst(player.playerName) + " has logged in for the first time! Welcome!", GlobalMessages.MessageType.NEWS);
					player.getPA().sendString("https://discord.gg/UQABRTC", 12000);
					player.getPA().addStarter();
				} 
				player.getTutorial().proceed();
			}

		}), EXPRATE_DIALOGUE (4, new Scene() {

			@Override
			public void display(Player player) {
				if (player.getMode().isGroupIronman()) {
					player.getTutorial().proceed();
				} else {
				player.getDH().sendDialogues(672, -1);
			}
		}
			
		}), LAST_DIALOGUE(5, new Scene() {

			@Override
			public void display(Player player) {
				player.getDH().sendDialogues(649, 311);
			}

		}), END(6, new Scene() {

			@Override
			public void display(Player player) {
				player.getPA().showInterface(3559);
				player.canChangeAppearance = true;
			}

		});

		/**
		 * The identification value of the stage, representing where this {@link Stage} object exists amongst others in chronological order. The order should initially start at 0
		 * and increase by 1 for every element.
		 */
		private final int id;

		/**
		 * The scene for the stage
		 */
		private final Scene scene;

		/**
		 * Creates a new Stage with an identification value
		 * 
		 * @param id the id of the stage
		 */
		private Stage(int id, Scene scene) {
			this.id = id;
			this.scene = scene;
		}

		/**
		 * Used to compare the {@link Stage#id} of one {@link Stage} to another.
		 */
		private static final Comparator<Stage> COMPARE_STAGES = (one, two) -> Integer.compare(one.id, two.id);

		/**
		 * An unmodifiable {@link Set} of all elements in the {@link Stage} enumeration.
		 */
		private static final List<Stage> ALL_STAGES = ImmutableList.copyOf(Ordering.from(COMPARE_STAGES).sortedCopy(Arrays.asList(values())));

		/**
		 * Retrieves the next element in an ordered list based on where this element exists in that ordered list. The list is ordered by the {@link Stage#id}, in chronological
		 * order.
		 * 
		 * @return
		 */
		public final Stage next() {
			int currentIndex = ALL_STAGES.indexOf(this);

			if (currentIndex == ALL_STAGES.size() - 1) {
				return Stage.END;
			}

			return ALL_STAGES.get(currentIndex + 1);
		}

		/**
		 * The {@link Scene} used to display information about the tutorial to the player
		 * 
		 * @return the scene
		 */
		public final Scene getScene() {
			return scene;
		}
	}
}
