package view.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import static types.Types.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import types.Types.EnableState;
import view.IViewClient;

public class PlayerView
{
	private final IViewClient m_client;
	//Needs array of buttons and a handler for these buttons 
	
	private JButton[] m_buttons = new JButton[HOLES_PER_PLAYER];
	private int m_playerNum;
	
	
	
	public PlayerView(IViewClient client, int playerNumber, JPanel panel )
	{
		m_client = client;
		m_playerNum = playerNumber;
		
		for(int i = 0; i < m_buttons.length; i++)
		{
			JButton button = new JButton();
			button.setText(Integer.toString((i + 1)));
			m_buttons[i] = button;
			panel.add(button);
			
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e)
				{
					Object source = e.getSource();
					
					int index = 0;
					
					for(JButton button : m_buttons)
					{
						if(button == source)
						{
							m_client.onMove(m_playerNum, index);
							
							break;
						}
						
						index++;
					}
					
					
				}
		
			});
			

		}
		
	}
	
	public void enable(EnableState state)
	{
		
	}
	
	
	
	
	
}
