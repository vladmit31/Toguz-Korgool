package view;

//View uses this interface to convey actions to whoever is interested
//The testing mock will implement this interface
public interface IViewClient
{
	public enum AppEvent
	{
		NEW_GAME, 
		QUIT, 
		SAVE,
		OPEN, 
		SETUP_GAME
	}
	
	
	public void onMove(int playerNum, int holeNum);
	
	public void onAppEvent(AppEvent appEvent);

}
