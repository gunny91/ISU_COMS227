package mini2;

import api.Selector;

/**
 * Selector whose <code>select</code> method returns false for strings whose first non-whitespace
 * text is "//", and true for all other strings.
 */
public class NonLineCommentSelector implements Selector
{

	@Override
	public boolean select(String s)
	{
		
		if(s.trim().startsWith("//"))
		{
			return false;
		}
		return true;
//		for ( int i = 0; i < s.length(); i++)
//		{
//			if ( (s.length()-i) >= 2)
//			{
//				if ( s.substring(i,i+2).equals("//")) { return false; }
//			}
//		}
//		return true;
	}
}