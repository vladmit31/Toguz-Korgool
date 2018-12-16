package main.java.view.gui;

import static main.java.types.Types.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	
	private JTextField m_textField;
	
	private final IListener m_listener;
	
	
	
	public HoleView(int index, IListener listener)
	{
		//Register to the listener which promises that we can call the functions from
		//the listener interface
		m_listener = listener;
		m_index = index;
		m_ballCount = 0;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		setBorderThickness(DEFAULT_HOLE_BORDER_THICKNESS);
		
		m_button = new JButton("Play");
		
		m_countLabel = new JLabel(m_ballCount + " korguls");
		
		m_indexLabel = new JLabel();
		m_indexLabel.setText(Integer.toString(index + 1));
		
		m_textField = new JTextField();
		enableInput(EnableState.DISABLED);
		
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
		this.add(m_textField);
		
	}
	
	public JLabel getCountLabel()
	{
		return m_countLabel;
	}
	
	public void setHoleCountLabel(int count)
	{
		m_countLabel.setText(Integer.toString(count) + " korguls");
	}
	
	//Change state of button 
	public void setButtonState(EnableState state)
	{
		m_button.setEnabled(state == EnableState.ENABLED);
	}
	
	public void setBorderThickness(int thickness)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black, thickness, true));
	}
	
	public void enableInput(EnableState state)
	{
		m_textField.setVisible(state == EnableState.ENABLED);
	}
	
	public int getEditHoleCount()
	{
		try
		{
			int holeCount = Integer.parseInt(m_textField.getText());
			
			return holeCount;
		}
		catch (NumberFormatException e)
		{
			return DEFAULT_BALLS_PER_HOLE;
		}
		
		
		

	}
	

	
}
