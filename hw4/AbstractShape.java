
package hw4;

import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;
import api.Shape;


	/**
	 * Abstract superclass for implementations of the Shape interface.
	 * @author GeonHee Cho
	 */
public abstract class AbstractShape implements Shape
{
	/**
	 *  The magic 
	 */
	private boolean isMagic;
	
	/**
	 * The cell
	 */
	private Cell[] cell;
	
	/**
	 *  The position
	 */
	private Position originPosition;
	
	/**
	 *  the relative position
	 */
	private Position[] relativePosition;
	
	
	/**
	 * the magic block 
	 */
	private int magicBlock = 0;
	
	/**
	 * the color of block
	 */
	private Color blockColor;
	
	/**
	 * The constructor for AbstractSahpe it can access every shape class to build their position
	 * @param position Position of the block
	 * @param relPo relative position of block
	 * @param magic the magic status of the block.
	 * @param color the color of the block
	 */
	protected AbstractShape ( Position position, Position[] relPo, boolean magic, Color color)
	{
		isMagic = magic;
		originPosition = new Position(position.row(),position.col());
		relativePosition = new Position[relPo.length];
		blockColor = color;
		cell= new Cell[relPo.length];
		
		for ( int i = 0; i < relPo.length; i++)
		{
			boolean tempMagic;
			if ( magic && i == magicBlock )
			{ 
				tempMagic = true; 
			}
			else 
			{ 
				tempMagic = false;
			}
			
			relativePosition[i] = relPo[i];
			
			//simply make a cell to use Cell(Block b, Position position) method in Cell class.
			cell[i] = new Cell(new Block(blockColor,tempMagic),new Position(originPosition.row()+relativePosition[i].row(),originPosition.col()+relativePosition[i].col()));
		}
	}

	
	@Override
	public Cell[] getCells()
	{
		Cell[] temp = new Cell[cell.length];
		for ( int i = 0; i < cell.length; i++)
		{
			temp[i] = new Cell(cell[i]);
		}
		
		return temp;
	}


	@Override
	public void shiftDown()
	{
		for ( int i = 0; i < cell.length; i++)
		{
			cell[i].setRow(cell[i].getRow()+1);
		}
		originPosition.setRow(originPosition.row()+1);
	}

	
	@Override
	public void shiftLeft()
	{
		
		for ( int i = 0; i < cell.length; i++)
		{
			cell[i].setCol(cell[i].getCol()-1);
		}
		originPosition.setCol(originPosition.col()-1);
	}


	@Override
	public void shiftRight()
	{
		for ( int i = 0; i < cell.length; i++)
		{
			cell[i].setCol(cell[i].getCol()+1);
		}
		originPosition.setCol(originPosition.col()+1);
	}


	@Override
	public void transform()
	{
		int oriRow = originPosition.row();
		int oriCol = originPosition.col();
		for ( int i = 0; i < cell.length; i++)
		{
			int tempRow = cell[i].getRow() - oriRow;
			int tempCol = cell[i].getCol() - oriCol;

			cell[i].setRow( oriRow - tempCol);
			cell[i].setCol( oriCol + tempRow);
		}
	}

	@Override
	public void cycle()
	{
		if ( isMagic )
		{
			cell[magicBlock] = new Cell(new Block(this.blockColor,false),new Position(cell[magicBlock].getRow(), cell[magicBlock].getCol()));
			magicBlock = (magicBlock+1) % cell.length;  
			cell[magicBlock] = new Cell(new Block(this.blockColor,true),new Position(cell[magicBlock].getRow(), cell[magicBlock].getCol()));
		}
	}

  public Shape clone()
  {
    try
    {
      AbstractShape s = (AbstractShape) super.clone();
      //go into the cell and copy all of that information into a new cell and then create 
      //the abstract shape from that information
      
      s.originPosition = new Position(this.originPosition.row(),this.originPosition.col());
      s.cell = new Cell[this.cell.length];
      for ( int i = 0; i < this.cell.length; i++ )
      {
    	  s.cell[i] = new Cell(this.cell[i]);
	  }
      
      return s;
    }
    catch (CloneNotSupportedException e)
    {
      // can't happen
      return null;
    }
  }
  
  	//Below are the helper methods for SZShape. I made those helper method to use in SZ class
  	protected boolean isBlockMagic( int i ) { return ( i == magicBlock); }
  	protected int getCellNum() { return cell.length; }
  	protected int getOriginCol() { return originPosition.col(); }
  	protected int getCol( int i ) { return cell[i].getCol(); }
  	protected void setBlock( int i, Block block) { cell[i].setBlock(block); }
  	protected void setCol( int i, int a ) { cell[i].setCol(a); }
}
