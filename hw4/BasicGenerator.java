
package hw4;

import java.util.Random;

import api.Generator;
import api.Position;
import api.Shape;


	/**
	 * Generator for Shape objects in MagicTetris.  All six shapes
	 * are equally likely, and the generated shape is magic with
	 * 20% probability.
	 * @author GeonHee Cho
	 */
public class BasicGenerator implements Generator
{
  @Override
  public Shape getNext(int width)
  {
	  
	  Random r= new Random();
	  int shape= r.nextInt(6);
	  
	  //magic calculation
	  Random r2= new Random();
	  int m= r2.nextInt(100);
	  boolean magic;
	
	  if(m<20) { //20% of magic blocks
		  magic=true;
	  }
	  else {
		  magic=false;
	  }
	  
	  
	  //IShape
	  if(shape==0) {
		  return new IShape(new Position(-2, (width / 2)), magic);
	  }
	  //LShape
	  else if(shape==1) {
		  return new LShape(new Position(-1, (width / 2)+1), magic);
	  }
	  //OShape
	  else if(shape==2) {
		  return new OShape(new Position(-1, width / 2), magic);
	  }
	  //SZShape
	  else if(shape==3) {
		  return new SZShape(new Position(-1, width / 2), magic);
	  }
	  //TShape
	  else if(shape==4) {
		  return new TShape(new Position(0, width / 2), magic);
	  }
	  //JShape
	  else {
		  return new JShape(new Position(-1, width / 2), magic);
	  }
	    

  }
}
