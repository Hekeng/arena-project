package arena.logic;

public class GameContext {
	private String nextState = "MAIN_MENU";
	private int selectedClassId = -1;
	private boolean isAiGame = false;
	private String pendingName = "";

	public void setNextState(String state) { this.nextState = state; }
	public String getNextState() { return nextState; }
	public void setSelectedClassId(int id) { this.selectedClassId = id; }
	public int getSelectedClassId() { return selectedClassId; }

	public void setIsAiGame (boolean state) { this.isAiGame = state; }
	public boolean getIsAiGame () { return isAiGame; }

	public void setPendingName (String pendingName){this.pendingName = pendingName;}
	public String getPendingName(){return this.pendingName;}

}
