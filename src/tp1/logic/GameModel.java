package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel //GameModel = interaction with user´s input
{	
	public boolean isFinished();
	public void update();
	public void reset(int nLevel);
	public void exit();
	public boolean setRole(LemmingRole role, Position position);	
}
