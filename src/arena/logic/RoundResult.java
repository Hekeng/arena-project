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
	
	public RoundResult(String attackMessage, int rawAttackerDmg, int dmgToDef, String defMessage, int rawDefenderDmg, int dmgToAtk, boolean defResponded) {
		this.attackMessage = attackMessage;
		this.defMessage = defMessage;
		this.dmgToDef = dmgToDef;
		this.dmgToAtk = dmgToAtk;
		this.rawAttackerDmg = rawAttackerDmg;
		this.rawDefenderDmg = rawDefenderDmg;
		this.defResponded = defResponded;
		
	}
}
