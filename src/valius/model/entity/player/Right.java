package valius.model.entity.player;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import valius.util.Misc;

/**
 * The rights of a player determines their authority. Every right can be viewed with a name and a value. The value is used to separate each right from one another.
 * 
 * @author Jason MacK
 * @date January 22, 2015, 5:23:49 PM
 */

public enum Right implements Comparator<Right> {
	
	PLAYER(0, "000000"), 
	HELPER(11, "004080"), 
	MODERATOR(1, "666666", HELPER), 
	ADMINISTRATOR(2, "F5FF0F", MODERATOR), 
	OWNER(3, "F5FF0F", ADMINISTRATOR), 
	UNKNOWN(4, "F5FF0F"), 
	SAPPHIRE(5, "B60818"), 
	EMERALD(6, "063DCF", SAPPHIRE), 
	RUBY(7, "05AD30", EMERALD), 
	
	DIAMOND(8, "C4F1F1", RUBY),
	DRAGONSTONE(9, "4C05CA", DIAMOND),
	ONYX(17, "0F0000", DRAGONSTONE),
	ZENYTE(18, "C78B02", ONYX),
	HYDRIX(19, "DED000", ZENYTE),
	MAX_DONATOR(20, "9E6405", HYDRIX),
	
	RESPECTED_MEMBER(10, "272727"), 
	HITBOX(12, "437100"), 
	IRONMAN(13, "3A3A3A"), 
	ULTIMATE_IRONMAN(14, "717070"), 
	YOUTUBER(15, "FE0018"), 
	GAME_DEVELOPER(16, "544FBB"), 
	EXTREME(23, "437100"),
	HC_IRONMAN(24, "FE0018"),
	CLASSIC(25, "437100"), 
	GROUP_IRONMAN(26, "666666"),
	ELITE(27, "437100"), 
	BETA_TESTER(30, "96ff00", DIAMOND);

	/**
	 * The level of rights that define this
	 */
	private final int right;

	/**
	 * The rights inherited by this right
	 */
	private final List<Right> inherited;

	/**
	 * The color associated with the right
	 */
	private final String color;

	/**
	 * Creates a new right with a value to differentiate it between the others
	 * 
	 * @param right the right required
	 * @param color a color thats used to represent the players name when displayed
	 * @param inherited the right or rights inherited with this level of right
	 */
	private Right(int right, String color, Right... inherited) {
		this.right = right;
		this.inherited = Arrays.asList(inherited);
		this.color = color;
	}

	/**
	 * The rights of this enumeration
	 * 
	 * @return the rights
	 */
	public int getValue() {
		return right;
	}

	/**
	 * Returns a {@link Rights} object for the value.
	 * 
	 * @param value the right level
	 * @return the rights object
	 */
	public static Right get(int value) {
		return RIGHTS.stream().filter(element -> element.right == value).findFirst().orElse(PLAYER);
	}

	/**
	 * A {@link Set} of all {@link Rights} elements that cannot be directly modified.
	 */
	private static final Set<Right> RIGHTS = Collections.unmodifiableSet(EnumSet.allOf(Right.class));

	/**
	 * The color associated with the right
	 * 
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Determines if this level of rights inherited another level of rights
	 * 
	 * @param rights the level of rights we're looking to determine is inherited
	 * @return {@code true} if the rights are inherited, otherwise {@code false}
	 */
	public boolean isOrInherits(Right right) {
		/*if (this == right) 
			return true;
		for (int i = 0; i < inherited.size(); i++) {
			if (this == inherited.get(i))
				return true;
		}
		System.out.println("inherited.size: "+inherited.size());
		return false;*/
		return this == right || inherited.size() > 0 && inherited.stream().anyMatch(r -> r.isOrInherits(right));
	}
	
	/**
	 * Determines if the players rights equal that of {@linkplain MODERATOR}
	 * @return	true if they are of type moderator
	 */
	public boolean isModerator() {
		return equals(MODERATOR);
	}
	
	/**
	 * Determines if the players rights equal that of {@linkplain HELPER}
	 * @return	true if they are of type moderator
	 */
	public boolean isHelper() {
		return equals(HELPER);
	}
	
	/**
	 * Determines if the players rights equal that of {@linkplain ADMINISTRATOR}
	 * @return	true if they are of type administrator
	 */
	public boolean isAdministrator() {
		return equals(ADMINISTRATOR);
	}
	
	/**
	 * Determines if the players rights equal that of {@linkplain OWNER}
	 * @return	true if they are of type owner
	 */
	public boolean isOwner() {
		return equals(OWNER);
	}

	/**
	 * Determines if the players right equal that of {@link MODERATOR}, {@link ADMINISTRATOR},
	 * and {@link OWNER}
	 * @return	true if they are any of the predefined types
	 */
	public boolean isStaff() {
		return isHelper() || isModerator() || isAdministrator() || isOwner();
	}
	
	public boolean isManagment() {
		return isAdministrator() || isOwner();
	}
	
	/**
	 * An array of {@link Right} objects that represent the order in which some rights should be prioritized over others. The index at which a {@link Right} object exists
	 * determines it's priority. The lower the index the less priority that {@link Right} has over another. The list is ordered from lowest priority to highest priority.
	 * <p>
	 * An example of this would be comparing a {@link #MODERATOR} to a {@link #ADMINISTRATOR}. An {@link #ADMINISTRATOR} can be seen as more 'powerful' when compared to a
	 * {@link #MODERATOR} because they have more power within the community.
	 * </p>
	 */

	public static final Right[] PRIORITY = { PLAYER, EXTREME, CLASSIC, ELITE, IRONMAN, ULTIMATE_IRONMAN, HC_IRONMAN, GROUP_IRONMAN, SAPPHIRE, EMERALD, RUBY, DIAMOND, DRAGONSTONE, ONYX, ZENYTE, HYDRIX, MAX_DONATOR, RESPECTED_MEMBER, HITBOX, YOUTUBER, HELPER,
			GAME_DEVELOPER, MODERATOR, ADMINISTRATOR, OWNER, UNKNOWN, };

	@Override
	public String toString() {
		return Misc.capitalizeJustFirst(name().replaceAll("_", " "));
	}

	@Override
	public int compare(Right arg0, Right arg1) {
		return 0;
	}

}
