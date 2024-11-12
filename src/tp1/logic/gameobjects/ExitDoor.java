package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject
{ 	
	//constructor
	public ExitDoor(Game game, Position position)
	{
		super(game, position);
	}

	//methods
	@Override
	public String getIcon()	{ return Messages.EXIT_DOOR; }	//return exit door icon
	
	@Override
	public boolean isSolid() { return false; }	//not a wall
	
	@Override
	public void update() {}	//no update needed

	@Override
	public String toString() 
	{
		return this.position.toString() + this.getIcon(); 
	}

	@Override
	public boolean receiveInteraction(GameItem other)
	{
		return other.interactWith(this);	//return false (gameobject method)
	}	 
}
