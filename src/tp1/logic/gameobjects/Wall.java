package tp1.logic.gameobjects;

//import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Wall extends GameObject
{	
	//constructor
	public Wall(Game game, Position position) 
	{
		super(game, position);
	}
	
	//methods
	@Override
	public String getIcon() { return Messages.WALL;	}	//return wall icon	

	@Override
	public boolean isSolid() { return true;	}	//is a wall
	
	@Override
	public void update() {}	//no update needed

	@Override
	public String toString() 
	{
		return this.position.toString() + this.getIcon();
	}

	public void dead() { this.isAlive=false; }
		
	@Override
	public boolean receiveInteraction(GameItem other) 
	{
		return other.interactWith(this);
	}
}
