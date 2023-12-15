package valius.model.entity.player.commands.owner;

import java.util.Optional;

import valius.model.entity.player.Player;
import valius.model.entity.player.commands.Command;
import valius.world.World;

/**
 * Teleport the player to easts.
 * 
 * @author Emiel
 */
public class Fi extends Command {

	@Override
	public void execute(Player c, String input) {
		if (World.getWorld().getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		if (c.inClanWars() || c.inClanWarsSafe()) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		if (c.inWild()) {
			return;
		}
		c.getPA().spellTeleport(3730, 3810, 0, false);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to Fossil Island");
	}

}
