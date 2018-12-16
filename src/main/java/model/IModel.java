package main.java.model;

public interface IModel
{
	int getPlayerBalls(int playerNum, int holeNum);
	int getPlayerScore(int playerNum);
	
	void setPlayerBalls(int playerNum, int hole, int ballCount);
	void setPlayerScore(int playerNum, int score);
	
	public int addBalls(int playerNum, int holeNum, int ballCount);
	public int takeAllBalls(int playerNum, int holeNum);
	public int addToScore(int playerNum, int ballCount);
	
	int getPlayerTuzIndex(int playerNum);
	void setPlayerTuzIndex(int playerNum, int tuzIndex);
	void save(String filename);
	void read(String filename);
	
	
}
