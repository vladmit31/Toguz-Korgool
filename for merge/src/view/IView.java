
package view;
import java.util.EventListener;

import types.Types.*;


public interface IView
{
	interface IListener extends EventListener
	{
		 void onPlay(int holeNum, int playerNum); 
		 void markTuz(int holeNum, int playerNum); 
		
		//Not sure what parameter
		 void saveGame(); 
		 void openGame();
		 void specialSetup(); 
		 void newGame(); 
		
	}
	
	
	public void enablePlayer(int playerNum, EnableState state);
	
	public void displayMessage(String message);
	
	public void markTuz(int playerNum, int holeNum, MarkState state);
	
	public void setKorgulCount(int playerNum, int holeNum, int count);
	
	void registerListener(IListener listener);
	
	
	

}
