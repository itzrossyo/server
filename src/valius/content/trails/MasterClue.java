package valius.content.trails;

import valius.model.entity.player.Player;
import valius.model.entity.player.skills.Skill;
import valius.util.Misc;

public class MasterClue {
	
	public static void exchangeClue(Player player) {
		if (player.getItems().getItemCount(19835, false) > 0) {
			player.sendMessage("You already have a master clue scroll, complete it and then come back.");
			return;
		}
		if (player.getItems().playerHasItem(2677) && player.getItems().playerHasItem(2801) && player.getItems().playerHasItem(2722)) {
			player.getItems().deleteItem(2677, 1);
			player.getItems().deleteItem(2801, 1);
			player.getItems().deleteItem(2722, 1);
			player.getItems().addItem(19835, 1);
			generateRequirement(player);
			player.sendMessage("Here you go, a master clue just for you;");
			player.sendMessage("A " + Skill.forId(player.masterClueRequirement[0]).name().toLowerCase() + " level of " + player.masterClueRequirement[1] + " and " + Skill.forId(player.masterClueRequirement[2]).name().toLowerCase() + " level of " + player.masterClueRequirement[3] + " is what you need.");
		} else {
			player.sendMessage("One of each, easy, medium and hard clue scroll is what I ask from you.");
			return;
		}
	}
	
	//TODO: Generate a chance for boss fight
	public static void generateRequirement(Player player) {
		int SKILL_ONE = Misc.random(21);
		int SKILL_TWO = Misc.random(21);
		int LEVEL_REQUIREMENT_ONE = Misc.random(29) + 70;
		int LEVEL_REQUIREMENT_TWO = Misc.random(29) + 70;
		
		player.masterClueRequirement[0] = SKILL_ONE;
		player.masterClueRequirement[1] = LEVEL_REQUIREMENT_ONE;
		player.masterClueRequirement[2] = SKILL_TWO;
		player.masterClueRequirement[3] = LEVEL_REQUIREMENT_TWO;
	}

}
