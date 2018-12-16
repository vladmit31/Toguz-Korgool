
package main.java.view;
import java.util.EventListener;

import main.java.types.Types.*;


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
		 void onApply(); 
		 void onCancel();
		
	}
	
	//friend i just made this not public if something doesn't work :)
	void enablePlayer(int playerNum, int holeIndex, EnableState state);
	
	void displayMessage(String message);
	
	void markTuz(int playerNum, int holeNum, MarkState state);
	
	void setBallCount(int playerNum, int holeNum, int count);
	
	void registerListener(IListener listener);
	
	void setScore(int playerNum, int score);
	
	void displayWinner(int playerNum, boolean isVisible);
	
	void displayHoleInput(EnableState state);
	
	void showCustomGameButtons(EnableState state);
	
	int[] getEditHoleCounts(int playerNum);

	String getSavePath();

	String getOpenPath();
	
	
	

}
