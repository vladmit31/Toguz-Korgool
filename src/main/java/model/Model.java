package main.java.model;
import static main.java.types.Types.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Model implements IModel
{
	private final Player[] m_players = new Player[NUMBER_OF_PLAYERS];
	
	public Model()
	{
		//Default game
		reset(DEFAULT_BALLS_PER_HOLE, DEFAULT_SCORE);
	}

	
	//Reset the two players with a score and the amount of balls per hole.
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


	@Override
	public int getPlayerTuzIndex(int playerNum)
	{
		return m_players[playerNum].getTuzIndex();
	}


	@Override
	public void setPlayerTuzIndex(int playerNum, int tuzIndex)
	{
			m_players[playerNum].setTuzIndex(tuzIndex);
	}
	
	//TODO: Override reset to change the individual player holes.
	
	//Return the resulting number of balls in that hole for a given player
	public int addBalls(int playerNum, int holeNum, int ballCount)
	{
		int ballsInHole = m_players[playerNum].getBalls(holeNum);
		
		int numOfBalls = ballsInHole + ballCount;
		
		m_players[playerNum].setBalls(holeNum, ballsInHole + ballCount);
		
		
		return numOfBalls;
	}
	
	public int takeAllBalls(int playerNum, int holeNum)
	{
		int ballCount = m_players[playerNum].getBalls(holeNum);
		m_players[playerNum].setBalls(holeNum, 0);
		
		return ballCount;
		
	}
	
	public int addToScore(int playerNum,int ballCount)
	{
		int currScore = m_players[playerNum].getScore();
		
		m_players[playerNum].setScore(currScore + ballCount);
		
		return m_players[playerNum].getScore();
	}


	@Override
	public void save(String filename)
	{
		
		try
		{
			DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));

			for(int playerNum = 0; playerNum < m_players.length; playerNum++)
			{
				m_players[playerNum].saveTo(os);
			}
			
			os.close();
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public void read(String filename)
	{
		try
		{
			DataInputStream os = new DataInputStream(new FileInputStream(filename));

			for(int playerNum = 0; playerNum < m_players.length; playerNum++)
			{
				m_players[playerNum].readFrom(os);
			}
			
			os.close();
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
