package mini2;

import api.Combiner;

/**
 * Combiner that appends the first letter of a string onto
 * the accumulator.  If the string is empty, returns the 
 * accumulator.
 */
public class FirstLetterCombiner implements Combiner<String>
{
	@Override
	public String combine(String obj, String s)
	{
		if ( s.equals("")) { return obj; }
		else
		{
			return obj + s.charAt(0);
		}
	}
  // TODO
	
}
