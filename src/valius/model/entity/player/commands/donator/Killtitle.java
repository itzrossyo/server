package valius.model.entity.player.commands.donator;

import java.util.Optional;

import valius.model.entity.player.Player;
import valius.model.entity.player.commands.Command;

/**
 * Changes the title of the player to their default kill title.
 * 
 * @author Emiel
 */
public class Killtitle extends Command {

	@Override
	public void execute(Player c, String input) {
		c.sendMessage("You will now be using your kill title instead. Relog for changes to take effect.");
		c.keepTitle = false;
		c.killTitle = true;
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Changes your kill title");
	}

	@Override
	public Optional<String> getParameter() {
		return Optional.of("title");
	}

}
