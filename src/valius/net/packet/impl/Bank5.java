package valius.net.packet.impl;

import java.util.Objects;

import valius.content.tradingpost.Listing;
import valius.model.entity.player.Player;
import valius.model.entity.player.skills.Skill;
import valius.model.entity.player.skills.Smithing;
import valius.model.entity.player.skills.crafting.JewelryMaking;
import valius.model.items.Item;
import valius.model.multiplayer_session.MultiplayerSession;
import valius.model.multiplayer_session.MultiplayerSessionFinalizeType;
import valius.model.multiplayer_session.MultiplayerSessionStage;
import valius.model.multiplayer_session.MultiplayerSessionType;
import valius.model.multiplayer_session.duel.DuelSession;
import valius.model.multiplayer_session.trade.TradeSession;
import valius.net.packet.PacketType;
import valius.world.World;

/**
 * Bank 5 Items
 **/
public class Bank5 implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		int interfaceId = c.getInStream().readSignedWordBigEndianA();
		int removeId = c.getInStream().readSignedWordBigEndianA() & 0xFFFF;
		int removeSlot = c.getInStream().readSignedWordBigEndian();
		
		if (c.debugMessage)
			c.sendMessage("Bank 5: interfaceid: "+interfaceId+", removeSlot: "+removeSlot+", removeID: " + removeId);
		
		if (c.getInterfaceEvent().isActive()) {
			c.sendMessage("Please finish what you're doing.");
			return;
		}
		if (c.getTutorial().isActive()) {
			c.getTutorial().refresh();
			return;
		}
		if (c.viewingLootBag || c.addingItemsToLootBag) {
			if (c.getLootingBag().handleClickItem(removeId, 5)) {
				return;
			}
		}
		if (c.viewingRunePouch) {
			if (c.getRunePouch().handleClickItem(removeId, 5, interfaceId)) {
				return;
			}
		}
		if(interfaceId >= -1281 && interfaceId <= -1275) {

			c.getShops().buyItem(interfaceId & 0xFFFF, removeId & 0xFFFF, removeSlot, 1);
			
			return;
		}
		switch (interfaceId) {
		
		case -17515:
			Listing.buyListing(c, removeSlot, 5);
		break;
	
		case -17036: //Listing interface
			if(c.insidePost) {
				int amount = 5;
				if(c.getItems().getItemAmount(removeId) < 5)
					amount = c.getItems().getItemAmount(removeId);
				Listing.offerSelectedItem(c, removeId, amount);
			}
		break;
		
		
		
		case 4233:
		case 4239:
		case 4245:
			JewelryMaking.mouldItem(c, removeId, 5);
			break;

		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			Smithing.readInput(c.getSkills().getLevel(Skill.SMITHING), Integer.toString(removeId), c, 5);
			break;

		case 3900:
			c.getShops().buyItem(interfaceId, removeId & 0xFFFF, removeSlot, 1);
			break;
		case -1281:
		case -1520:
			c.getShops().buyItem(interfaceId, removeId & 0xFFFF, removeSlot, 1);
			break;

		case 3823:
			c.getShops().sellItem(removeId & 0xFFFF, removeSlot, 1);
			break;

		case 5064:
			if (World.getWorld().getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
				World.getWorld().getMultiplayerSessionListener().finish(c, MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
				c.sendMessage("You cannot add items to the bank whilst trading.");
				return;
			}
			DuelSession duelSession = (DuelSession) World.getWorld().getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
			if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
				c.sendMessage("You have declined the duel.");
				duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
				duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
				return;
			}
			if (c.isBanking) {
				c.getItems().addToBank(removeId, 5, true);
			}
			if (c.inSafeBox) {
				if (!c.pkDistrict && removeId != 13307) {
					c.sendMessage("You cannot do this right now.");
					return;
				}
				c.getSafeBox().deposit(removeId, 5);
			}
			break;
		case 5382:
			if (World.getWorld().getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
				World.getWorld().getMultiplayerSessionListener().finish(c, MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
				c.sendMessage("You cannot remove items from the bank whilst trading.");
				return;
			}
			if (c.getBank().getBankSearch().isSearching()) {
				c.getBank().getBankSearch().removeItem(removeId, 5);
				return;
			}
			c.getItems().removeFromBank(removeId, 5, true);
			break;

		case 3322:
			MultiplayerSession session = World.getWorld().getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof TradeSession || session instanceof DuelSession) {
				session.addItem(c, new Item(removeId, 5));
			}
			break;

		case 3415:
			session = World.getWorld().getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof TradeSession) {
				session.removeItem(c, removeSlot, new Item(removeId, 5));
			}
			break;

		case 6669:
			session = World.getWorld().getMultiplayerSessionListener().getMultiplayerSession(c);
			if (Objects.isNull(session)) {
				return;
			}
			if (session instanceof DuelSession) {
				session.removeItem(c, removeSlot, new Item(removeId, 5));
			}
			break;

		}
	}

}
