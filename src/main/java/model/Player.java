package main.java.model;
import static main.java.types.Types.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Player
{
	//Byte used instead of int for easier stream operation (assuming ball number is 256)
	private byte[] m_holes;
	
	//Score is a kazan (better readability)
	private int m_score;
	
	private int m_tuzIndex;
	
	public Player(int playerNum, int ballsPerHole, int score)
	{
		m_holes = new byte[DEFAULT_HOLES_PER_PLAYER];
		setScore(score);
		
		
		//By default no player has a tuz
		m_tuzIndex = -1;
		
		for(int i = 0; i < m_holes.length; i++)
		{
			m_holes[i] = (byte) ballsPerHole;
		}
		
	}

	public int getBalls(int holeIndex)
	{
		return m_holes[holeIndex];
	}
	
	public void setBalls(int holeIndex, int ballCount)
	{
		m_holes[holeIndex] = (byte) ballCount;
	}

	public int getScore()
	{
		return m_score;
	}

	public void setScore(int score)
	{
		m_score = score;
	}

	public int getTuzIndex()
	{
		return m_tuzIndex;
	}

	public void setTuzIndex(int tuzIndex)
	{
		m_tuzIndex = tuzIndex;
		
	}

	public void saveTo(DataOutputStream os) throws IOException
	{
		os.write(m_holes);
		os.writeInt(m_score);
		os.writeInt(m_tuzIndex);
		
	}
	
	public void readFrom(DataInputStream is) throws IOException
	{
		is.read(m_holes);
		m_score = is.readInt();
		m_tuzIndex = is.readInt();
		
	}

	
}
