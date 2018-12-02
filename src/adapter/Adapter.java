package adapter;
import view.IView;
import model.IModel;
import view.IViewClient;
import view.IViewClient.AppEvent;


//If someone wants to talk with IViewClient, they need to register to it
public class Adapter implements IViewClient
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
		
		//Register the observer of the view (which is me, as in, this object)
		//this is a reduced observer pattern - we are not multicasting and it is type-safe (so we dont have to cast Objects)
		//So when the view reports an event, we will handle it.
		m_view.registerClient(this);
		
		m_gameLogic = new GameLogic(view, model);
		m_toolbarLogic = new ToolbarLogic(view, model);
	}
	
	//The business logic delegation
	public void onMove(int playerNum, int holeNum)
	{
		m_gameLogic.onMove(playerNum, holeNum);
	}
	
	public void onAppEvent(AppEvent appEvent)
	{
		m_toolbarLogic.onAppEvent(appEvent);
	}
	
	

}
