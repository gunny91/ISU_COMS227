
package hw4;

import java.util.ArrayList;
import java.util.List;

import api.AbstractGame;
import api.Position;

	/**
	 * MagicTetris implementation.
	 * @author GeonHee Cho
	 */
public class MagicTetris extends AbstractGame
{
	/**
	 * the score of the game
	 */
	private int score;
	
	/**
	 * the gravity status of this game
	 */
	private boolean gravity;
	
	
  /**
   * Constructs a game with the given width (columns) and height (rows).
   * This game will use a new instance of BasicGenerator to 
   * generate new shapes.
   * @param width
   *   width of the game grid (number of columns)
   * @param height
   *   height of the game grid (number of rows)
   */
  public MagicTetris(int width, int height)
  {
    super(width, height, new BasicGenerator());
    gravity=false;
    score=0;
  }

  @Override
  public List<Position> determinePositionsToCollapse()
  {
	  if(gravity) {  //check the gravity or not
		  return gravityPositions();
	  }
	  else {
			List<Position> result= new ArrayList<Position>();
			for(int i=0; i<this.getHeight();i++) {
				int magicCount=0;
				boolean isCompleted=true;
				for(int j=0; j<this.getWidth(); j++) {
					if(this.getBlock(i, j) != null) {
						if(this.getBlock(i, j).isMagic()) {
							magicCount++;
						}
					}
					else {
						isCompleted=false;
					}
				}	
				if(isCompleted) {
					//get entire row and add to result list
					for(int j=0; j<this.getWidth(); j++) {
						result.add(new Position(i,j));
					}
					if(magicCount == 0) {
						score= score + 1;
						
					}
					else {
						score+=(int) Math.pow(2, magicCount);
					}
					if(magicCount >= 3) {
						gravity=true;
					}
				}
			}
		    return result;
	  }
  }


  // this is the helper method that will help for gravity position.
  private List<Position> gravityPositions(){
	  List<Position> result= new ArrayList<Position>();
	  
	 for(int i=this.getWidth()-1; i>=0;i--) {
		  int iter=this.getHeight()-1;
		  boolean adding=false;
		  while(iter>=0) {
			if(this.getBlock(iter, i) !=null) {
				adding=true;
			}
			else if(this.getBlock(iter, i) == null && adding) {
				result.add(new Position (iter, i));
			}
			iter--;
		  }
	 }	
	gravity=false;
	return result;
  }
  
  
  @Override
  public int determineScore()
  {
    return score;
  }

}
