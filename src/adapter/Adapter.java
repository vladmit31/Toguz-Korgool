package adapter;
import view.IView;
import model.IModel;



//If someone wants to talk with IViewClient, they need to register to it
public class Adapter implements IView.IListener
{
	private final IView m_view;
	private final IModel m_model;
	
	private final GameLogic m_gameLogic;
	private final ToolbarLogic m_toolbarLogic;
	
	//We are only talking with the interfaces not the objects themselves
	//Dependency-injection of interfaces (we dont care what class as long as
	//we can call their methods)
	
	public Adapter(IView view, IModel model)
	{
		m_view = view;
		m_model = model;
		
		
		//event linking 
		m_view.registerListener(this);
		
		m_gameLogic = new GameLogic(view, model);
		m_toolbarLogic = new ToolbarLogic(view, model);
	}
	

	
	//The business logic delegation
	@Override
	public void onPlay(int holeNum, int playerNum)
	{
		m_gameLogic.onMove(playerNum, holeNum);
		
	}

	@Override
	public void markTuz(int holeNum, int playerNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		
		
	}

	@Override
	public void openGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void specialSetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		
	}
	
	

}
