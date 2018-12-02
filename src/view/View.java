package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static types.Types.*;
import view.gui.PlayerView;


public class View implements IView
{
	
	
	
	private JFrame m_frame;
	private JPanel m_mainPanel = new JPanel();
	
	private JPanel m_toolbarPanel = new JPanel();
	private JPanel m_messagePanel = new JPanel();
	private JPanel m_playerPanel1 = new JPanel();
	private JPanel m_playerPanel2 = new JPanel();
	
	
	
	private PlayerView[] m_playerViews = new PlayerView[NUMBER_OF_PLAYERS];
	
	
	
	private IViewClient m_client;
	
	public View()
	{
		m_frame = new JFrame("Dupa");
		BoxLayout boxlayout = new BoxLayout(m_mainPanel, BoxLayout.Y_AXIS);
		
		
		m_mainPanel.add(m_playerPanel1);
		m_mainPanel.add(m_playerPanel2);
		
		m_frame.add(m_mainPanel);
		m_frame.setSize(700,  500);
		
		
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
	public void setKorgulCount(int playerNum, int holeNum, int count) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void registerClient(IViewClient client)
	{
		//Only 1 client is allowed
		assert m_client == null;
		
		m_client = client;
		
		
		//To later let the controller know who is who
		m_playerViews[0] = new PlayerView(m_client, 0, m_playerPanel1);
		m_playerViews[1] = new PlayerView(m_client, 1, m_playerPanel2);
		
		
	}

}
