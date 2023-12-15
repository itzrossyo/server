package valius.model.minigames.bounty_hunter;

import valius.event.CycleEvent;
import valius.event.CycleEventContainer;

public abstract class TargetEvent implements CycleEvent {
	/**
	 * The owner of this TargetSelector object
	 */
	protected BountyHunter bountyHunter;

	/**
	 * Creates a new TargetSelector object that will be used to select targets for the owner of the BountyHunter object, the player.
	 * 
	 * @param bountyHunter the BountyHunter instance
	 */
	public TargetEvent(BountyHunter bountyHunter) {
		this.bountyHunter = bountyHunter;
	}

	@Override
	public abstract void execute(CycleEventContainer container);

}
