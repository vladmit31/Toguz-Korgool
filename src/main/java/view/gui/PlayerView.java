package main.java.view.gui;


import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.types.Types.EnableState;

import static main.java.types.Types.*;

import java.awt.FlowLayout;
import java.util.EventListener;



public class PlayerView extends JPanel implements HoleView.IListener 
{
	public interface IListener extends EventListener
	{
		
		void onPlay(int holeNum, int playerNum);
	}
	
	
	private IListener m_listener;
	private  HoleView[] m_holes = new HoleView[DEFAULT_HOLES_PER_PLAYER];
	private int m_playerNum;
	private JLabel m_score = new JLabel();
	private JPanel m_holePanel = new JPanel();
	
	
	public PlayerView(int playerNumber, IListener listener, int holeCount, boolean reversed)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		FlowLayout flowlayout = new FlowLayout();
		m_holePanel.setLayout(flowlayout);
		
		m_playerNum = playerNumber;
		m_listener = listener;
		
		
		generateHoleView(reversed);
		
		displayScore(0);
		this.add(m_score);
		
		this.add(m_holePanel);
		
		this.setVisible(true);
		
	}
	
	public void enable(int holeIndex, EnableState state)
	{
		m_holes[holeIndex].setButtonState(state);
		
	}

	@Override
	public void onPlay(int holeNum)
	{
		m_listener.onPlay(holeNum, m_playerNum);
		
	}

	public void markTuz(int index, MarkState state)
	{
		m_holes[index].setBorderThickness(state == MarkState.MARK ? TUZ_MARKING_BORDER : DEFAULT_HOLE_BORDER_THICKNESS);
		
	}
	
	public void setBallCount(int holeNum, int ballCount)
	{
		m_holes[holeNum].setHoleCountLabel(ballCount);
	}
	
	public void displayScore(int score)
	{
		m_score.setText("Player: " + (m_playerNum + 1) + " Score: " + score);
	}
	
	public void displayHoleInput(EnableState state)
	{
		for(int i = 0; i < m_holes.length; i++)
		{
			m_holes[i].enableInput(state == EnableState.ENABLED ? EnableState.ENABLED : EnableState.DISABLED);
		}
	}
	
	public int[] getEditHoleCounts()
	{
		int[] editedHoleCounts = new int[DEFAULT_HOLES_PER_PLAYER];
		
		for(int i = 0; i < m_holes.length; i++)
		{
			editedHoleCounts[i] = m_holes[i].getEditHoleCount();
		}
		
		return editedHoleCounts;
	}
	
	
	public void generateHoleView(boolean reversed)
	{
		
		if(reversed)
		{
			for(int i = m_holes.length - 1; i >= 0; i--)
			{
				HoleView hole = new HoleView(i, this);
				m_holes[i] = hole;
				hole.setVisible(true);
				m_holePanel.add(hole);
			}
		}
		else
		{
			for(int i = 0; i < m_holes.length; i++)
			{
				HoleView hole = new HoleView(i, this);
				m_holes[i] = hole;
				hole.setVisible(true);
				m_holePanel.add(hole);
			}
		}
		
		
	}
	
}
