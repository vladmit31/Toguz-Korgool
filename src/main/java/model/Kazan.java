package model;

public class Kazan extends Storage{

    public Kazan(){
        super();
    }

    public boolean checkWinningCondition()
    {
        return (getNumberOfBalls()>81);
    }
}