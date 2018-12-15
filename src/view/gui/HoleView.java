package view.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class HoleView extends JPanel
{
	//Whoever is interested in these events, implement this interface
	public interface IListener extends EventListener
	{
		//default in case we dont need these events for testing
		default void onPlay(int holeNum) {}
		default void markTuz(int holeNum) {}
	}
	
	private int m_index;
	private JLabel m_indexLabel;
	private JLabel m_countLabel;
	private JButton m_button;
	private int m_ballCount;
	
	private final IListener m_listener;
	
	
	
	public HoleView(int index, IListener listener)
	{
		//Register to the listener which promises that we can call the functions from
		//the listener interface
		m_listener = listener;
		m_index = index;
		m_ballCount = 0;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		
		
		m_button = new JButton("Play");
		
		m_countLabel = new JLabel(m_ballCount + " korguls");
		
		m_indexLabel = new JLabel();
		m_indexLabel.setText(Integer.toString(index + 1));
		
		m_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				m_listener.onPlay(m_index);

			}
	
		});
		
	
		this.add(m_indexLabel);
		this.add(m_countLabel);
		this.add(m_button);
		
	}
	
	public JLabel getCountLabel()
	{
		return m_countLabel;
	}
	
	public void setHoleCountLabel(int count)
	{
		m_countLabel.setText(Integer.toString(count));
	}
	

	
}
