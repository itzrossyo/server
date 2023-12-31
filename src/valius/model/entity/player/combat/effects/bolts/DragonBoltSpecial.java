package valius.model.entity.player.combat.effects.bolts;

import valius.model.entity.npc.NPC;
import valius.model.entity.player.Player;
import valius.model.entity.player.combat.Damage;
import valius.model.entity.player.combat.DamageEffect;
import valius.model.entity.player.combat.range.RangeExtras;
import valius.util.Misc;

public class DragonBoltSpecial implements DamageEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		if (defender.antifireDelay > 0 || defender.getItems().isWearingAnyItem(11283, 33115, 11284, 1540)) {
			return;
		}
		int change = Misc.random((int) (damage.getAmount() * 1.45));
		damage.setAmount(change);
		RangeExtras.createCombatGraphic(attacker, defender, 756, false);
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getNpcName() != null && defender.getDefinition().getNpcName().toLowerCase().contains("dragon")) {
			return;
		}
		int change = Misc.random((int) (damage.getAmount() * 1.45));
		damage.setAmount(change);
		RangeExtras.createCombatGraphic(attacker, defender, 756, false);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeExtras.boltSpecialAvailable(operator, 9244);
	}

}
