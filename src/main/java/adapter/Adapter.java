package main.java.adapter;
import main.java.model.IModel;
import main.java.types.Types.EnableState;
import main.java.view.IView;



//If someone wants to talk with IViewClient, they need to register to it
public class Adapter implements IView.IListener
{
	private final IView m_view;
	private final IModel m_model;
	
	private final GameLogic m_gameLogic;
	private final SetupLogic m_setupLogic;
	
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
		m_setupLogic = new SetupLogic(view, model);
		
		onNewGame();
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
	public void onSaveGame()
	{
		String filename = m_view.getSavePath();
		
		m_model.save(filename);
		
	}



	@Override
	public void onOpenGame()
	{
		String filename = m_view.getOpenPath();
		m_model.read(filename);
		m_gameLogic.onLoad();
		
		
	}



	@Override
	public void onCustomGame()
	{
		m_setupLogic.customGame(EnableState.ENABLED);
		
	}



	@Override
	public void onNewGame()
	{
		m_setupLogic.newGame();
		m_gameLogic.newGame();
		
	}



	@Override
	public void onApply()
	{
		m_setupLogic.customGame(EnableState.DISABLED);
		
	}



	@Override
	public void onCancel() 
	{
		m_setupLogic.customGame(EnableState.DISABLED);
		System.out.println("On cancel clicked");
		
	}
	
	

	
	
	

}
