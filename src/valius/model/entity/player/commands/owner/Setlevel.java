package valius.model.entity.player.commands.owner;

import java.util.stream.IntStream;

import valius.model.entity.player.Player;
import valius.model.entity.player.SkillExperience;
import valius.model.entity.player.commands.Command;
import valius.model.entity.player.skills.Skill;

/**
 * Change the level of a given skill.
 * 
 * @author Emiel
 */
public class Setlevel extends Command {

	@Override
	public void execute(Player c, String input) {
	
		String[] args = input.split(" ");
		if (args.length < 2) {
			return;
		}
		if (args.length == 3) {
			IntStream.range(Integer.parseInt(args[0]), Integer.parseInt(args[1])).forEach(skillId -> {
				c.getSkills().setLevel(Integer.parseInt(args[2]), Skill.forId(skillId));
				c.getSkills().setExperience(SkillExperience.getExperienceForLevel(Integer.parseInt(args[2])), Skill.forId(skillId));
			});
			
			c.sendMessage("You have set the skill id '" + Integer.parseInt(args[0]) + "' to '"
					+ Integer.parseInt(args[1]) + "' to level " + Integer.parseInt(args[2]) + ".");
		} else {
			int skillId = -1;
			int skillLevel;
			try {
				skillLevel = Integer.parseInt(args[1]);
				try {
					skillId = Integer.parseInt(args[0]);
				} catch (NumberFormatException ex) {
					int sID = getSkillID(args[0]);
					if (sID >= 0) {
						skillId = sID;
					} else {
						c.getDH().sendStatement("You must enter a skill name or skill id.");
					}
				}
				if (skillId < 0 || skillId >= Skill.values().length) {
					c.sendMessage("Unable to set level, skill id cannot exceed the range of 0 -> "
							+ (Skill.values().length - 1) + ".");
					return;
				}
				if (skillLevel < 1) {
					skillLevel = 1;
					// } else if (skillLevel > 99) {
					// skillLevel = 99;
				}
				c.getSkills().setLevel(skillLevel, Skill.forId(skillId));
				c.getSkills().setExperience(SkillExperience.getExperienceForLevel(skillLevel), Skill.forId(skillId));
				c.getSkills().sendRefresh();
				c.sendMessage("You have set the skill id '" + skillId + "' to level " + skillLevel + ".");
				
				c.getPA().levelUp(skillId);
			} catch (Exception e) {
				c.sendMessage("Error. Correct syntax: ::setlevel skillid level");
			}
		}
	}

	private int getSkillID(String type) {

		switch (type) {
			case "attack":
				return 0;
			case "defence":
				return 1;
			case "strength":
				return 2;
			case "hitpoints":
				return 3;
			case "ranged":
				return 4;
			case "prayer":
				return 5;
			case "magic":
				return 6;
			case "cooking":
				return 7;
			case "woodcutting":
				return 8;
			case "fletching":
				return 9;
			case "fishing":
				return 10;
			case "firemaking":
				return 11;
			case "crafting":
				return 12;
			case "smithing":
				return 13;
			case "mining":
				return 14;
			case "herblore":
				return 15;
			case "agility":
				return 16;
			case "thieving":
				return 17;
			case "slayer":
				return 18;
			case "farming":
				return 19;
			case "runecrafting":
				return 20;
			default:
				return -1;
		}
	}
}
