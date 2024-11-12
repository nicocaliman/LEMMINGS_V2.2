package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public abstract class AbstractRole implements LemmingRole 
{
	//attributes
	private final String name;
	private final String symbol;
	private final String help;
	
	//constructor
	public AbstractRole (String name, String symbol, String help) 
	{
		this.name = name;
		this.symbol = symbol;
		this.help = help;
	}
	
	//methods 	
	@Override
	public LemmingRole parse(String input)	 
	{
		LemmingRole role = null;
		if(input.equalsIgnoreCase(this.getName())||input.equalsIgnoreCase(this.getSymbol())) role = this;	//if role found
		return role;	
	}

	@Override
	public String getName() { return this.name; }	//get subclases role name
	
	@Override
	public String getHelp()	{ return this.help; }	//get subclasses role description
		
	public String getSymbol() { return this.symbol; }	//get subclasses role symbol
	
	@Override
	public boolean receiveInteraction(GameItem other, Lemming lemming) 
	{
		 return other.interactWith(lemming);
	}

	@Override
	public boolean interactWith(Lemming receiver, Lemming lemming) { return false; }	//default behaviour
	
	@Override
	public boolean interactWith(Wall wall, Lemming lemming)	{ return false; }	//default behaviour
	
	@Override
	public boolean interactWith(ExitDoor door, Lemming lemming)	{ return false; }	//default behaviour
		 
	public boolean equals(LemmingRole role) 
	{
		return this.name.equals(role.getName());
	}
}
