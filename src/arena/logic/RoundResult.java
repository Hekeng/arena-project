package arena.logic;

public class RoundResult {
	public final String attackMessage;
	public final String defMessage;
	public final int rawAttackerDmg;
	public final int rawDefenderDmg;
	public final int dmgToDef;
	public final int dmgToAtk;
	public final boolean defResponded;
	public final int atkResourceChange;
	public final int defResourceChange;
	public final int atkPoisonDmg;
	public final int defPoisonDmg;
	public final int atkTargetResChange;
	public final int defTargetResChange;
	public RoundResult(String attackMessage, int rawAttackerDmg, int dmgToDef, int atkResourceChange, int atkPoisonDmg,int atkTargetResChange,
	                   String defMessage, int rawDefenderDmg, int dmgToAtk, int defResourceChange, int defPoisonDmg, int defTargetResChange,
	                   boolean defResponded) {
		this.attackMessage = attackMessage;
		this.defMessage = defMessage;
		this.dmgToDef = dmgToDef;
		this.dmgToAtk = dmgToAtk;
		this.rawAttackerDmg = rawAttackerDmg;
		this.rawDefenderDmg = rawDefenderDmg;
		this.atkResourceChange = atkResourceChange;
		this.defResourceChange =  defResourceChange;
		this.defResponded = defResponded;
		this.atkPoisonDmg = atkPoisonDmg;
		this.defPoisonDmg = defPoisonDmg;
		this.atkTargetResChange= atkTargetResChange;
		this.defTargetResChange = defTargetResChange;
	}
}
