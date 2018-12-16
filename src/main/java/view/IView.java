
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
		 void onSaveGame(); 
		 void onOpenGame();
		 void onCustomGame(); 
		 void onNewGame(); 
		
	}
	
	//friend i just made this not public if something doesn't work :)
	void enablePlayer(int playerNum, EnableState state);
	
	void displayMessage(String message);
	
	void markTuz(int playerNum, int holeNum, MarkState state);
	
	void setBallCount(int playerNum, int holeNum, int count);
	
	void registerListener(IListener listener);
	
	
	

}
