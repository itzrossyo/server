package valius.net.packet.impl.npcoptions;

import java.util.Objects;

import valius.Config;
import valius.content.PotionMixing;
import valius.content.achievement_diary.desert.DesertDiaryEntry;
import valius.content.achievement_diary.kandarin.KandarinDiaryEntry;
import valius.content.achievement_diary.varrock.VarrockDiaryEntry;
import valius.model.Location;
import valius.model.entity.npc.NPC;
import valius.model.entity.npc.NPCHandler;
import valius.model.entity.npc.pets.PetHandler;
import valius.model.entity.npc.pets.Probita;
import valius.model.entity.player.Player;
import valius.model.entity.player.dialogue.CrystalWeaponElf;
import valius.model.entity.player.dialogue.Santa;
import valius.model.entity.player.dialogue.Scoop;
import valius.model.entity.player.dialogue.SirTiffy;
import valius.model.entity.player.dialogue.StarSprite;
import valius.model.entity.player.dialogue.StarterDungeonGuide;
import valius.model.entity.player.dialogue.TownCrier;
import valius.model.entity.player.dialogue.Watson;
import valius.model.entity.player.dialogue.ZulrahDismantler;
import valius.model.entity.player.skills.Skill;
import valius.model.entity.player.skills.agility.AgilityHandler;
import valius.model.entity.player.skills.crafting.Tanning;
import valius.model.entity.player.skills.hunter.impling.Impling;
import valius.model.entity.player.skills.mining.Mineral;
import valius.model.entity.player.skills.thieving.Thieving.Pickpocket;
import valius.model.holiday.halloween.HalloweenRandomOrder;
import valius.world.World;

/*
 * @author Matt NIGGER
 * Handles all first options on non playable characters.
 */
public class NpcOptionOne {

	public static void handleOption(Player player, NPC npc) {
		if (World.getWorld().getMultiplayerSessionListener().inAnySession(player)) {
			return;
		}
		
		player.clickNpcType = 0;
		player.rememberNpcIndex = player.npcClickIndex;
		player.npcClickIndex = 0;
		if (PetHandler.isPet(npc.npcType)) {
			if (Objects.equals(PetHandler.getOptionForNpcId(npc.npcType), "first")) {
				if (PetHandler.pickupPet(player, npc.npcType, true))
					return;
			}
		}

		player.getQuestManager().onNpcClick(1, npc);
		if (World.getWorld().getHolidayController().clickNpc(player, 1, npc.npcType)) {
			return;
		}
		switch (npc.npcType) {
		
			case 3821:
				StarSprite.startDialogue(player, 3821);
				break;
				
			case 3396:
				StarterDungeonGuide.startDialogue(player, 3396);
				break;
				
			case 8045:
				SirTiffy.startDialogue(player, 8045);
				break;
				
			case 279:
				TownCrier.startDialogue(player, 279);
				break;
				
			case 9170:
				CrystalWeaponElf.startDialogue(player, 9170);
				break;
				
			case 6020:
				Scoop.startDialogue(player, 6020);
				break;

			case 1603:
				player.getDH().sendDialogues(150, npc.npcType);
				break;
			case 3924:
				player.getShops().openShop(23);
				break;
				
			case 3413:
				Santa.startDialogue(player, 3413);
				break;
				
			case 7204:
				player.getDH().sendDialogues(357, npc.npcType);
				break;
				
			case 3819:
				player.getDH().sendDialogues(359, npc.npcType);
				break;
				
			case 7690:
				player.createInfernoInstance();
				player.getInfernoMinigame().create(1);
				player.getInfernoMinigame().getPlayer();
				break;
			case 4407:
				player.getShops().openShop(124);
				break;
				
			case 8369: //Verzik vitur starting boss
				//player.getDH().sendDialogues(23682763, npc.npcType);
				npc.requestTransform(8370);
				break;
				
			case 3818:
				player.getDH().sendDialogues(354, 3585);
				break;
	
			case 5314:
				player.getPA().showInterface(63000);
				// player.getTeleport().loadMonsterTab();
				break;
				
			case 6781:
				player.getShops().openShop(123);
				player.sendMessage("You have @blu@" + player.skillPoints + "</col> Skill Points!");
				break;
				
			case 2662://pvm point shop
				player.getShops().openShop(125);
				player.sendMessage("You have @blu@" + player.pvmPoints + "</col> PVM Points!");
				break;
				
			case 8122://boss point shop
				player.getShops().openShop(126);
				player.sendMessage("You have @blu@" + player.bossPoints + "</col> Boss Points!");
				break;
	
			case 1143:
				player.getShops().openShop(83);
				break;
	
			case 1909:
				player.getDH().sendDialogues(900, 1909);
				break;
			case 2989:
				player.getDH().sendDialogues(1427, 2989);
				break;
			case 3306:
				player.getDH().sendDialogues(1577, -1);
				break;
			case 7520:
				player.getDH().sendDialogues(850, 7520);
				break;
			/**
			 * Doomsayer
			 */
			case 6773:
				if (!player.pkDistrict) {
					player.sendMessage("You cannot do this right now.");
					return;
				}
				player.getDH().sendDialogues(800, 6773);
				break;
			// Zeah Throw Aways
			case 2200:
				player.getDH().sendDialogues(55873, 2200);
				break;
			case 3189:
				player.getDH().sendDialogues(11929, 3189);
				break;
			case 5998:
				if (player.amDonated <= 1) {
					player.getDH().sendDialogues(5998, 5998);
				} else {
					player.getDH().sendDialogues(5999, 5998);
				}
				break;
			case 4062:
				player.getDH().sendDialogues(55875, 4062);
				break;
			case 4321:
				player.getDH().sendDialogues(145, 4321);
				break;
			case 3461:
				player.getShops().openShop(119);
				break;
			case 7041:
				player.getDH().sendDialogues(500, 7041);
				break;
			case 6877:
				player.getDH().sendDialogues(55877, 6877);
				break;
			case 4409:
				player.getDH().sendDialogues(55876, 4409);
				break;
			case 6982:
				player.getDH().sendDialogues(55868, 6982);
				break;
			case 6947:
			case 6948:
			case 6949:
				player.getDH().sendDialogues(55872, 6947);
				break;
			case 6998:
				player.getDH().sendDialogues(55871, 6998);
				break;
			case 7001:
				player.getDH().sendDialogues(55869, 7001);
				break;
			case 6999:
				player.getDH().sendDialogues(55870, 6999);
				break;
			case 6904:
				player.getDH().sendDialogues(55864, 6904);
				break;
			case 6906:
				player.getDH().sendDialogues(55865, 6904);
				break;
			case 6908:
				player.getDH().sendDialogues(55866, 6904);
				break;
			case 6910:
				player.getDH().sendDialogues(55867, 6904);
				break;
			// End Zeah Throw Aways
			case 1503:
				player.getDH().sendDialogues(88393, 1503);
				break;
			case 1504:
				player.getDH().sendDialogues(88394, 1504);
				break;
	
			case 6774:
				player.getDH().sendDialogues(800, 6773);
				break;
	
			case 7440:
				HalloweenRandomOrder.checkOrder(player);
				break;
			case 822:
				if (player.getDiaryManager().getWildernessDiary().hasDoneEasy()) {
					player.getDH().sendDialogues(702, 822);
				} else {
					if (player.getItems().playerHasItem(11286) && player.getItems().playerHasItem(1540)
							&& player.getItems().playerHasItem(995, 5_000_000)) {
						player.getItems().deleteItem(11286, 1);
						player.getItems().deleteItem(1540, 1);
						player.getItems().deleteItem(995, 500_000);
						player.getItems().addItem(11283, 1);
						player.votePoints -= 5;
						player.refreshQuestTab(2);
						player.getDH().sendItemStatement("Oziach successfully bound your dragonfire shield.", 11283);
					} else {
						player.getDH().sendNpcChat("Come back with a shield, visage and 5M Gold!");
					}
				}
				break;
			case 306:
				player.getDH().sendDialogues(710, 306);
				break;
	
			case 7303:
				Watson.startDialogue(player, 7303);
				break;
	
			case 2914:
				player.getDH().sendNpcChat2("Use a Zamorakian Spear on me to turn", "it into a Hasta! Or Vice Versa.", 2914,
						"Otto Godblessed");
				break;
	
			case 1635:
			case 1636:
			case 1637:
			case 1638:
			case 1639:
			case 1640:
			case 1641:
			case 1642:
			case 1643:
			case 1654:
			case 7302:
			case 3384:
				Impling.catchImpling(player, npc.npcType, player.rememberNpcIndex);
				break;
	
			case 17: // Rug merchant - Pollnivneach
				if (!player.getDiaryManager().getDesertDiary().hasCompleted("MEDIUM")) {
					player.getDH().sendNpcChat1(
							"You must have completed all medium diaries here in the desert \\n to use this location.", 17,
							"Rug Merchant");
					return;
				}
				player.getDiaryManager().getDesertDiary().progress(DesertDiaryEntry.TRAVEL_POLLNIVNEACH);
				player.startAnimation(2262);
				AgilityHandler.delayFade(player, "NONE", 3351, 3003, 0, "You step on the carpet and take off...",
						"at last you end up in pollnivneach.", 3);
				break;
			case 276:
				if (Config.BONUS_XP_WOGW) {
					player.getDH().sendNpcChat1(
							"Well of Goodwill is currently @red@active@bla@! \\n It is granting 1 hour of @red@Double XP@bla@!",
							276, "Valius Crier");
				} else if (Config.BONUS_PC_WOGW) {
					player.getDH().sendNpcChat1(
							"Well of Goodwill is currently @red@active@bla@! \\n It is granting 1 hour of @red@Double Pc Points@bla@!",
							276, "Valius Crier");
				} else if (Config.DOUBLE_DROPS) {
					player.getDH().sendNpcChat1(
							"Well of Goodwill is currently @red@active@bla@! \\n It is granting 1 hour of @red@Double Drops@bla@!",
							276, "Valius Crier");
				} else {
					player.getDH().sendNpcChat1("Well of Goodwill is currently @red@inactive@bla@!", 276, "Valius Crier");
				}
				break;
			case 5520:
				player.getDiaryManager().getDesertDiary().claimReward();
				break;
			case 5519:
				player.getDiaryManager().getArdougneDiary().claimReward();
				break;
			case 5790:
				player.getDiaryManager().getKaramjaDiary().claimReward();
				break;
			case 5525:
				player.getDiaryManager().getVarrockDiary().claimReward();
				break;
			case 5523:
				player.getDiaryManager().getLumbridgeDraynorDiary().claimReward();
				break;
			case 5524:
				player.getDiaryManager().getFaladorDiary().claimReward();
				break;
			case 5521:
				player.getDiaryManager().getMorytaniaDiary().claimReward();
				break;
			case 5514:
				player.getDiaryManager().getWildernessDiary().claimReward();
				break;
			case 5517:
				player.getDiaryManager().getKandarinDiary().claimReward();
				break;
			case 5526:
				player.getDiaryManager().getFremennikDiary().claimReward();
				break;
			case 5518:
				player.getDiaryManager().getWesternDiary().claimReward();
				break;
	
			case 3936:
				player.getDH().sendNpcChat1("Right click on me and i will take you on-board.", 3936, "Sailor");
				break;
	
			case 1896:
				if (player.getItems().playerHasItem(995, 1000000)) {
					player.getItems().deleteItem(995, 1000000);
					player.getItems().addItem(36, 1);
					player.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.BUY_CANDLE);
				} else {
					player.sendMessage("You need 1,000,000 coins to purchase a candle.");
					return;
				}
				break;
	
			case 6586:
				player.getDH().sendNpcChat1("No shirt, Sherlock", 6586, "Sherlock");
				player.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.SHERLOCK);
				break;
	
			case 5036:
				if (player.getItems().playerHasItem(225) || player.getItems().playerHasItem(223)) {
					player.sendMessage("The Apothecary takes your ingredients and creates a strength potion.");
					player.getItems().deleteItem(225, 1);
					player.getItems().deleteItem(223, 1);
					player.getItems().addItem(115, 1);
					player.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.APOTHECARY_STRENGTH);
				} else {
					player.sendMessage("You must have limpwurt root and red spiders' eggs to do this.");
					return;
				}
				break;
	
			case 5906:
				Probita.hasInvalidPet(player);
				break;
	
			case 3500:
				player.getDH().sendDialogues(64, npc.npcType);
				break;
	
			case 5870:
				if (player.getCerberusLostItems().size() > 0) {
					player.getDH().sendDialogues(640, 5870);
					return;
				}
				player.getDH().sendDialogues(105, npc.npcType);
				break;
	
			case 7283:
				if (player.getSkotizoLostItems().size() > 0) {
					player.getDH().sendDialogues(640, 7283);
					return;
				}
				player.getDH().sendDialogues(105, npc.npcType);
				break;
	
			case 6481: // Max
				if (!player.maxRequirements(player)) {
					player.sendMessage("Max does not even bother speaking to you.");
					return;
				}
				player.getDH().sendDialogues(85, npc.npcType);
				break;
			case 3307: // Combat instructor
				player.getDH().sendDialogues(1390, npc.npcType);
				break;
	
			case 5513: // Elite void knight
				player.getDH().sendDialogues(79, npc.npcType);
				break;
	
			case 5527: // Achievement cape
				player.getAchievements().claimCape();
				break;
			case 1027:
				player.getDH().sendDialogues(669, npc.npcType);
				break;
			case 311:
				player.getDH().sendDialogues(650, npc.npcType);
				break;
	
			// Noting Npc At Skill Area
			case 905:
				player.talkingNpc = 905;
				player.getDH().sendNpcChat("Hello there, I can note your resources.",
						"I charge @red@25%@bla@ of the yield, this @red@does not apply to donators@bla@.",
						"Use any resource item obtained in this area on me.");
				player.nextChat = -1;
				break;
				//zulrah npcs
			case 2039:
				ZulrahDismantler.startDialogue(player, 2039);
				break;
			case 2040:
				player.getDH().sendDialogues(637, npc.npcType);
				break;
				
			case 2184:
				player.getShops().openShop(29);
				break;
	
			case 6866:
				player.getShops().openShop(82);
				player.sendMessage("You currently have @red@" + player.getShayPoints() + " @bla@Assault Points!");
				break;
	
			case 6601:
				NPC golem = NPCHandler.npcs[player.rememberNpcIndex];
				if (golem != null) {
					player.getMining().mine(golem, Mineral.RUNE,
							new Location(golem.getX(), golem.getY(), golem.getHeight()));
				}
				break;
			case 1850:
				player.getShops().openShop(112);
				break;
			case 2580:
				player.getDH().sendDialogues(629, npc.npcType);
				break;
			case 3257://master farmer pickpocketing
				player.getThieving().steal(Pickpocket.FARMER, NPCHandler.npcs[player.rememberNpcIndex]);
				break;
			case 3894:
				player.getDH().sendDialogues(628, npc.npcType);
				break;
			case 3220:
				player.getShops().openShop(25);
				break;
			case 637:
				player.getShops().openShop(6);
				break;
				case 6875:
					player.specRestore = 120;
					player.specAmount = 10.0;
					player.setRunEnergy(100);
					player.getItems().addSpecialBar(player.playerEquipment[player.playerWeapon]);
					player.getSkills().resetToActualLevel(Skill.PRAYER);
					player.getHealth().removeAllStatuses();
					player.getHealth().reset();
					player.getPA().refreshSkill(5);
					player.getDH().sendItemStatement("Restored your HP, Prayer, Run Energy, and Spec", 4049);
					player.nextChat =  -1;
					break;
				case 732:
				player.getShops().openShop(16);
				break;
			case 3219:
				player.getShops().openShop(113);
				break;
			case 2949:
				player.getPestControlRewards().showInterface();
				break;
			case 2461:
				player.getWarriorsGuild().handleDoor();
				break;
			case 7663:
				player.getDH().sendDialogues(3299, npc.npcType);
				break;
			case 402:// slayer
				if(player.combatLevel<20){
					player.getDH().sendNpcChat2("Do not waste my time peasent.","You need a Combat level of 20.",402,"Mazchna");
					return;
				}
				player.getDH().sendDialogues(3300, npc.npcType);
				break;
			case 401:
				player.getDH().sendDialogues(3300, npc.npcType);
				break;
			case 405:
				if(player.combatLevel<100){
					player.getDH().sendNpcChat2("Do not waste my time peasent.","You need a Combat level of at least 100.",402,"Duradel");
					return;
				}
				if (player.getSkills().getLevel(Skill.SLAYER) < 50) {
					player.getDH().sendNpcChat1("You must have a slayer level of at least 50 weakling.", 490, "Duradel");
					return;
				}
				player.getDH().sendDialogues(3300, npc.npcType);
				break;
			case 6797: // Nieve
				if (player.getSkills().getLevel(Skill.SLAYER) < 90) {
					player.getDH().sendNpcChat1("You must have a slayer level of at least 90 weakling.", 490, "Nieve");
					return;
				} else {
					player.getDH().sendDialogues(3300, npc.npcType);
				}
				break;
			case 315:
				player.getDH().sendDialogues(550, npc.npcType);
				break;
	
			case 1308:
				player.getDH().sendDialogues(538, npc.npcType);
				break;
			case 6771:
				player.getShops().openShop(78);
				player.sendMessage("You currently have " + player.getAchievements().getPoints() + " Achievement points.");
				break;
			case 7456:
				player.getDH().sendDialogues(619, npc.npcType);
				break;
			case 4306:
				player.getShops().openShop(17);
				break;
			case 3341:
				player.getDH().sendDialogues(2603, npc.npcType);
				break;
			case 5919:
				player.getDH().sendDialogues(14400, npc.npcType);
				break;
			case 6599:
				player.getShops().openShop(12);
				break;
			case 2578:
				player.getDH().sendDialogues(2401, npc.npcType);
				break;
			case 3789:
				player.getShops().openShop(75);
				break;
			// FISHING
				/*FISHING
				*    startFishing(fishing spot id,fishing tool item id);
				*    
				*/
			case 3913://small net fishing
				player.getFishing().startFishing(npc.npcType,303);
				break;
			case 3417://fly fishing
				player.getFishing().startFishing(npc.npcType,309);
				break;
			case 3657://lobster cage
				player.getFishing().startFishing(npc.npcType,301);
				break;
			case 1520://monkfish
				player.getFishing().startFishing(npc.npcType,305);
				break;
			case 6825://angler
				player.getFishing().startFishing(npc.npcType,307);
				break;
			case 635://dark crab
				player.getFishing().startFishing(npc.npcType,301);
				break;
			case 4712://karambwan
				player.getFishing().startFishing(npc.npcType,3157);
				break;
			case 944:
				player.getDH().sendOption5("Hill Giants", "Hellhounds", "Lesser Demons", "Chaos Dwarf", "-- Next Page --");
				player.teleAction = 7;
				break;
	
			case 559:
				player.getShops().openShop(16);
				break;
			case 5809:
				Tanning.sendTanningInterface(player);
				break;
	
			case 2913:
				player.getShops().openShop(22);
				break;
			case 403:
				player.getDH().sendDialogues(2300, npc.npcType);
				break;
			case 1599:
				break;
	
			case 953: // Banker
			case 2574: // Banker
			case 166: // Gnome Banker
			case 1702: // Ghost Banker
			case 494: // Banker
			case 495: // Banker
			case 496: // Banker
			case 497: // Banker
			case 498: // Banker
			case 499: // Banker
			case 567: // Banker
			case 766: // Banker
			case 1036: // Banker
			case 1360: // Banker
			case 2163: // Banker
			case 2164: // Banker
			case 2354: // Banker
			case 2355: // Banker
			case 2568: // Banker
			case 2569: // Banker
			case 2570: // Banker
				player.getPA().openUpBank();
				break;
			case 1986:
				player.getDH().sendDialogues(2244, player.npcType);
				break;
	
			case 5792:
				player.getDH().sendDialogues(4005, player.npcType);
				// player.getShops().openShop(9);
				break;
			case 6747:
				player.getShops().openShop(77);
				break;
			case 3218:// magic supplies
				player.getShops().openShop(6);
				break;
			case 4423:
				player.getShops().openShop(122);
				player.sendMessage("@red@ You must fill the books with pages found in clue scrolls or purchased from players.");
				break;
			case 8278:// range supplies
				player.getShops().openShop(4);
				break;
			case 8280://master capes
				player.getShops().openShop(121);
				break;
			case 1785:
				player.getShops().openShop(8);
				break;
	
			case 1860:
				player.getShops().openShop(47);
				break;
	
			case 519:
				player.getShops().openShop(48);
				break;
	
			case 548:
				player.getDH().sendDialogues(69, player.npcType);
				break;
	
			case 2258:
				player.getDH().sendOption2("Teleport me to Runecrafting Abyss.", "I want to stay here, thanks.");
				player.dialogueAction = 2258;
				break;
	
			case 532:
				player.getShops().openShop(47);
				break;
	
			case 3216:// melee supplies
				player.getShops().openShop(8);
				break;
	
			case 2814:
				if (player.getMode().isIronman() || player.getMode().isUltimateIronman() || player.getMode().isHcIronman() || player.getMode().isGroupIronman()) {
					player.getShops().openShop(41);
				} else {
					player.sendMessage("You must be an Iron Man to access this shop.");
				}
				break;
			case 2813:
				player.getShops().openShop(2);
				break;
	
			case 5449:
				PotionMixing.decantInventory(player);
				player.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.POTION_DECANT);
				break;
	
			/*
			 * case 198: c.getShops().openSkillCape(); break;
			 */
	
			/**
			 * Make over mage.
			 */
		}
	}
	
}