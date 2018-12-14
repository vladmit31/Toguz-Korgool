package model;
import static types.Types.*;

public class Model implements IModel
{
	private final Player[] m_players = new Player[NUMBER_OF_PLAYERS];
	
	public Model()
	{
		//Default game
		reset(DEFAULT_BALLS_PER_HOLE, DEFAULT_SCORE);
	}

	
	
	public void reset(int ballsPerHole, int score)
	{
		for(int i = 0; i < m_players.length; i++)
		{
			m_players[i] = new Player(i, ballsPerHole, score);
		}
	}


	//Delegation of tasks between the Player and the Model
	
	@Override
	public int getPlayerBalls(int playerNum, int holeNum)
	{
		return m_players[playerNum].getBalls(holeNum);
	}


	@Override
	public int getPlayerScore(int playerNum)
	{
		return m_players[playerNum].getScore();
	}


	@Override
	public void setPlayerBalls(int playerNum, int holeNum, int ballCount)
	{
		m_players[playerNum].setBalls(holeNum, ballCount);
	}


	@Override
	public void setPlayerScore(int playerNum, int score)
	{
		m_players[playerNum].setScore(score);
		
	}
	
	//TODO: Override reset to change the individual player holes.
	
	
}
