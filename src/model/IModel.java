package model;

public interface IModel
{
	int getPlayerBalls(int playerNum, int holeNum);
	int getPlayerScore(int playerNum);
	
	void setPlayerBalls(int playerNum, int hole, int ballCount);
	void setPlayerScore(int playerNum, int score);
}
