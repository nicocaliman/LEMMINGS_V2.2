package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem	//GameItem = modifications btwn gameobjects &  double-dispatch technique
{	
	public boolean receiveInteraction(GameItem other);

	public boolean interactWith(Lemming lemming);
	public boolean interactWith(Wall wall);
	public boolean interactWith(ExitDoor door);
	public boolean interactWith(MetalWall metalWall);
	
	public boolean isSolid();
	public boolean isAlive();
	public boolean isInPosition(Position pos);
}