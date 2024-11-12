package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.ParachuterRole;

public class Game implements GameModel, GameStatus, GameWorld
{
	//constant attributes
	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	public static final int WIN = 2;
	
	private static final int LEMMINGS_LVL_2 = 6;
	private static final int LEMMINGS_LVL_1 = 4;
	private static final int LEMMINGS_LVL_0 = 3;
	
	//attributes
	private int numberOfCycles;
	private int numberLemmingsInBoard;
	private int numberOfDeadLemmings;
	private int numberOfExitLemmings;
	private int numberOfLemmingsToWin;
	private boolean finished;
	
	private int level;
	
	private GameObjectContainer container;
		
	//constructor
	public Game(int nLevel)
	{				
		this.reset(nLevel);
	}

	// GameStatus methods	
	@Override
	public int getCycle() 
	{
		return this.numberOfCycles;	//return number of cycles played
	}

	@Override
	public int numLemmingsInBoard()
	{
		return this.numberLemmingsInBoard;	//return number of lemmings in board
	}

	@Override
	public int numLemmingsDead() 
	{
		return this.numberOfDeadLemmings;	//return number of lemmings who died
	}	

	@Override
	public int numLemmingsExit() 
	{		
		return this.numberOfExitLemmings;	//return number of lemmings that crossed the door
	}

	@Override
	public int numLemmingsToWin()
	{
		return this.numberOfLemmingsToWin;	//return number of lemmings needed to win(they must cross the door)
	}

	@Override
	public String positionToString(int col, int row)
	{
		return this.container.positionToString(new Position(col, row));	//return what object is in that position 
	}

	@Override
	public boolean playerWins()	//true if number of lemmings that exit the door >= number of lemmings to win && number of lemmings in board = 0
	{
		return (this.numLemmingsToWin() <= this.numLemmingsExit() && this.playerLooses());	
	}

	@Override
	public boolean playerLooses()
	{
		return this.numLemmingsInBoard() == 0;	//player looses if there is no lemmings left in board
	}
	
	// GameWorld methods (callbacks)
	@Override
	public boolean isSolid(Position position) 
	{
		return this.container.isSolid(position);	//true if position is solid
	}

	@Override
	public boolean isInAir(Position position)
	{
		return !this.container.isSolid(position);	//true if lemming is in the air
	}

	@Override
	public void lemmingArrived()
	{
		this.numberOfExitLemmings++;
		this.numberLemmingsInBoard--;
	}

	@Override
	public void lemmingDead() 
	{
		this.numberOfDeadLemmings++;
		this.numberLemmingsInBoard--;
	}
	
	@Override
	public boolean receiveInteractionsFrom(GameItem obj) 
	{
		return this.container.receiveInteractionsFrom(obj); //game world receives an interaction from obj
	}	
	
	// GameModel methods
	@Override
	public boolean isFinished() 
	{
		return this.finished || this.playerLooses();	//game finishes if user quits or the number of lemmings in board equals 0
	}	

	@Override	
	public void update() 
	{
		++this.numberOfCycles;      //update number of cycles	
		this.container.update();	//update game elements
	}

	@Override
	public void exit() 
	{
		this.finished = true;	//user quits the game
	}

	@Override
	public void reset(int nLevel) 
	{
		this.numberOfCycles = 0;	//starts at 0
		this.numberOfDeadLemmings = 0;
		this.numberOfExitLemmings = 0;
		this.numberOfLemmingsToWin = WIN;	//exit door lemmings = 2
		
		this.finished = false;	//initially not finished
		
		this.container = new GameObjectContainer();	//instance of GameObjectContainer class
		
		if(nLevel != -1)	
			setLevel(nLevel);	//set same level
		
		switch(this.level)
		{
		case 0: // level = 0
			setNumOfLemmingsInBoard(LEMMINGS_LVL_0); //number of lemmings in board LVL 0 = 3
			initGame0();	//init game map 0
			break;	
		
		case 1:	//level = 1
			setNumOfLemmingsInBoard(LEMMINGS_LVL_1);	//number of lemmings in board LVL 1 = 4
			initGame1();	//init game map 1
			break;
		case 2: //level = 2
			setNumOfLemmingsInBoard(LEMMINGS_LVL_2);	//number of lemmings in board LVL 2 = 6
			initGame2();	//init game map 2
			break;
		}
	}
	
	private void setLevel(int nLevel) 
	{
		this.level = nLevel;		
	}

	@Override
	public boolean setRole(LemmingRole role, Position position) 
	{
		return this.container.setRole(role, position);	//check if one of the game objects from the container can modify its role
	}
	
	// Other methods	
	public void initGame0() //map0
	{			
		//add walls
		this.container.add(new Wall(this, new Position(8, 1)));
		this.container.add(new Wall(this, new Position(9, 1)));
		
		this.container.add(new Wall(this, new Position(2, 4)));
		this.container.add(new Wall(this, new Position(3, 4)));
		this.container.add(new Wall(this, new Position(4, 4)));
		
		this.container.add(new Wall(this, new Position(7, 5)));		

		this.container.add(new Wall(this, new Position(4, 6)));
		this.container.add(new Wall(this, new Position(5, 6)));
		this.container.add(new Wall(this, new Position(6, 6)));
		this.container.add(new Wall(this, new Position(7, 6)));		

		this.container.add(new Wall(this, new Position(8, 8)));		

		this.container.add(new Wall(this, new Position(0, 9)));
		this.container.add(new Wall(this, new Position(1, 9)));		
		this.container.add(new Wall(this, new Position(8, 9)));
		this.container.add(new Wall(this, new Position(9, 9)));
		
		//add lemmings
		this.container.add(new Lemming(this, new Position(9, 0)));
		
		this.container.add(new Lemming(this, new Position(2, 3)));
		
		this.container.add(new Lemming(this, new Position(0, 8)));		
		
		//add exit door
		this.container.add(new ExitDoor(this, new Position(4, 5)));
	}
	
	public void initGame1() //map1
	{
		initGame0();
		this.container.add(new Lemming(this, new Position(3, 3)));		
	}

	public void initGame2() //map2
	{
		initGame1();
		this.container.add(new Wall(this, new Position(3, 5)));
		this.container.add(new MetalWall(this, new Position(3, 6)));
		this.container.add(new Lemming(this, new Position(6, 0)));
		
		Lemming lem=new Lemming(this, new Position(6, 0));
		lem.setRole(new ParachuterRole());
		this.container.add(lem);		
	}
	
	private void setNumOfLemmingsInBoard(int lemmingsLv)
	{
		this.numberLemmingsInBoard = lemmingsLv;		
	}
}