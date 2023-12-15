package valius.model.entity.player.combat.effects.bolts;

import valius.model.entity.npc.NPC;
import valius.model.entity.player.Player;
import valius.model.entity.player.combat.Damage;
import valius.model.entity.player.combat.DamageEffect;
import valius.model.entity.player.combat.range.RangeExtras;
import valius.util.Misc;

public class DiamondBoltSpecial implements DamageEffect {

	@Override
	public void execute(Player attacker, Player defender, Damage damage) {
		int change = Misc.random((int) (damage.getAmount() * 1.15));
		damage.setAmount(change);
		RangeExtras.createCombatGraphic(attacker, defender, 758, false);
		defender.ignoreDefence = true;
	}

	@Override
	public void execute(Player attacker, NPC defender, Damage damage) {
		if (defender.getDefinition().getNpcName() == null) {
			return;
		}
		int change = Misc.random((int) (damage.getAmount() * 1.15));
		damage.setAmount(change);
		RangeExtras.createCombatGraphic(attacker, defender, 758, false);
	}

	@Override
	public boolean isExecutable(Player operator) {
		return RangeExtras.boltSpecialAvailable(operator, 9243);
	}

}
