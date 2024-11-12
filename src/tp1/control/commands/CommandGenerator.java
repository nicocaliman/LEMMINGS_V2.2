package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator 
{
	//added commands
	private static final List<Command> availableCommands = Arrays.asList(
			 new SetRoleCommand(),
			 new UpdateCommand(),
		     new ResetCommand(),
		     new HelpCommand(),
		     new ExitCommand()		     
	);

	public static Command parse(String[] commandWords)
	{
		for (Command c: availableCommands) 
		{
			Command command = c.parse(commandWords);	//parse specific command
			
			if(command != null) //if found
				return command;
		}
		return null;
	}
		
	public static String commandHelp() 
	{
		StringBuilder commands = new StringBuilder();
		
		for (Command c: availableCommands) 
		{
			commands.append(c.helpText());
		}		
		return commands.toString();
	}

}