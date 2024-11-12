package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;

public abstract class GameObject implements GameItem
{
	//attributes
	protected Position position;
	protected boolean isAlive;
	protected GameWorld game;
	
	//constructor
	public GameObject(GameWorld game, Position pos) 
	{
		this.isAlive = true;
		this.position = pos;
		this.game = game;
	}
	
	//methods	
	@Override
	public boolean isInPosition(Position pos) 
	{
		return this.position.equals(pos);
	}
 	
	@Override
	public boolean isAlive() { return isAlive; }
	
	public boolean setRole(LemmingRole role) { return false; }	//wall/metal wall/exit door do not have a role
	
	@Override
	public boolean interactWith(Lemming lemming) { return false; }	//walker/wall/metal wall/exit door can not interact with another lemming

	@Override
	public boolean interactWith(Wall wall) { return false; }	//walker/wall/metal wall/exit door can not interact with a wall

	@Override
	public boolean interactWith(ExitDoor door) { return false; }	//walker/wall/metal wall/exit door can not interact with an exit door (can not modify the door)
	
	@Override
	public boolean interactWith(MetalWall metalWall) { return false; }	//walker/wall/metal wall/exit door can not interact with a metal wall
	
	@Override
	public abstract boolean isSolid();
		
	public abstract void update();
	
	public abstract String getIcon();
}
