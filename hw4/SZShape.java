package hw4;

import java.awt.Color;

import api.Block;
import api.Position;

	/**
	 * The SZ shape of the block class
	 * @author GeonHee Cho
	 *
	 */

public class SZShape extends AbstractShape 
{
	/**
	 * the relative position of block
	 */
	private static Position[] relativePosition = {new Position(0,0),new Position(1,0),new Position(1,1),new Position(2,1)};
	
	/**
	 * the 
	 */
	private int fliped =-1;
	
	/**
	 * the magic state
	 */
	private boolean magic;
	
	/**
	 * Constructs an SZShape with the given position and magic state.
	 * @param position position of this shape's upper left block
	 * @param magic true if the this shape's first cell should be magic
	 */ 
	public SZShape(Position position, boolean magic)
	{
		super(position,relativePosition,magic,Color.GREEN );
		this.magic=magic;
	}


	@Override
	public void transform() 
	{
		for ( int i = 0; i < this.getCellNum(); i ++)
		{
			
			int oriCol = this.getOriginCol();
			int tempCol = relativePosition[i].col()*fliped;
			this.setCol(i,oriCol+tempCol);
			if ( fliped == 1 ) { 
				this.setBlock(i, new Block(Color.GREEN,this.isBlockMagic(i)&& magic));
			}
			if ( fliped ==-1 ) { 
				this.setBlock(i, new Block(Color.RED,this.isBlockMagic(i)&& magic));
				this.setCol(i,oriCol+tempCol+1);
			}
			if( i == (this.getCellNum()-1)) { 
				fliped *= -1;
			}
		}
	}
}
