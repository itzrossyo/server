package valius.model.entity.player.skills;

import valius.event.CycleEvent;
import valius.event.CycleEventContainer;
import valius.event.CycleEventHandler;
import valius.model.entity.player.Player;

/**
 * Agility.java
 * 
 * @author Acquittal
 *
 *
 **/

public class Agility {

	public Player client;
	public int agtimer = 10;
	public boolean bonus = false;

	public Agility(Player c) {
		client = c;
	}

	public void brimhavenMonkeyBars(Player c, String Object, int level, int x, int y, int a, int b, int xp) {
		if (c.getSkills().getLevel(Skill.AGILITY) < level) {
			c.sendMessage("You need a Agility level of " + level + " to pass this " + Object + ".");
			return;
		}
		if (c.getX() == a && c.getY() == b) {
			c.getPA().walkTo3(x, y);
			c.getPA().addSkillXP(xp, Skill.AGILITY.getId(), true);
			c.getPA().refreshSkill(Skill.AGILITY.getId());
		}
	}

	/*
	 * Wilderness course
	 */

	public void wildernessEntrance(Player c, String Object, int level, int x, int y, int a, int b) {
		if (c.getSkills().getLevel(Skill.AGILITY) < level) {
			c.sendMessage("You need a Agility level of " + level + " to pass this " + Object + ".");
			return;
		}
		if (c.getX() == a && c.getY() == b) {
			c.getPA().walkTo3(x, y);
		}
	}

	public void doWildernessEntrance(final Player c, int x, int y, boolean reverse) {
		if (c.freezeTimer > 0) {
			return;
		}
		c.freezeTimer = 17;
		c.stopMovement();
		c.playerWalkIndex = 762;
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
		c.getAgility().wildernessEntrance(c, "Door", 1, 0, reverse ? -15 : +15, x, y);// 2998, 3916
		c.foodDelay = System.currentTimeMillis();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.getPA().addSkillXP(40, Skill.AGILITY.getId(), true);
				c.getPA().refreshSkill(Skill.AGILITY.getId());
				container.stop();
			}

			@Override
			public void stop() {
			}
		}, 17);
	}
}