/**
 * 
 */
package valius.model.entity.npc.combat.impl.tob;

import valius.model.entity.Entity;
import valius.model.entity.npc.NPC;
import valius.model.entity.npc.combat.CombatScript;
import valius.model.entity.npc.combat.ScriptSettings;

/**
 * @author ReverendDread
 * Apr 22, 2019
 */
@ScriptSettings(
	npcIds = { 8367 }
)
public class BloodSpawn extends CombatScript {

	@Override
	public void init(NPC npc) {
		npc.setNeverWalkHome(true);
		npc.setNoRespawn(true);
		npc.setRandomWalk(true);
		setCanAttack(false);
	}
	
	/* (non-Javadoc)
	 * @see ethos.model.entity.npc.combat.CombatScript#attack(ethos.model.entity.npc.NPC, ethos.model.entity.Entity)
	 */
	@Override
	public int attack(NPC npc, Entity target) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see ethos.model.entity.npc.combat.CombatScript#getAttackDistance(ethos.model.entity.npc.NPC)
	 */
	@Override
	public int getAttackDistance(NPC npc) {
		return 0;
	}

}
