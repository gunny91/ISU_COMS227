package mini2;

import api.Combiner;

/**
 * Combiner whose <code>combine</code> method, given an Integer n and a string, 
 * returns n if the string is a blank line or a line containing
 * a single curly brace; otherwise returns n + 1.  (A curly brace on a line whose
 * only other text is an end-of-line comment is treated as just a curly brace.)
 * Using a SlocCounter in the reduce method has the general effect of counting 
 * the number of "source lines of code" that are actual program statements,
 * assuming that line comments and javadoc comments have already been
 * filtered out.
 */
public class SlocCounter implements Combiner<Integer>
{

	@Override
	public Integer combine(Integer obj, String s)
	{
		int temp = obj;
		
		if(s.trim().length() == 0 || s.trim().contains("//")|| (s.trim().length()==1 && (s.trim().startsWith("{") ||(s.trim().startsWith("}"))))) 
			{
				return temp;
				}
			else
			{
				return temp +1;
			}
	}
//	public class SlocCounter implements Combiner<Integer>
//	{
//
//		boolean brace = false;
//		for ( int i = 0; i < s.length(); i ++)
//		{
//			if ( s.charAt(i) == '{' || s.charAt(i) == '}' )
//			{
//				if ( brace ) { return obj+1; }
//				else { brace = true; }
//			}
//			
//			if ( s.charAt(i) != ' ' && s.charAt(i) != '{' && s.charAt(i) != '}' ) { return obj+1; }
//			if ( i == s.length()-1 ) { return obj; }
//		}
//		return obj;
//	}
	
		
		
	
}