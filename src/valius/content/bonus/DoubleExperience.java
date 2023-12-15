package valius.content.bonus;

import java.util.Calendar;

import valius.model.entity.player.Player;

public class DoubleExperience {

	public static boolean isDoubleExperience(Player player) {
			return (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
	}
}
