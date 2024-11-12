package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class LemmingRoleFactory 
{
	//added roles
	private static final List<LemmingRole> availableRoles = Arrays.asList(
			 new DownCaverRole(),
			 new ParachuterRole(),
		     new WalkerRole()
		     
	);
	
	public static LemmingRole parse(String input) 
	{
		for (LemmingRole lr: availableRoles) 
		{
			LemmingRole role = lr.parse(input);	//ask abstract role method
			
			if(role != null) return role;
		}
		
		return null;
	}
	
	public static String RoleHelp() 
	{
		StringBuilder roles = new StringBuilder();
		
		for (LemmingRole lr: availableRoles) roles.append(Messages.LINE_SEPARATOR + Messages.TAB + Messages.TAB + lr.getHelp());
		
		return roles.toString();	//return roles description
	}
}
