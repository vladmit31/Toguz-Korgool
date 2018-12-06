package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static types.Types.*;

import java.awt.FlowLayout;
import java.util.EventListener;

import view.gui.PlayerView;


public class View implements IView, PlayerView.IListener
{

	
	private IListener m_listener;
	private JFrame m_frame;
	private JPanel m_mainPanel = new JPanel();
	
	private JPanel m_toolbarPanel = new JPanel();
	private JPanel m_messagePanel = new JPanel();
	
	private JPanel m_playerViewHolder = new JPanel();
	private PlayerView m_playerPanel1; 
	private PlayerView m_playerPanel2;
	
	
	
	private PlayerView[] m_playerViews = new PlayerView[NUMBER_OF_PLAYERS];
	
	
	public View()
	{
		m_frame = new JFrame("Dupa");
	
		
		
		BoxLayout boxlayout = new BoxLayout(m_mainPanel, BoxLayout.Y_AXIS);
		
		
		
		
		BoxLayout pBoxlayout = new BoxLayout(m_frame.getContentPane(), BoxLayout.Y_AXIS);
		
		
		m_playerPanel1 = new PlayerView(1, this);
		m_playerPanel2 = new PlayerView(2, this);
		
		m_playerViewHolder.add(m_playerPanel1);
		m_playerViewHolder.add(m_playerPanel2);
		
		//FlowLayout flowlayout = new FlowLayout();
		//m_mainPanel.setLayout(flowlayout);
		
		//m_mainPanel.add(m_playerPanel1);
		//m_mainPanel.add(m_playerPanel2);
		
		m_mainPanel.add(m_playerViewHolder);
		
		m_frame.add(m_mainPanel);
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
	public void setKorgulCount(int playerNum, int holeNum, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerListener(IListener listener)
	{
		//There can only be 1 listener in our case (not multicast listener)
		//Could have an array of listeners if we had more
		assert m_listener == null;
		
		m_listener = listener;
		
	}

	@Override
	public void onPlay(int holeNum, int playerNum)
	{
		m_listener.onPlay(holeNum, playerNum);
		
	}

	

}
