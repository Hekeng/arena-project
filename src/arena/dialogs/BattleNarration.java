package arena.dialogs;

import java.util.ArrayList;
import java.util.List;

import arena.helpers.UnRand;

import arena.characters.Character;
public class BattleNarration {
	
	private BattleNarration(){};
	
	public static final String [] FIRST_SCENA_DIALOGE = {
		"=============== ARENA EVENT ================",
		"================== CROWD ===================",
		"The arena falls silent...",
		"Two fighters look at each other.",
		"The referee raises his hand — the crowd holds",
		" its breath. Time to roll the dice."
	};

	private static final String[] ARENA_PHRASES = {
		"The crowd is chanting: 'Blood! Blood! Blood!'",
		"A merchant in the front row is betting all his gold on the next strike!",
		"The smell of sand and sweat fills the air. The audience holds its breath...",
		"The Emperor leans forward, intrigued by this display of skill!",
		"Somewhere in the stands, a child points at the heroes, dreaming of glory."
	};

	public static String [] getRandomArenaEvent(Character atk, Character def) {
		int type = UnRand.randomNumber(1, 3); // Выбираем тип события

		List<String> arenaContext = new ArrayList<>();

		arenaContext.add("=============== ARENA EVENT ================");


		String eventPhrase = switch (type) {
			case 1 -> ARENA_PHRASES[UnRand.randomNumber(0, ARENA_PHRASES.length - 1)];
			case 2 -> "The crowd roars: 'Go, " + atk.getName() + "! Crush them!'";
			case 3 -> "Boos and whistles erupt as " + def.getName() + " stumbles!";
			default -> "The drums beat faster, anticipating blood...";
		};

		arenaContext.add(eventPhrase);



		return arenaContext.toArray(new String[0]);
	}

	public static final String[] BATTLE_FINALE = {
			"=============== ARENA EVENT ================",
			"============= VICTORY ACHIEVED =============",
			"[... The dust settles over the arena sand ...]",
			"The roar of the crowd fades into a tensed silence.",
			"The referee approaches the fallen fighter and shakes his head..."
	};
	// Тексты для панихиды (Loser)
	public static final String[] DEATH_TRIBUTE = {
			"=============== ARENA EVENT ================",
			"============== REST IN PEACE ===============",
			"The arena sand drinks the fallen warrior's blood...",
			"Medics rush to the scene, carrying the defeated away.",
			"Silence falls as the crowd acknowledges the loss."
	};

	// Тексты для поздравления (Winner)
	public static final String[] VICTORY_SHOUTS = {
			"=============== ARENA EVENT ================",
			"============ VICTORY ACHIEVED ==============",
			"The crowd erupts in a deafening roar!",
			"The champion stands tall, bathed in golden light.",
			"The legendary hero has claimed another soul for the Arena!"
	};

	// Тексты для лечения
	public static final String[] RECOVERY_LOG = {
			"=============== ARENA EVENT ================",
			"============ RECOVERY AND REST =============",
			"Priests of the Hexen Order bless the survivor.",
			"Wounds are being tended, energy is being restored."
	};
//	Rolling the dice...
//			.
//			..
//			...


//	The Arena Judge raises his hand...
//	The dice is thrown!
//
//	Mage: 4
//	Warrior: 2
//
//	The crowd roars!
//	Mage takes the first move!



}
