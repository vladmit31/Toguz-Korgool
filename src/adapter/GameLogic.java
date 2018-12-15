package adapter;

import view.IView;
import static types.Types.*;
import model.IModel;

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
		
		refreshView();
		
		m_activePlayer = getNextPlayer();
		evaluateHoles();
		
		
		System.out.println("Player: " + playerNum + " Hole Number: " + holeNum);
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
			m_model.setPlayerScore(i, DEFAULT_SCORE);
			m_view.setScore(i, 0);
		}


		evaluateHoles();
		
	}
	
	private void evaluateHoles()
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
		
		
		attemptCapture(holeLocation);
		
		tuzConstructor(holeLocation);
		
		
	}
	
	private void attemptCapture(HoleLocation location)
	{
		//Here, the location is the last hole
		//If this last location is on the opposite players side, and the hole has an even 
		//number of balls then we capture the balls and update the score.
		
		
		
		if(location.playerNum != m_activePlayer)
		{
			int ballCount = m_model.getPlayerBalls(location.playerNum, location.holeNum);
			
			//An even amount of balls therefore capture
			if(ballCount % 2 == 0)
			{
				int currScore = m_model.getPlayerScore(m_activePlayer) + ballCount;
				
				//We set the enemy players balls to 0
				m_model.setPlayerBalls(location.playerNum, location.holeNum, 0);
				m_model.setPlayerScore(m_activePlayer, currScore);
				
				
				m_view.setScore(m_activePlayer, currScore);
				
				if(currScore > DEFAULT_WINNING_SCORE)
				{
					m_view.displayWinner(m_activePlayer, true);
				}
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
			
		}
	}
		
		
	
	
	
	
	
	
}
