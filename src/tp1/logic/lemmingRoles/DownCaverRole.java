package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole
{
	//private constants
	private static final String NAME = Messages.DOWNCAVER_ROL_NAME;
	private static final String SYMBOL = Messages.DOWNCAVER_ROL_SYMBOL;
	private static final String HELP = Messages.DOWNCAVER_ROL_HELP;
	private static final String ICON = Messages.LEMMING_DOWN_CAVER;
	private boolean hasCaved;
	
	//constructor
	public DownCaverRole() 
	{
		super(NAME, SYMBOL, HELP);
		hasCaved=false;
	}
	
	//methods	
	@Override
	public void start(Lemming lemming) {}

	@Override
	public void play(Lemming lemming) 
	{
		if(this.hasCaved) 
		{
			lemming.doFall();
			this.hasCaved=false;
		}
		
		else 
		{ 
			lemming.disableRole();
			lemming.walkOrFall();
		}
	}

	@Override
	public String getIcon(Lemming lemming) 
	{
		return ICON;
	}
	
	@Override
	 public boolean interactWith(Wall wall, Lemming lemming) 
	{ 
		 wall.dead();
		 this.hasCaved = true;
		 return this.hasCaved;
	 }
}
