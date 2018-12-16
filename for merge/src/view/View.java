package view;

import javax.swing.*;

import static types.Types.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import view.gui.PlayerView;


public class View extends JFrame implements IView, PlayerView.IListener
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


	public View(String gName){
		super(gName);
		setResizable(false);
	}

	public void addComps(final Container pane){
		final JPanel comps = new JPanel();
		comps.setLayout(new GridLayout(2,0));
		JPanel ctrls = new JPanel();
		ctrls.setLayout(new GridLayout(1,0));

		//Buttons
		JButton play = new JButton("Play");
		JButton quit = new JButton("Quit");

		//Add Components to SplashScreen
		comps.add(new JLabel("Image Area"));
		ctrls.add(play);
		ctrls.add(quit);

		//ActionListeners
		play.addActionListener(e -> {
			mainGame();
			m_frame.setVisible(true);
		});

		quit.addActionListener(e -> {
			System.exit(0);
		});

		pane.add(comps, BorderLayout.NORTH);
		pane.add(ctrls, BorderLayout.SOUTH);

	}
	
	public void startView()
	{
		View startup = new View("Game");
		startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startup.addComps(startup.getContentPane());
		startup.pack();
		startup.setVisible(true);
	}

	private void mainGame(){
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
