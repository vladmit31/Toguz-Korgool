
package view;
import types.Types.*;


public interface IView
{
	public void enablePlayer(int playerNum, EnableState state);
	
	public void displayMessage(String message);
	
	public void markTuz(int playerNum, int holeNum, MarkState state);
	
	public void setKorgulCount(int playerNum, int holeNum, int count);
	
	//Reduced observer pattern that is type-safe  (doesn't use Object as a type but a concrete ViewClient)
	public void registerClient(IViewClient client);
	

}
