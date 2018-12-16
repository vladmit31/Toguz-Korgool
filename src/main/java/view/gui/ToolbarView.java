package main.java.view.gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.types.Types.EnableState;



public class ToolbarView extends JPanel
{
	
	public interface IListener extends EventListener
	{
		//default in case we dont need these events for testing
		default void onNewGame() {}
		default void onSaveGame() {}
		default void onOpenGame() {}
		default void onCustomGame() {}
		default void onApply() {}
		default void onCancel() {}
	}
	
	private JButton[] m_toolbarButtons = {
			new JButton("New game"),
			new JButton("Save game"),
			new JButton("Open game"),
			new JButton("Custom game"),
			new JButton("Apply"),
			new JButton("Cancel")
	};
	
	private enum ActionType
	{
		NEW_GAME,
		SAVE_GAME,
		OPEN_GAME,
		CUSTOM_GAME,
		APPLY,
		CANCEL
	}
	
	
	private final IListener m_listener;
	
	public ToolbarView(IListener listener)
	{
		m_listener = listener;
		
		
		for(JButton button : m_toolbarButtons)
		{
			this.add(button);
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e)
				{
					
					int type =  ActionType.NEW_GAME.ordinal();
					
					for(JButton button : m_toolbarButtons)
					{
						if(button == e.getSource())
						{
							switch(ActionType.values()[type])
							{
							case NEW_GAME:
								m_listener.onNewGame();
								break;
								
							case SAVE_GAME:
								m_listener.onSaveGame();
								break;
								
							case OPEN_GAME:
								m_listener.onOpenGame();
								break;
								
							case CUSTOM_GAME:
								m_listener.onCustomGame();
								break;
								
							case APPLY:
								m_listener.onApply();
								break;
								
							case CANCEL:
								m_listener.onCancel();
								break;
								
							default:
								assert(false); //BUG!!!!!!!!!!!!!!!
								break;
							}

							break;
							
						}
						
						type++;
	
					}

				}
		
			});
		}
		
		showCustomGameButtons(EnableState.DISABLED);
		
	}
	
	public void showCustomGameButtons(EnableState state)
	{
		if(state == EnableState.ENABLED)
		{
			for(int i = 0; i < m_toolbarButtons.length; i++)
			{
				if(m_toolbarButtons[i].getText().equals("Apply"))
				{
					m_toolbarButtons[i].setVisible(true);
				}
				else if(m_toolbarButtons[i].getText().equals("Cancel"))
				{
					m_toolbarButtons[i].setVisible(true);
				}
				else
				{
					m_toolbarButtons[i].setVisible(false);
				}

			}
		}
		else
		{
			System.out.println("i here");
			for(int i = 0; i < m_toolbarButtons.length; i++)
			{
				if(m_toolbarButtons[i].getText().equals("Apply"))
				{
					m_toolbarButtons[i].setVisible(false);
				}
				else if(m_toolbarButtons[i].getText().equals("Cancel"))
				{
					m_toolbarButtons[i].setVisible(false);
				}
				else
				{
					m_toolbarButtons[i].setVisible(true);
				}
			}
		}
		
	}
	
	
}
