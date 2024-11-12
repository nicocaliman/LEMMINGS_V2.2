package tp1.logic;

import tp1.logic.gameobjects.GameItem;

public interface GameWorld	//GameWorld = interaction with game objects
{	
	public boolean isSolid(Position position);
	public boolean isInAir(Position position);
	public void lemmingDead();
	public void lemmingArrived();
	public boolean receiveInteractionsFrom(GameItem obj);
}
