package mini2;

import api.Transformation;

/**
 * Transformation whose <code>apply</code> method prepends a line number to each string.  
 * For a newly created LineNumberer, the line numbers start at 1 and increase on
 * each successive call.  The line number is left-justified in a field 5 spaces wide.
 */
public class LineNumberer implements Transformation
{
	private int line;
	public LineNumberer ()
	{
		line = 0;
	}
	@Override
	public String apply(String s)
	{
		line++;
		String temp = ""+line;
		
		for ( int i = 0; i < 5-((""+line).length()); i++ )
		{
			temp += " ";
		}
		return temp + s;
	}
  // TODO
}