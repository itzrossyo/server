package valius.model.entity.player.commands.all;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import valius.event.CycleEvent;
import valius.event.CycleEventContainer;
import valius.event.CycleEventHandler;
import valius.model.entity.player.Player;
import valius.model.entity.player.PlayerHandler;
import valius.model.entity.player.Right;
import valius.model.entity.player.commands.Command;
import valius.util.Misc;
import valius.world.World;

/**
 * Moves the selected player home after a period of time.
 * 
 * @author Matt
 */
public class Stuck extends Command {

	@Override
	public void execute(Player c, String input) {
		
		if (World.getWorld().getMultiplayerSessionListener().inAnySession(c) || c.underAttackBy > 0) {
			c.sendMessage("Finish what you are doing before doing this.");
			return;
		}
		
		if (c.inClanWars() || c.inClanWarsSafe()) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		
		if (!c.inWild()) {
			c.sendMessage("You can only use this in the wilderness.");
			return;
		}

		if (!c.isStuck) {
			c.isStuck = true;
			List<Player> staff = PlayerHandler.nonNullStream().filter(Objects::nonNull).filter(p -> p.getRights().isOrInherits(Right.MODERATOR)).collect(Collectors.toList());
			
			if (staff.size() > 0) {
				PlayerHandler.sendMessage("@blu@[Stuck] " + Misc.capitalize(c.playerName) + "" + " is stuck, teleport and help them.", staff);
				c.sendMessage("@red@You've activated stuck command and the staff online has been notified.");
				c.sendMessage("@red@Your account will be moved home in approximately 5 minutes.");
				c.sendMessage("@red@You cannot attempt ANY actions whatsoever other than talking.");
				c.sendMessage("@red@Or else your timer will be reset..");
				c.getPA().logStuck();
			} else {
				c.sendMessage("@red@You've activated stuck command and there are no staff-members online.");
				c.sendMessage("@red@Your account will be moved home in approximately 5 minutes.");
				c.sendMessage("@red@You cannot attempt ANY actions whatsoever other than talking.");
				c.sendMessage("@red@Or else your timer will be reset..");
			}
			
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c.disconnected || !c.isStuck) {
						container.stop();
						return;
					}
					if (c.isStuck) {
						c.getPlayerAssistant().movePlayer(3093, 3493, 0);
						c.sendMessage("@red@Your account has been moved home.");
						c.isStuck = false;
					}
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 1000);

		} else {
			c.sendMessage("@red@You have already activated stuck command, stay patient.");

		}
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you home if you are ever stuck");
	}

}
