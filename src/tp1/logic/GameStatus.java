package tp1.logic;

public interface GameStatus //GameStatus = show data 
{	
	public int getCycle();
	public int numLemmingsInBoard();
	public int numLemmingsDead();
	public int numLemmingsExit();
	public int numLemmingsToWin();

	public String positionToString(int col, int row);

	public boolean playerWins();
	public boolean playerLooses();
}
