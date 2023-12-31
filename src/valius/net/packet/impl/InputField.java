package valius.net.packet.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import valius.content.help.HelpDatabase;
import valius.content.help.HelpRequest;
import valius.content.presets.Preset;
import valius.model.entity.player.Player;
import valius.model.entity.player.PlayerHandler;
import valius.model.entity.player.Right;
import valius.model.items.bank.BankPin;
import valius.net.packet.PacketType;
import valius.util.Misc;
import valius.world.World;

public class InputField implements PacketType {
	
	private long getKMorB(String input) {

		if (input.toLowerCase().contains("k")) {
			input = input.replaceAll("k", "000");
		} else if (input.toLowerCase().contains("m")) {
			input = input.replaceAll("m", "000000");
		} else if (input.toLowerCase().contains("b")) {
			input = input.replaceAll("b", "000000000");
		
		}

			return Long.valueOf(input);
	}

	@Override
	public void processPacket(Player player, int packetType, int packetSize) {
		int id = player.inStream.readInteger();
		String text = player.inStream.readString();
		if (player.getInterfaceEvent().isActive()) {
			player.sendMessage("Please finish what you're doing.");
			return;
		}
		if (player.getTutorial().isActive()) {
			player.getTutorial().refresh();
			return;
		}
		//System.out.println("ID: " + id);
		switch (id) {
		
		case 38011: //Wogw donation amount
			
			player.wogwDonationAmount = getKMorB(text);
			if (player.wogwOption.isEmpty()) {
				player.sendMessage("You must choose something to donate towards.");
				return;
			}
			player.getPA().sendFrame171(0, 38020);
			player.getPA().sendFrame126("Are you sure you want to contribute\\n" + Misc.getValueWithoutRepresentation(player.wogwDonationAmount) + " gp?", 38022);
			break;
		
			/**
			 * Player shop name
			 */
		case 28054:
			player.sendMessage("Setting player shop name to: " + text);
			break;
			
			/**
			 * Player shop description
			 */
		case 28055:
			player.sendMessage("Setting player shop description to: " + text);
			break;

		case 33205:
			player.getPunishmentPanel().setReason(text);
			break;

		case 33211:
			player.getPunishmentPanel().setDuration(Integer.parseInt(text));
			break;

		case 53536:
			if (text.length() > 16) {
				player.sendMessage("Custom title length can only be sixteen characters, no more.");
				return;
			}
			player.getTitles().setTemporaryCustomTitle(text);
			break;
			
		case 39806:
			World.getWorld().getDropManager().search(player, text);
			break;

		case 59527:
			if (text.length() < 25) {
				player.sendMessage("Your help request must contain 25 characters for the description.");
				return;
			}
			List<Player> staff = PlayerHandler.nonNullStream().filter(Objects::nonNull).filter(p -> p.getRights().isOrInherits(Right.HELPER)).collect(Collectors.toList());
			if (HelpDatabase.getDatabase().requestable(player)) {
				HelpDatabase.getDatabase().add(new HelpRequest(player.playerName, player.connectedFrom, text));
				if (staff.size() > 0) {
					PlayerHandler.sendMessage("@blu@[HelpDB] " + Misc.capitalize(player.playerName) + "" + " is requesting help, type ::helpdb to view their request.", staff);
					player.sendMessage("You request has been sent, please wait as a staff member gets back to you.");
				} else {
					player.sendMessage("There are no staff online to help you at this time, please be patient.");
				}
			}
			player.getPA().removeAllWindows();
			break;

		case 32002:
			Preset preset = player.getPresets().getCurrent();
			if (preset == null) {
				player.sendMessage("You must select a preset before changing the name.");
				return;
			}
			preset.setAlias(text);
			player.getPresets().refreshMenus(preset.getMenuSlot(), preset.getMenuSlot() + 1);
			break;

		case 58063:
			if (player.getPA().viewingOtherBank) {
				player.getPA().resetOtherBank();
				return;
			}
			if (player.isBanking) {
				player.getBank().getBankSearch().setText(text);
				player.getBank().setLastSearch(System.currentTimeMillis());
				if (text.length() > 2) {
					player.getBank().getBankSearch().updateItems();
					player.getBank().setCurrentBankTab(player.getBank().getBankSearch().getTab());
					player.getItems().resetBank();
					player.getBank().getBankSearch().setSearching(true);
				} else {
					if (player.getBank().getBankSearch().isSearching())
						player.getBank().getBankSearch().reset();
					player.getBank().getBankSearch().setSearching(false);
				}
			}
			break;

		case 59507:
			if (player.getBankPin().getPinState() == BankPin.PinState.CREATE_NEW)
				player.getBankPin().create(text);
			else if (player.getBankPin().getPinState() == BankPin.PinState.UNLOCK)
				player.getBankPin().unlock(text);
			else if (player.getBankPin().getPinState() == BankPin.PinState.CANCEL_PIN)
				player.getBankPin().cancel(text);
			else if (player.getBankPin().getPinState() == BankPin.PinState.CANCEL_REQUEST)
				player.getBankPin().cancel(text);
			break;

		default:
			break;
		}
	}

}
