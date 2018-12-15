package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static types.Types.*;

import view.gui.HoleView;
import view.gui.PlayerView;
import view.gui.ToolbarView;


public class View implements IView, PlayerView.IListener, ToolbarView.IListener
{

	//The adapter is the listener
	private IListener m_viewListener;
	
	
	
	private JFrame m_frame;
	private JPanel m_mainPanel = new JPanel();
	
	private ToolbarView m_toolbarView;
	private JPanel m_messagePanel = new JPanel();
	
	
	//private PlayerView m_playerPanel1; 
	//private PlayerView m_playerPanel2;
	
	
	
	private PlayerView[] m_playerViews = new PlayerView[NUMBER_OF_PLAYERS];
	
	
	public View()
	{
		m_frame = new JFrame("My Game");
	
		
		m_mainPanel.setLayout(new BoxLayout(m_mainPanel, BoxLayout.Y_AXIS));
		m_frame.add(m_mainPanel);
		
		//'this' is the listener (ME!)
		m_toolbarView = new ToolbarView(this);
		
		m_mainPanel.add(m_toolbarView);
		
		

		for(int i = 0; i < m_playerViews.length; i++)
		{
			
			PlayerView player = new PlayerView(i, this, DEFAULT_HOLES_PER_PLAYER);
			m_playerViews[i] = player;
			m_mainPanel.add(player);
			
		}
		
		
		
	
		
		//FlowLayout flowlayout = new FlowLayout();
		//m_mainPanel.setLayout(flowlayout);
		
		//m_mainPanel.add(m_playerPanel1);
		//m_mainPanel.add(m_playerPanel2);
		
		
		
		m_frame.setSize(700, 500);
		m_frame.pack();
		
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void startView()
	{
		m_frame.setVisible(true);
	}
	
	

	@Override
	public void enablePlayer(int playerNum, EnableState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markTuz(int playerNum, int holeNum, MarkState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBallCount(int playerNum, int holeNum, int ballCount)
	{
		m_playerViews[playerNum].setBallCount(holeNum, ballCount);
		
	}

	public void registerListener(IListener listener)
	{
		//There can only be 1 listener in our case (not multicast listener)
		//Could have an array of listeners if we had more
		assert m_viewListener == null;
		
		m_viewListener = listener;
		
	}

	@Override
	public void onPlay(int holeNum, int playerNum)
	{
		m_viewListener.onPlay(holeNum, playerNum);
		
	}
	
	
	@Override
	public void onNewGame()
	{
		m_viewListener.onNewGame();
	}
	
	public void onSaveGame()
	{
		m_viewListener.onSaveGame();
	}
	
	public void onOpenGame()
	{
		m_viewListener.onOpenGame();
	}
	
	public void onCustomGame()
	{
		m_viewListener.onCustomGame();
	}

	

}
