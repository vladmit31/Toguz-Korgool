package view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class ToolbarView extends JPanel
{
	
	public interface IListener extends EventListener
	{
		//default in case we dont need these events for testing
		default void onNewGame() {}
		default void onSaveGame() {}
		default void onOpenGame() {}
		default void onCustomGame() {}
	}
	
	private JButton[] m_toolbarButtons = {
			new JButton("New game"),
			new JButton("Save game"),
			new JButton("Open game"),
			new JButton("Custom game")
	};
	
	private enum ActionType
	{
		NEW_GAME,
		SAVE_GAME,
		OPEN_GAME,
		CUSTOM_GAME
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

					//TODO: call method
					
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
	}
}