package model;

public class Storage {
    private int numberOfBalls;

    public Storage()
    {
        numberOfBalls = 0;
    }

    public int getNumberOfBalls()
    {
        return numberOfBalls;
    }

    public void addBalls(int n)
    {
        numberOfBalls = numberOfBalls + n;
    }

    public void setNumberOfBallsTo(int n)
    {
        numberOfBalls = n;
    }

}