package main.java.adapter;

import static main.java.types.Types.*;

import main.java.model.IModel;
import main.java.view.IView;

public class GameLogic
{
	private final IView m_view;
	private final IModel m_model;
	
	private int m_activePlayer;
	
	
	private class HoleLocation
	{
		//Local scope helper so members are public (no setter getter necessary)
		public int playerNum;
		public int holeNum;
		
		public HoleLocation(int playerNum, int holeNum)
		{
			this.playerNum = playerNum;
			this.holeNum = holeNum;
		}
		
		private void moveToNextLocation()
		{
			if(holeNum < DEFAULT_HOLES_PER_PLAYER - 1)
			{
				holeNum++;
			}
			else
			{
				this.playerNum = getNextPlayer();
				holeNum = 0;
			}
		}

	}
	
	
	public GameLogic(IView view, IModel model)
	{
		m_view = view;
		m_model = model;
		
	}
	
	
	public void onMove(int playerNum, int holeNum)
	{

		performMove(holeNum);
		
		
		if(m_model.getPlayerScore(m_activePlayer) > DEFAULT_WINNING_SCORE)
		{
			m_view.displayWinner(m_activePlayer, true);
		}
		else if(m_model.getPlayerScore(getNextPlayer()) > DEFAULT_WINNING_SCORE)
		{
			m_view.displayWinner(getNextPlayer(), true);
		}
		else
		{
			refreshView();
			
			m_activePlayer = getNextPlayer();
			enableValidHoles();
		}
		
	}


	private void refreshView()
	{
		for(int playerNum = 0; playerNum < NUMBER_OF_PLAYERS; playerNum++)
		{
			for(int holeNum = 0; holeNum < DEFAULT_HOLES_PER_PLAYER; holeNum++)
			{
				int ballCount = m_model.getPlayerBalls(playerNum, holeNum);
				m_view.setBallCount(playerNum, holeNum, ballCount);
			}
			
			m_view.setScore(playerNum, m_model.getPlayerScore(playerNum));
		}
			
		
	}


	public void newGame()
	{
		//Player 1 = 0 , Player2 = 1
		m_activePlayer = 1;
		
		//Hide winning message
		m_view.displayWinner(0, false);
		
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++)
		{
			
			for(int holeNum = 0; holeNum < DEFAULT_HOLES_PER_PLAYER; holeNum++)
			{
				m_view.markTuz(i, holeNum, MarkState.UNMARK);
			}
			
			m_model.setPlayerTuzIndex(i, EMPTY_TUZ);
			
			m_model.setPlayerScore(i, DEFAULT_SCORE);
			m_view.setScore(i, 0);
		}


		enableValidHoles();
		
	}
	
	private void enableValidHoles()
	{
		for(int i = 0; i < DEFAULT_HOLES_PER_PLAYER; i++)
		{
			m_view.enablePlayer(getNextPlayer(), i, EnableState.DISABLED);

			m_view.enablePlayer(m_activePlayer, i, m_model.getPlayerBalls(m_activePlayer, i) == 0 ? EnableState.DISABLED : EnableState.ENABLED);
		}
	}

	
	private void performMove(int holeNum)
	{
		HoleLocation holeLocation = new HoleLocation(m_activePlayer, holeNum);
			
		int ballCount = m_model.getPlayerBalls(m_activePlayer, holeNum);
		
		
		if(ballCount == 0)
			; //Nothing to do - empty statement
		else if(ballCount == 1)
		{
			m_model.setPlayerBalls(m_activePlayer, holeNum, 0);
			
			holeLocation.moveToNextLocation();
			addOneBall(holeLocation);
		}	
		else
		{
			m_model.setPlayerBalls(m_activePlayer, holeNum, 1);
			
			//We have just taken one ball out so we have 1 less
			ballCount--;
			
			//Compare first, then decrement
			while(ballCount-- != 0)
			{
				holeLocation.moveToNextLocation();
				addOneBall(holeLocation);
			}
		}
		
		//At this point, the location is the location of the last hole
		
		tuzConstructor(holeLocation);
		updateScore(holeLocation);
		

	}
	
	//1. If last hole has even number of balls and in enemy side then add to score
	//2. If any ball lands in player tuz, add to score.
	private void updateScore(HoleLocation location)
	{
		//Here, the location is the last hole
		//If this last location is on the opposite players side, and the hole has an even 
		//number of balls then we capture the balls and update the score.
		
		//1.
		if(location.playerNum != m_activePlayer)
		{
			int ballCount = m_model.getPlayerBalls(location.playerNum, location.holeNum);
			
			//An even amount of balls therefore capture
			if(ballCount % 2 == 0)
			{

				m_model.takeAllBalls(location.playerNum, location.holeNum);
				m_model.setPlayerBalls(location.playerNum, location.holeNum, 0);
				
				m_model.addToScore(m_activePlayer, ballCount);
	
			}
		}
		
		//2. 
		for(int playerNum = 0; playerNum < NUMBER_OF_PLAYERS; playerNum++)
		{
			int holeNum = m_model.getPlayerTuzIndex(playerNum);
			
			if(holeNum > EMPTY_TUZ)
			{
				int ballsFromTuz = m_model.takeAllBalls((playerNum + 1) % 2, holeNum);
				m_model.addToScore(playerNum,ballsFromTuz);
			}
		}
	}


	private int getNextPlayer()
	{
		return (m_activePlayer + 1) % 2;
	}
	
	private void addOneBall(HoleLocation location)
	{
		int ballCount = m_model.getPlayerBalls(location.playerNum, location.holeNum);
		m_model.setPlayerBalls(location.playerNum, location.holeNum, ballCount + 1);
		
	}
	
	private void tuzConstructor(HoleLocation location)
	{
		if (m_model.getPlayerTuzIndex(m_activePlayer) == -1 &&
			location.playerNum != m_activePlayer &&
			location.holeNum != DEFAULT_HOLES_PER_PLAYER - 1 &&
			m_model.getPlayerBalls(location.playerNum, location.holeNum) == DEFAULT_TUZ_CONDITION &&
			m_model.getPlayerTuzIndex(location.playerNum) != location.holeNum)
		{
			m_model.setPlayerTuzIndex(m_activePlayer, location.holeNum);
			m_view.markTuz(location.playerNum, location.holeNum, MarkState.MARK);
			System.out.println("Tuz created at hole: " + m_model.getPlayerTuzIndex(m_activePlayer));
		}
		else
		{
			System.out.println("No tuz made");
		}
	}
	
	public void onLoad()
	{
		refreshView();
		
		
		for(int playerNum = 0; playerNum < NUMBER_OF_PLAYERS; playerNum++)
		{
			int opponent = (playerNum + 1) % 2;
			int tuzIndex = m_model.getPlayerTuzIndex(playerNum);
			
			for(int holeNum = 0; holeNum < DEFAULT_HOLES_PER_PLAYER; holeNum++)
			{
				m_view.markTuz(playerNum, holeNum, MarkState.UNMARK);
			}
			
			if (tuzIndex > EMPTY_TUZ)
			{
				m_view.markTuz(opponent, tuzIndex, MarkState.MARK);
			}
			
			m_view.setScore(playerNum, m_model.getPlayerScore(playerNum));
			
		}
		
		enableValidHoles();
		
	}
	

		
		
	
	
	
	
	
	
}
