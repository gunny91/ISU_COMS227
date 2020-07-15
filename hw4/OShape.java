package hw4;

import java.awt.Color;

import api.Position;

	/**
	 * The O shape of the block class
	 * @author GeonHee Cho
	 */
public class OShape extends AbstractShape
{

	/**
	 * the relative position of block
	 */
	private static Position[] relativePosition = {new Position(0,0),new Position(0,1),new Position(1,0),new Position(1,1)};
	
	/**
	 * Constructs an OShape with the given position and magic state.
	 * @param position position of this shape's upper left block
	 * @param magic true if the this shape's first cell should be magic
	 */
	public OShape(Position position, boolean magic)
	{
		super(position,relativePosition,magic,Color.YELLOW);
		
	}
	@Override
	public void transform()
	{
		
	}
	
}
