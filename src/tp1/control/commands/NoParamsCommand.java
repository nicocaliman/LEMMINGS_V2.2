package tp1.control.commands;

public abstract class NoParamsCommand extends Command
{
	//constructor
	public NoParamsCommand(String name, String shortcut, String details, String help) 
	{		
		super(name, shortcut, details, help);
	}

	//methods
    @Override
	public Command parse(String[] commandWords) 
    {
		Command c = null;
		
		if(this.matchCommandName(commandWords[0]) && commandWords.length==1) 
			c=this;
		
		return c;
	}
}
