package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole 
{
	//private constants
	private static final String NAME = Messages.PARACHUTER_ROL_NAME;
	private static final String SYMBOL = Messages.PARACHUTER_ROL_SYMBOL;
	private static final String HELP = Messages.PARACHUTER_ROL_HELP;
	private static final String ICON = Messages.LEMMING_PARACHUTE;
	
	//constructor
	public ParachuterRole() 
	{
		super(NAME, SYMBOL, HELP);
	}
	
	//methods	
	@Override
	public void start(Lemming lemming){	}	//override from LemmingRole

	@Override
	public void play(Lemming lemming) //override from LemmingRole
	{
		lemming.fallForceToZero();	//set fall force to 0
		
		if(lemming.isInAir()) 
			lemming.doFall();
		
		else 
		{	//if lemming lands
			lemming.disableRole();	//disable parachute role
			lemming.walkOrFall();	
		}
	}

	@Override
	public String getIcon(Lemming lemming) //override from LemmingRole
	{
		return ICON;	//parachute icon
	}
}