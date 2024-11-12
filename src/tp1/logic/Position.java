package tp1.logic;

import tp1.view.Messages;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public final class Position 
{
	//attributes
	private final int col;
	private final int row;
	
	//constructors
	public Position(int column, int row) 
	{
		this.col = column;
		this.row = row;
	}
	
	public Position(Direction direction, Position position) 
	{
		this.col = position.col + direction.getX();
		this.row = position.row + direction.getY();
	}

	//methods
	@Override
	public String toString() 
	{
		return String.format(Messages.POSITION, this.col, this.row);	//"(" + this.col + "," + this.row + ")";
	}
	
	public boolean equals(Position position)
	{ 		
	    return this.col == position.col && this.row == position.row; //if col and row are equals
	}
	
	public boolean isOutOfBoard() 
	{
		return this.isEastBorder() || this.isSouthBorder() || this.isWestBorder();
	}
	
	private boolean isEastBorder() { return this.col == Game.DIM_X; }
	
	private boolean isWestBorder() { return this.col < 0; }

	private boolean isSouthBorder() { return this.row == Game.DIM_Y; }
}
