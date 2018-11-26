package model;

public class Hole extends Storage{
    private boolean isTuz;

    public Hole()
    {
        super();
        isTuz = false;
    }

    private void setTuz()
    {
        isTuz = true;
    }

    private boolean getIsTuz()
    {
        return isTuz;
    }

    private void setBallsToZero()
    {
        super.setNumberOfBallsTo(0);
    }

    public int getAllTheBalls()
    {
        int temp = super.getNumberOfBalls();
        setBallsToZero();
        return temp;
    }

}