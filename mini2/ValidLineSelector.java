package mini2;

import api.Selector;

/**
 * Selector that returns false for a strings that are
 * empty, that are all whitespace, or whose first non-whitespace
 * character is the '#' character.
 */
public class ValidLineSelector implements Selector
{

	@Override
	public boolean select(String s) {
		if(s.trim().length() == 0 || s.trim().startsWith("#"))
		{
			return false;
		}
		return true;
	}
  // TODO
}
