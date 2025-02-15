package hw4;

import java.awt.Color;

import api.Position;

	/**
	 * ThecL shape of the block class
	 * @author GeonHee Cho
	 *
	 */
public class IShape extends AbstractShape
{	
	
	/**
	 * the relative position of block
	 */
	private static Position[] relativePosition = {new Position(0,0),new Position(1,0),new Position(2,0)};

	
	/**
	 * Constructs an IShape with the given position and magic state.
	 * @param position position of this shape's center of rotation
	 * @param magic true if the this shape's first cell should be magic
	 * 
	 */
	public IShape(Position position, boolean magic)
	{
		super(position,relativePosition,magic,Color.CYAN);
	}
}
