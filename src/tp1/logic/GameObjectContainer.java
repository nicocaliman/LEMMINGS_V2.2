package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class GameObjectContainer
{
	private List<GameObject> objects;

	public GameObjectContainer()
	{
		this.objects = new ArrayList<GameObject>();
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object)
	{
		this.objects.add(object);	//add object to its list
	}
	  
	public void update() 
	{
		for (GameObject object : this.objects) 
		{
			object.update();	//update game object
		}
		
		this.removeDead();
	}
	
	public String positionToString(Position position)
	{
		StringBuilder elements = new StringBuilder();	//mutable string(we can add string representation from an object + a differente representation from another object)

	    for (GameObject object : this.objects)
	    {
	        if (object.isInPosition(position)) 
	        {
	            elements.append(object.getIcon());
	        }
	    }
	    
	    return elements.length() > 0 ? elements.toString() : Messages.EMPTY;	//if string length is > 0 print string, else print empty string " "
	}
	
	private void removeDead()
	{
		for(int i = this.objects.size()-1; i >= 0; --i) 
		{	
			if (!this.objects.get(i).isAlive())
			{	//if object at position i is dead
				this.objects.remove(i);	//remove dead object 
			}
		}		
	}
	
	public boolean isSolid(Position position)
	{
		int i = 0;
		
		while(i < this.objects.size() && !(this.objects.get(i).isInPosition(position) && this.objects.get(i).isSolid())) 
		{
			++i;
		}
		
		return i < this.objects.size();	//true if there is a wall in that position
	}
		
	@Override
	public String toString()
	{
		StringBuilder elements = new StringBuilder();
		
	    for (GameObject object : this.objects) 
	    {
	    	elements.append(object.toString());
	    }
	    
		return elements.toString();	
	}

	public boolean setRole(LemmingRole role, Position position)
	{
		int i = 0;
		
		while(i < this.objects.size() && !(this.objects.get(i).isInPosition(position) && this.objects.get(i).setRole(role))) 
		{
			++i;
		}
		
		return i < this.objects.size();	//true if there is an object that can modify its role
	}
	
	public boolean receiveInteractionsFrom(GameItem obj) 
	{
		boolean interact = false;
		
	    for (GameObject object : this.objects)	//check which object from the container is receiving an interaction
	    {
	    	if(object.receiveInteraction(obj)) interact=true;
	    }
	    
	    return interact;
	}
}
