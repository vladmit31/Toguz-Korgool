package main.java.view;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.types.Types.EnableState;
import main.java.view.gui.HoleView;
import main.java.view.gui.PlayerView;
import main.java.view.gui.ToolbarView;

import static main.java.types.Types.*;

import java.awt.Font;




public class View implements IView, PlayerView.IListener, ToolbarView.IListener
{

	//The adapter is the listener
	private IListener m_viewListener;
	
	
	
	private JFrame m_frame;
	private JPanel m_mainPanel = new JPanel();
	private JPanel m_playersPanel = new JPanel();
	
	private ToolbarView m_toolbarView;
	private JPanel m_messagePanel = new JPanel();
	
	private JLabel m_winningNotification = new JLabel();

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
			if(i == 0)
			{
				PlayerView player = new PlayerView(i, this, DEFAULT_HOLES_PER_PLAYER, true);
				m_playerViews[i] = player;
				m_playersPanel.add(player);
			}
			else
			{
				PlayerView player = new PlayerView(i, this, DEFAULT_HOLES_PER_PLAYER, false);
				m_playerViews[i] = player;
				m_playersPanel.add(player);
			}
			
			
		}
		
		m_playersPanel.setLayout(new BoxLayout(m_playersPanel, BoxLayout.Y_AXIS));
		m_mainPanel.add(m_playersPanel);
		
		m_winningNotification.setFont(new Font("Courier New", Font.BOLD, 64));
		
		
		m_mainPanel.add(m_winningNotification);
		
		displayWinner(0, false);
		
		m_frame.setSize(650, 300);
		
		
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	public void startView()
	{
		m_frame.setVisible(true);
	}
	
	

	public void enablePlayer(int playerNum, int holeIndex, EnableState state)
	{
		m_playerViews[playerNum].enable(holeIndex, state);
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markTuz(int playerNum, int holeNum, MarkState state)
	{
		m_playerViews[playerNum].markTuz(holeNum, state);
		
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
	
	public void onApply()
	{
		m_viewListener.onApply();
	}
	
	public void onCancel()
	{
		m_viewListener.onCancel();
	}
	
	
	
	public void setScore(int playerNum, int score)
	{
		m_playerViews[playerNum].displayScore(score);
	}
	
	public void displayWinner(int playerNum, boolean isVisible)
	{
		
		if(isVisible)
		{
			m_winningNotification.setText("Player " + playerNum + " has won!!!");
			
			m_playersPanel.setVisible(false);
			m_winningNotification.setVisible(true);
			
		}
		else
		{
			m_playersPanel.setVisible(true);
			m_winningNotification.setVisible(false);
		}
		
		
	}
	
	public void displayHoleInput(EnableState state)
	{
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++)
		{
			m_playerViews[i].displayHoleInput(state);
		}
	}

	@Override
	public void showCustomGameButtons(EnableState state)
	{
		m_toolbarView.showCustomGameButtons(state);
	}
	
	public int[] getEditHoleCounts(int playerNum)
	{
		return m_playerViews[playerNum].getEditHoleCounts();
	}

	@Override
	public String getSavePath()
	{
		return getPath(JFileChooser.SAVE_DIALOG);
	}

	@Override
	public String getOpenPath()
	{
		return getPath(JFileChooser.OPEN_DIALOG);
	}
	
	public String getPath(int option)
	{
		JFrame saveFrame = new JFrame();
		
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogType(option);
		
		int retVal = fileChooser.showOpenDialog(saveFrame);
		
		if(retVal == JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		
		
		return null;
	}

	

}
