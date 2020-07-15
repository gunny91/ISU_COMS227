package mini2;

import api.Selector;

/**
 * Selector that returns false if the current string is
 * within a javadoc comment, true otherwise.  Using a NonJavadocSelector
 * in the filter method of a StringList has the effect of removing all
 * javadoc comments.  Note that we are assuming that javadoc comments
 * always start and end on different lines, and that no executable
 * code ever appears on the same line as a javadoc comment.
 */
public class NonJavadocSelector implements Selector
{
	boolean inComment;
	public NonJavadocSelector()
	{
		inComment = false;
	}
	@Override
	public boolean select(String s)
	{
		if ( inComment )
		{
			if ( s.length() >= 2)
			{
				if ( s.substring(s.length()-2,s.length()).equals("*/")) { inComment = false;}
			}
			return false;
		}
		else
		{
			for ( int i = 0; i < s.length(); i++ )
			{
				if ( (s.length()-i) >= 3 )
				{
					if ( s.substring(i,i+3).equals("/**")) { inComment = true; return false;}
				}
			}
			return true;
		}
	}
  // TODO
}
