package main.java.adapter;

import static main.java.types.Types.*;

import javax.swing.JFileChooser;

import main.java.model.IModel;
import main.java.view.IView;

public class SetupLogic
{
	//Structs alternative (from C#)
	//Helper class to hold initial values which will change from special setup.
	//This isn't in the Model package because this is not persistent. 
	//When the user wants to save the game, this information will be sent to the 
	//actual model. The data from the dialogue box will go here.
	private class Player
	{
		public int[] initialBallCount;
		public int initialScore;
		
		public Player()
		{
			initialBallCount = new int[DEFAULT_HOLES_PER_PLAYER];
			initialScore = DEFAULT_SCORE;
			
			for(int i = 0; i < initialBallCount.length; i++)
			{
				initialBallCount[i] = DEFAULT_BALLS_PER_HOLE;
			}
			
		}
		
	}
	
	private final IView m_view;
	private final IModel m_model;
	
	private Player[] m_players = new Player[NUMBER_OF_PLAYERS];
	
	
	
	public SetupLogic(IView view, IModel model)
	{
		m_view = view;
		m_model = model;
		
		for(int i = 0; i < m_players.length; i++)
		{
			m_players[i] = new Player();
		}
		
		newGame();
		
	}


	//We are using those intial values to initalize our player models
	public void newGame()
	{
		int playerNum = 0;
		
		for(Player player : m_players)
		{
			int hole = 0;
			
			for(int numOfBalls : player.initialBallCount)
			{
				m_model.setPlayerBalls(playerNum, hole, numOfBalls);
				m_view.setBallCount(playerNum, hole, numOfBalls);
				
				hole++;
			}
			
			playerNum++;
		}
		
		
	}


	//TODO: Save game implementation
	public void saveGame()
	{
		
		
		
		
		
		
	}


	//TODO: Open game implementation
	public void openGame()
	{
		System.out.println("Open game clicked!");
		
	}

	//TODO: Custom game implementation
	public void customGame(EnableState state)
	{
		m_view.showCustomGameButtons(state);
		m_view.displayHoleInput(state);
		
		setNewDefault();
		updateNewCount();
		
		System.out.println("Displayed the two extra buttons!");		
	}
	
	 public void setNewDefault()
	 {
		 for(int i = 0; i < NUMBER_OF_PLAYERS; i++)
		 {
			 int[] editedValues = m_view.getEditHoleCounts(i);
			 m_players[i].initialBallCount = editedValues;
		 }
		 
		// m_view.showCustomGameButtons(EnableState.DISABLED);
	 }
	 
	 public void updateNewCount()
	 {
		 for(int i = 0; i < NUMBER_OF_PLAYERS; i++)
		 {
			 int[] editedValues = m_view.getEditHoleCounts(i);
			 
			 for(int j = 0; j < DEFAULT_HOLES_PER_PLAYER; j++)
			 {
				 m_model.setPlayerBalls(i, j, editedValues[j]);
				 m_view.setBallCount(i, j, editedValues[j]);
			 }
	 
		 }
	 }
	 
	
	
	
}
