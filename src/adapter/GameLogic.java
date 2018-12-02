package adapter;

import view.IView;
import model.IModel;

public class GameLogic
{
	private final IView m_view;
	private final IModel m_model;
	
	
	public GameLogic(IView view, IModel model)
	{
		m_view = view;
		m_model = model;
		
	}
	
	
	public void onMove(int playerNum, int holeNum)
	{
		System.out.println("Player: " + playerNum + " Hole Number: " + holeNum);
	}
	
}
