import static org.junit.Assert.*
import java.util.Random

import model.Model;

public class ModelTests {
	
	@Test
	public void resetResetsAllTheHolesToZeroForPlayerOne()
	{
		Model m = new Model()
		int i = 0;
		Random rand = new Random()
		while(i<9)
		{
			m.setPlayerBalls(0, i, random.nextInt(10) + 1);
			i++;
		}
		int counter = 0;
		int j = 0;
		while(j<9)
		{
			if(m.getPlayerBalls(0, j) != 0)
				counter++;
		}
		
		assertEquals(0, counter);
	}

	@Test
	public void resetResetsAllTheHolesToZeroForPlayerTwo()
	{
		Model m = new Model();
		int i = 0;
		Random rand = new Random();
		while(i<9)
		{
			m.setPlayerBalls(1, i, random.nextInt(10) + 1);
			i++;
		}
		int counter = 0;
		int j = 0;
		while(j<9)
		{
			if(m.getPlayerBalls(1, j) != 0)
				counter++;
		}
		
		assertEquals(0, counter);
	}
	
	@Test
	public void setPlayerOneScoreToRandomValue()
	{
		Model m= new Model();
		Random rand = new Random();
		int random_value = rand.nextInt(100)+1;
		
		
		m.setPlayerScore(0, random_value);
		
		assertEquals(random_value, m.getPlayerScore(0));
	}
	
	@Test
	public void setPlayerTwoScoreToRandomValue()
	{
		Model m= new Model();
		Random rand = new Random();
		int random_value = rand.nextInt(100)+1;
		
		
		m.setPlayerScore(0, random_value);
		
		assertEquals(random_value, m.getPlayerScore(0));
	}
}
