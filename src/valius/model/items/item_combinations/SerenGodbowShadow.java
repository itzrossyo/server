package valius.model.items.item_combinations;

import java.util.List;
import java.util.Optional;

import valius.model.entity.player.Player;
import valius.model.items.Item;
import valius.model.items.ItemCombination;

public class SerenGodbowShadow extends ItemCombination {

	public SerenGodbowShadow(Item outcome, Optional<List<Item>> revertedItems, Item[] items) {
		super(outcome, revertedItems, items);
	}

	@Override
	public void combine(Player player) {
		super.items.forEach(item -> player.getItems().deleteItem2(item.getId(), item.getAmount()));
		player.getItems().addItem(super.outcome.getId(), super.outcome.getAmount());
		player.getDH().sendItemStatement("You combined the items and created the Seren godbow (Shadow).", 33914);
		player.setCurrentCombination(Optional.empty());
		player.nextChat = -1;
	}

	@Override
	public void showDialogue(Player player) {
		player.getDH().sendStatement("The Seren godbow (Shadow) is tradeable.", "You can dismantle this item for both items back.", "You will also no longer need to Charge this weapon.");
	}

}