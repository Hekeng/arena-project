package arena.logic;

public class RoundResult {
	// Делаем поля public и final, чтобы их можно было легко прочитать,
	// но нельзя было изменить после создания "посылки"
	public final String attackMessage;
	public final String defMessage;
	public final int rawAttackerDmg;
	public final int rawDefenderDmg;
	public final int dmgToDef;
	public final int dmgToAtk;
	public final boolean defResponded;

	public final int atkResourceChange;

	public final int defResourceChange;
	
	public RoundResult(String attackMessage, int rawAttackerDmg, int dmgToDef, int atkResourceChange, String defMessage, int rawDefenderDmg, int dmgToAtk, int defResourceChange, boolean defResponded) {
		this.attackMessage = attackMessage;
		this.defMessage = defMessage;
		this.dmgToDef = dmgToDef;
		this.dmgToAtk = dmgToAtk;
		this.rawAttackerDmg = rawAttackerDmg;
		this.rawDefenderDmg = rawDefenderDmg;
		this.atkResourceChange = atkResourceChange;
		this.defResourceChange =  defResourceChange;
		this.defResponded = defResponded;
		
	}
}
