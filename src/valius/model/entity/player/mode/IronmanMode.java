package valius.model.entity.player.mode;

import valius.model.entity.player.Player;
import valius.model.items.GroundItem;
import valius.model.items.ItemUtility;
import valius.model.minigames.pest_control.PestControlRewards.RewardButton;
import valius.util.Misc;

public class IronmanMode extends Mode {

	public IronmanMode(ModeType type) {
		super(type);
	}

	@Override
	public boolean isTradingPermitted(Player player, Player other) {
		return false;
	}

	@Override
	public boolean isStakingPermitted() {
		return false;
	}

	@Override
	public boolean isItemScavengingPermitted(Player player, GroundItem item) {
		return false;
	}

	@Override
	public boolean isPVPCombatExperienceGained() {
		return true;
	}
	
	@Override
	public boolean isDonatingPermitted() {
		return true;
	}

	@Override
	public boolean isVotingPackageClaimable(String packageName) {
		if (packageName.equals("Vote Ticket")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isShopAccessible(int shopId) {
		switch (shopId) {
		/*
		 * case 78: case 77: case 48: case 44: case 40: case 26: case 22: case 20: case 16: case 14: case 12: case 2:
		 */
		case 77:
		case 41:
		case 40:
		case 44:
		case 78:
		case 22:
		case 23:
		case 20:
		case 16:
		case 26:
		case 13:
		case 14:
		case 17:
		case 18:
		case 19:
		case 29:
		case 79:
		case 80:
		case 6:
		case 9:
		case 114:
		case 115:
		case 116:
		case 117:
		case 81:
		case 120:
		case 118:
		case 12:
		case 82:
		case 119:
		case 123:
		case 122:
		case 121:
		case 124:
		case 125:
		case 126:
		case 127:
		case 128:
		case 130:
		case 131:
		case 132:
		case 133:
		case 134:
		case 135:
		case 136:
		case 137:
		case 138:
			return true;
		}
		return false;
	}

	@Override
	public boolean isItemPurchasable(int shopId, int itemId) {
		switch (shopId) {
		case 26:
		case 14:
		case 44:
		case 40:
		case 41:
		case 17:
		case 13:
		case 29:
		case 18:
		case 19:
		case 79:
		case 80:
		case 9:
		case 23:
		case 77:
		case 115:
		case 116:
		case 117:
		case 81:
		case 118:
		case 119:
		case 123:
		case 122:
		case 121:
		case 124:
		case 125:
		case 126:
		case 127:
		case 128:
		case 130:
		case 131:
		case 132:
		case 133:
		case 134:
		case 135:
		case 136:
		case 137:
		case 138:
			return true;
			
		case 6:
			if (ItemUtility.getItemName(itemId).contains("rune")) {
				return true;
			}
			switch (itemId) {
			case 1391:
			case 4089:
			case 4091:
			case 4093:
			case 4095:
			case 4097:
				return true;
			}
			break;
			
		case 82:
			switch (itemId){
			case 10548:
			case 10551:
			case 11898:
			case 11897:
			case 11896:
			case 11899:
			case 11900:
				return true;
			}
			break;

		case 78:
			if (itemId != 6199 && itemId != 2677) {
				return true;
			}
			break;

		case 48:
			if (itemId == 10499) {
				return true;
			}
			break;

		case 22:
			if (itemId != 314 && itemId != 317 && itemId != 335 && itemId != 377) {
				return true;
			}
			break;

		case 20:
			if (itemId == 1755 || itemId == 1597 || itemId == 1592 || itemId == 1595 || itemId == 1734 || itemId == 1733 || itemId == 1775 || itemId == 1785) {
				return true;
			}
			break;

		case 16:
			if (itemId == 5341 || itemId == 5343 || itemId == 5340 || itemId == 1925) {
				return true;
			}
			break;

		case 12:
			if (itemId != 2996 && itemId != 13066 && itemId != 12751) {
				return true;
			}
			break;

		case 2:
			int[] disallowed = { 314, 1437, 1275, 1359, 1778, 221, 235, 1526, 223, 9736, 231, 225, 239, 243, 6049, 3138, 6693, 245, 6051 };
			if (Misc.linearSearch(disallowed, itemId) != -1) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isItemSellable(int shopId, int itemId) {
		switch (shopId) {
		case 26:
		case 29:
		case 18:
		case 115:
		case 116:
		case 41:
			return true;
		case 44:
			if (ItemUtility.getItemName(itemId).contains("head")) {
				return true;
			}
			break;
		}
		return false;
	}

	@Override
	public int getModifiedShopPrice(int shopId, int itemId, int price) {
		switch (shopId) {
		// case 113:
		case 81:
			if (itemId == 2368) {
				price = 5000000;
			}
			break;
		case 77:
			if (itemId == 2572) {
				price = 30;
			}
			if (itemId == 6199) {
				price = 45;
			}
			break;
		}
		return price;
	}

	@Override
	public boolean isBankingPermitted() {
		return true;
	}

	@Override
	public boolean isRewardSelectable(RewardButton reward) {
		switch (reward) {
		case FIGHTER_TORSO:
		case FIGHTER_HAT:
		case BARROWS_GLOVES:
		case VOID_KNIGHT_ROBE:
		case VOID_MAGE_HELM:
		case VOID_MELEE_HELM:
		case VOID_KNIGHT_TOP:
		case VOID_KNIGHT_GLOVES:
		case VOID_RANGE_HELM:
			return true;

		default:
			return false;
		}
	}

}