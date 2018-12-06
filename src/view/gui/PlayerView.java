package view.gui;


import javax.swing.BoxLayout;
import javax.swing.JPanel;
import static types.Types.*;

import java.awt.FlowLayout;
import java.util.EventListener;

import types.Types.EnableState;



public class PlayerView extends JPanel implements HoleView.IListener 
{
	public interface IListener extends EventListener
	{
		
		void onPlay(int holeNum, int playerNum);
	}
	
	
	private IListener m_listener;
	private  HoleView[] m_holes = new HoleView[HOLES_PER_PLAYER];
	private int m_playerNum;
	
	
	
	public PlayerView(int playerNumber, IListener listener)
	{
		FlowLayout flowlayout = new FlowLayout();
		this.setLayout(flowlayout);
		
		m_playerNum = playerNumber;
		m_listener = listener;
		
		for(int i = 0; i < m_holes.length; i++)
		{
			HoleView hole = new HoleView(i, this);
			m_holes[i] = hole;
			hole.setVisible(true);
			this.add(hole);
			
		}
		
		this.setVisible(true);
		
	}
	
	public void enable(EnableState state)
	{
		
	}

	@Override
	public void onPlay(int holeNum)
	{
		m_listener.onPlay(holeNum, m_playerNum);
		
	}

	@Override
	public void markTuz(int index)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
