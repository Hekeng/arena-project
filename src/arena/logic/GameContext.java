package arena.logic;

public class GameContext {
	private GameState nextState = GameState.MAIN_MENU;
	
	public enum GameState {
		AI_CHOICE,
		CHOICE_CLASS,
		CLASS_PREVIEW,
		CREATE_CHARACTER,
		ENTER_NAME,
		EXIT,
		FIGHT,
		HUB,
		LOAD_MENU,
		MAIN_MENU,
		SHOW_DUELIST
	}
	
	
	private Integer selectedClassId = null;
	private boolean isAiGame = false;
	private String pendingName = null;

	public void setNextState(GameState state) { this.nextState = state; }
	public GameState getNextState() { return nextState; }
	
	public void setSelectedClassId(Integer id) { this.selectedClassId = id; }
	public int getSelectedClassId() { return selectedClassId; }

	public void setIsAiGame (boolean state) { this.isAiGame = state; }
	public boolean getIsAiGame () { return isAiGame; }

	public void setPendingName (String pendingName){this.pendingName = pendingName;}
	public String getPendingName(){return this.pendingName;}

}
