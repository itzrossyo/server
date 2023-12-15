package valius.model.holiday.christmas;

import java.util.Optional;

import valius.event.CycleEvent;
import valius.event.CycleEventContainer;
import valius.model.entity.player.Player;
import valius.world.World;

public class ChristmasToyUpgrade implements CycleEvent {
	private Player player;
	private int item;
	private int npcId;

	public ChristmasToyUpgrade(Player player, int item, int npcId) {
		this.player = player;
		this.item = item;
		this.npcId = npcId;
	}

	@Override
	public void execute(CycleEventContainer container) {
		if (container.getOwner() == null || player == null) {
			container.stop();
			return;
		}
		if (!player.getItems().playerHasItem(item)) {
			container.stop();
			player.getDH().sendStatement("Arrgg what happened to the toy!!", "I better go talk to santa.");
			player.nextChat = -1;
			return;
		}
		Optional<ChristmasToy> toy = ChristmasToy.forItem(item);
		if (!toy.isPresent()) {
			container.stop();
			return;
		}
		Optional<Integer> newToy = toy.get().getNextItem(item);
		if (!newToy.isPresent()) {
			container.stop();
			return;
		}
		player.getItems().deleteItem(item, 1);
		if (!player.getItems().addItem(newToy.get(), 1)) {
			player.sendMessage("The toy has been put on the ground.");
			World.getWorld().getItemHandler().createGroundItem(player, newToy.get(), player.getX(), player.getY(), player.getHeight(), 1);
		}
		if (npcId == 4895) {
			player.getDH().sendDialogues(606, 4895);
		} else if (npcId == 4893) {
			player.getDH().sendDialogues(607, 4893);
		}
		container.stop();
	}

}
