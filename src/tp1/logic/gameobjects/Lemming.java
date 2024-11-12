package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.WalkerRole;

public class Lemming extends GameObject 
{
	private static final int FALL = 2;
	
	//attributes
	private Direction direction;
	private LemmingRole role;
	private int fallForce;

	//constructor
	public Lemming(Game game, Position position)
	{
		super(game,position);
		this.direction = Direction.RIGHT;	//default movement
		this.role = new WalkerRole();
		this.fallForce = 0;	//initially 0
	}
	
	//methods
	@Override
	public void update() 
	{
		if (this.isAlive)		//if is alive 
		{			
			this.game.receiveInteractionsFrom(this);	//game world receives an interaction from a lemming
			role.play(this);
		}
	}
	
	public void dead() { this.isAlive = false; }	//set lemming´s life	
	
	public boolean isInAir() 
	{
		Position below = new Position(Direction.DOWN, this.position);	//position below lemming
		
		//return true if position below lemming is not solid, else return false if there is a wall down below lemming´s position
		return game.isInAir(below);	
	}
	
	public void walkOrFall()
	{	
		if (this.isInAir()) 
		{	//if lemming is falling				
			this.doFall();				//fall action					
		}
			
		else 
		{	//if lemming is walking
			this.doWalk();		//walk action	
		}		
	}
	
	private void doWalk()
	{
		if (this.fallForce > FALL) 
		{
			this.dead();
			this.game.lemmingDead();
		}
		
		else
		{
			//this.fallForce = 0;
			this.fallForceToZero();
			
			if (this.getDirection() == Direction.RIGHT) 
			{	//if moving towards right
				this.moveRightAction();					
			}
			
			else if (this.getDirection() == Direction.LEFT) {	//if moving towards left
				this.moveLeftAction();
			}
		}		
	}

	private void moveLeftAction() 
	{
		Position newPosition = new Position(Direction.LEFT, this.position);
		
		if (newPosition.isOutOfBoard()|| this.game.isSolid(newPosition)) 
		{ //if new position touches right wall
			this.direction = this.direction.opposite();	//set lemming´s direction
		}
		
		else
		{	// if there is no obstacle
			this.position = newPosition;	//set new position
		}		
	}

	private void moveRightAction()
	{
		Position newPosition = new Position(Direction.RIGHT, this.position);
		
		if (newPosition.isOutOfBoard() || this.game.isSolid(newPosition)) 
		{ //if new position touches right wall
			this.direction = this.direction.opposite();	//set lemming´s direction
		}
		
		else 
		{	// if there is no obstacle
			this.position = newPosition;	//set new position
		}
		
	}

	public void doFall()
	{
		this.fallForce++;
		Position newPosition = new Position(Direction.DOWN, this.position);
						
		if (newPosition.isOutOfBoard())
		{	//if lemming jumps into the void
			this.dead();	//lemming dies
			this.game.lemmingDead();
		}
		
		else if (this.game.isSolid(newPosition) && this.fallForce > FALL) 
		{
				this.dead();
				this.game.lemmingDead();
		}
		
		else 
		{
			this.position = newPosition;
		}
	}
	
	public void fallForceToZero() {	this.fallForce = 0; }

	@Override
	public String getIcon() 
	{
		return this.role.getIcon(this);	//if lemming´s direction is set to the right, show RIGHT LEMMING, else, show LEFT LEMMING representation
	}
	
	@Override
	public String toString() 
	{
		return this.position.toString() + this.getIcon() + this.direction.toString();
	}
	
	public Direction getDirection() {return this.direction; }	//lemming´s direction
	
	@Override
	public boolean isSolid() { return false; }	//not a wall
			
	@Override
	public boolean setRole(LemmingRole role) 
	{
		boolean isSet = true;
		if(role.equals(this.role)) isSet = false;
		else this.role=role;
		return isSet;
	}
	
	public void disableRole() 
	{
		WalkerRole role = new WalkerRole();
		setRole(role);
	}
	
	@Override
	public boolean interactWith(Wall wall)	//lemming interacts with wall
	{
		boolean interact = false;
		if(wall.isInPosition(new Position(Direction.DOWN,this.position))) interact = this.role.interactWith(wall, this);	//call down caver role 
		return interact;
	}
	
	@Override
	public boolean interactWith(ExitDoor obj)	//lemming interacts with exit door
	{
		//we do not care about lemming´s role (every role crosses the door)
		
		boolean interact = false;
		
	    if (obj.isInPosition(this.position))		
	    {	       
	        this.dead();	//lemming dead	
	        this.game.lemmingArrived(); 	//update numberoflemminginboard & numberoflemmingsexit
	        
	        interact = true;	
	    }
	    
	    return interact; // No hay interacción si no está en la posición de la puerta
	}
	
	@Override
	public boolean receiveInteraction(GameItem other) 	
	{
		return other.interactWith(this);	//call interactWith() from other (object)
  }
}
