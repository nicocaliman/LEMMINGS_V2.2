package tp1.control.commands;


import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends Command 
{	
	//private constants
	private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    
    //attributes
    private int nLevel;
    
    //constructor
    public ResetCommand() 
    {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
    //methods
    @Override
	public void execute(GameModel game, GameView view) 
    {
    	if(this.nLevel >= -1 && this.nLevel <= 2)	//reset
    	{
    		game.reset(this.nLevel); //reset the game
    		view.showGame();	//show board	
    	}
    	
    	else
    		view.showError(Messages.RESET_COMMAND_ERROR);	//show error
    }

	@Override
	public Command parse(String[] commandWords) 
	{
		Command c = null;
		
		if(this.matchCommandName(commandWords[0]))	//if reset command
		{
			c = this;
			this.nLevel=-1;
			
			if(commandWords.length == 2)	//additional argument
			{
				this.nLevel = Integer.parseInt(commandWords[1]);	//user´s level			
			}
		}
		
		return c;
	}
}
