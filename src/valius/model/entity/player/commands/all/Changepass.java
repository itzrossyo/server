package valius.model.entity.player.commands.all;

import java.util.Optional;

import valius.model.entity.player.Player;
import valius.model.entity.player.commands.Command;

/**
 * Changes the password of the player.
 * 
 * @author Emiel
 *
 */
public class Changepass extends Command {

	@Override
	public void execute(Player c, String input) {
		if (input.length() > 20) {
			c.sendMessage("Passwords cannot contain more than 20 characters.");
			c.sendMessage("The password you tried had " + input.length() + " characters.");
			return;
		}
		if (input.contains("character-rights") || input.contains("[CHARACTER]")) {
			c.sendMessage("Your password contains illegal characters.");
			return;
		}
		c.playerPass = input;
		c.sendMessage("Your password is now: @red@" + c.playerPass);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Changes your password");
	}

	@Override
	public Optional<String> getParameter() {
		return Optional.of("password");
	}

}
