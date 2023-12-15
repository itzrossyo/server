package valius.model.entity.player.commands.owner;

import valius.model.entity.player.Player;
import valius.model.entity.player.commands.Command;

public class Resetdaily extends Command {

	@Override
	public void execute(Player player, String input) {
		player.dailyTaskDate = 0;
		player.totalDailyDone = 0;
		player.currentTask = null;
		player.completedDailyTask = false;
		player.playerChoice = null;
		player.dailyEnabled = false;
		player.sendMessage("dailyTaskDate: "+ player.dailyTaskDate +" | totalDailyDonate: "+ player.totalDailyDone +" | dailyTask: "+ player.currentTask == null ? "None" : player.currentTask.name().toLowerCase() +" | completedDailyTask: "+ player.completedDailyTask +"");
	}

}