package hw4;

import java.awt.Color;

import api.Position;

	/**
	 *  The J shape of the block class
	 *  @author GeonHee Cho
	 */
public class JShape extends AbstractShape
{

	/**
	 * the relative position of block
	 */
	private static Position[] relativePosition = {new Position(0,-1),new Position(1,-1),new Position(1,0),new Position(1,1)};
	
	/**
	 * Constructs a JShape with the given position and magic state.
	 * @param position position of this shape's center of rotation
	 * @param magic true if the this shape's first cell should be magic
	 */
	public JShape(Position position, boolean magic)
	{
		super(position,relativePosition,magic,Color.BLUE);
	}
	
}
