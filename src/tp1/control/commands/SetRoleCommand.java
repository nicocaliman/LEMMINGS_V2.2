package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Command
{	
	//private constants
    private static final String NAME = Messages.COMMAND_SET_ROLE_NAME;
    private static final String SHORTCUT = Messages.COMMAND_SET_ROLE_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_SET_ROLE_DETAILS;
    private static final String HELP = Messages.COMMAND_SET_ROLE_HELP + LemmingRoleFactory.RoleHelp();
    private LemmingRole role;
    private Position position;
    
    //constructor
	public SetRoleCommand() 
	{
		super(NAME, SHORTCUT, DETAILS, HELP);	//call command constructor
	}
	
	//methods
	@Override
	public void execute(GameModel game, GameView view) 
	{
		if(position.isOutOfBoard() || !game.setRole(role, position))	//if input position is off the board or the role does not exist 
			view.showError(Messages.SETROLE_COMMAND_ERROR);	//error message
		else	
		{
			game.update();	//update game
			view.showGame();	//show updated board
		}
	}  

	@Override
	public Command parse(String[] commandWords)
	{
		Command c = null;
		if(commandWords.length==4 && this.matchCommandName(commandWords[0]) && LemmingRoleFactory.parse(commandWords[1])!=null) 
		{			
			//sr <role> <y><x> 
			int x = Integer.parseInt(commandWords[3])-1;	//coord. x
			int y = this.stringToInt(commandWords[2]);		//coord. y
			
			position = new Position(x, y);					
			role = LemmingRoleFactory.parse(commandWords[1]);	//lemming role
			
			c = this;	//setRoleCommand
		}
		
		return c;
	}
	
	private int stringToInt(String s) 
	{
		int num;
		
		if(s.hashCode()<97) num = s.hashCode()-65;
		
		else 
			num = s.hashCode()-97;
		
		return num;
	}
}