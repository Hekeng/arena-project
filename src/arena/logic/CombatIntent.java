package arena.logic;

public class CombatIntent {
	// Влияние на здоровье
	public int damageValue = 0;
	public double defenseMod = 1.0;
	
	// Влияние на ресурсы (абстрактное)
	public int selfResourceChange = 0;   // Мой ресурс (мана/ярость)
	public int targetResourceChange = 0; // Ресурс врага
	
	// Информационное поле
	public String message = "";
}
