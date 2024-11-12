package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends GameObject 
{
	//constructor
	public MetalWall(GameWorld game, Position pos) 
	{
		super(game, pos);
	}
	
	//methods
	@Override
	public boolean isSolid() { return true; }	//is solid
		
	@Override
	public void update() {}

	@Override
	public String getIcon()	{ return Messages.METALWALL; }	//return metal wall icon
		
	@Override
	public String toString() { return this.position.toString() + this.getIcon(); }
	
	@Override
	public boolean receiveInteraction(GameItem other) 
	{
		return other.interactWith(this);
	}
}
