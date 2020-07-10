package hw2;

	/**
	 * Simplified model of American baseball. A client repeatedly calls one of the three methods 
	 * pitch(), pitchWithHit(), or pitchWithHitAndOut() until the game is over. Whenever there are 
	 * three outs, the teams switch. After the specified number of innings, the game is over.
	 *Rules are highly simplified in comparison to the "real" game of baseball. In particular, 
	 *the behavior of base running players is deterministic. The behavior of a player on base in 
	 *pitchWithHit() or pitchWithHitAndOut() is always to advance one base on a single, advance two
	 *bases on a double, and so on. Likewise, in case of a walk, all players just advance one base.
	 *Runners cannot "steal" and cannot independently decide whether to run based on the defense 
	 *team behavior.
	 * @author GeonHee Cho
	 *
	 */
public class CS227Baseball {

	/**
	 * Constant indicating that a pitch results in a ball.
	 */
	public static final int BALL = 0;

	/**
	 * Constant indicating that a pitch results in a strike.
	 */
	public static final int STRIKE = 1;

	/**
	 * Constant indicating that a pitch results in an out from a fly ball.
	 */
	public static final int POP_FLY = 2;

	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int MAX_STRIKES = 3;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int MAX_BALLS = 4;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int MAX_OUTS = 3;

	/**
	 *  given number of innings 
	 */
	private int numberInnings;
	
	/**
	 *  true if the game is over, false otherwise
	 */
	private boolean gameIsOver;
	/**
	 *  whether there is a player on first base
	 */
	private boolean playerOnFirst;
	/**
	 * whether there is a player on second base
	 */
	private boolean playerOnSecond;
	/**
	 * whether there is a player on third base
	 */
	private boolean playerOnThird;
	/**
	 *  the current inning
	 */
	private int currentInnings;
	/**
	 * the number of strikes for the current batter
	 */
	private int getStrikes;
	/**
	 * the number of balls for the current batter
	 */
	private int getBalls;
	/**
	 * the number of outs for the current batter
	 */
	private int getOuts;
	/**
	 *  if it's the first half of the inning (team 0 is at bat
	 */
	private boolean isTop;
	/**
	 * the score when team0 got it
	 */
	private int getTeam0Scored;
	/**
	 * the score when team1 got it
	 */
	private int getTeam1Scored;

	/**
	 * Constructs a game that has the given number of innings and starts at the top of inning 1
	 * @param givenNumInnings
	 */
	public CS227Baseball(int givenNumInnings) {

		numberInnings = givenNumInnings;
		currentInnings = 1;
		gameIsOver = false;
		isTop = true;
	}

	/**
	 * Returns true if the game is over, false otherwise. 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver() 
	{
		return gameIsOver;
	}
	
	/**
	 * Returns whether there is a player on first base.
	 * @return true if there is a player on first, false otherwise
	 */
	public boolean playerOnFirst() 
	{
		if (playerOnFirst) 
		{
			return true;
		}
		return false;
	}
	/**
	 * Returns whether there is a player on second base.
	 * @return true if there is a player on second, false otherwise
	 */
	public boolean playerOnSecond() 
	{
		if (playerOnSecond) 
		{
			return true;
		}
		return false;
	}
	/**
	 * Returns whether there is a player on third base.
	 * @return true if there is a player on third, false otherwise
	 */
	public boolean playerOnThird() 
	{
		if (playerOnThird) 
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns the current inning. Innings are numbered starting at 1. When the
	 * game is over, this method returns the games total number of innings, plus
	 * one.
	 * @return current inning, or the number of innings plus one if the game is over
	 */
	public int getInning() 
	{

		if (gameIsOver) 
		{
			return currentInnings;
		}
		else
			return currentInnings;

	}

	/**
	 * Returns true if it's the first half of the inning (team 0 is at bat).
	 * @return true if it's the first half of the inning, false otherwise
	 */
	public boolean isTop()
	{
		return isTop;
	}

	/**
	 * Returns the number of outs for the current batter.
	 * @return current number of outs
	 */
	public int getOuts() 
	{
		return getOuts;
	}

	/**
	 * Returns the number of strikes for the current batter.
	 * @return current number of strikes
	 */
	public int getStrikes()
	{
		return getStrikes;
	}

	/**
	 * Returns the number of balls for the current batter. 
	 * @return current number of balls
	 */
	public int getBalls() 
	{
		return getBalls;
	}

	/**
	 * Returns the score of the indicated team, where an argument of true
	 * indicates team 0 (batting in the top of the inning) and an argument of
	 * false indicates team 1 (batting in the bottom of the inning). If game is
	 * a CS227Baseball object, the call game.getScore(game.isTop()) always
	 * returns the score for the team that is currently at bat. 
	 * @param team0 true to get team 0's score, false for team 1's score
	 * @return score for the indicated team
	 */
	public int getScore(boolean team0) 
	{

		if (team0) 
		{
			return getTeam0Scored;
		}
		return getTeam1Scored;
	}

	/**
	 * Pitch not resulting in a hit. The argument for outcome should be one of
	 * the three predefined constants BALL, STRIKE, or POP_FLY. A detailed
	 * description of these values is described in the section entitled
	 * "Balls, strikes, and outs" in the assignment pdf. This method may update
	 * the count of balls, strikes, and/or outs, and may cause existing players
	 * on base to advance in the case of a walk, and causes the teams to switch
	 * if the number of outs reaches MAX_OUTS. This method does nothing if the
	 * game is over or if the argument is not one of the values BALL, STRIKE, or
	 * POP_FLY.
	 * @param outcome one of the three constants STRIKE, BALL, or POP_FLY
	 */
	public void pitch(int outcome) 
	{
		
		if(gameIsOver)
		{
			return;
		}
	
		if (outcome == STRIKE) 
		{
			getStrikes++;
		} 
		else if (outcome == BALL) // ball
		{
			getBalls += BALL + 1;
			if (getBalls == MAX_BALLS) 
			{
				getBalls = BALL;
				getStrikes = 0;
				advanceRunners(true); // helper method
			}
		} 
		else if (outcome == POP_FLY) // Pop fly, if it is poped fly, the strike will reset 0. out will increase 1.
		{
			getStrikes = 0;
			getOuts += 1;
		}

		if (getStrikes >= MAX_STRIKES) // If it is Max strike, then pluse outs 1, and reset the strikes count.
		{
			getOuts += 1;
			getStrikes = 0;
		}
		if (getOuts >= MAX_OUTS) //if the outs will be greater than equals to maximum strike(3) value, then change the inning.
		{
			changeInning(); //helper method to make inning change.
		}

	}

	/**
	 * Pitch resulting in a hit where no player is out. The argument for
	 * numBases should be 1, 2, 3, or 4, indicating a single, double, triple, or
	 * home run. The batter and all players currently on base must advance the
	 * given number of bases (possibly resulting in one or more runs). This
	 * method does nothing if the game is over or if the argument is not one of
	 * the values 1, 2, 3, or 4.
	 * @param numBases number of bases that the batter and all players on base will advance
	 */
	public void pitchWithHit(int numBases) 
	{
		
		
		getStrikes = 0; //Reset strike count = 0
		getBalls = 0; //Reset ball count =0 
		if (gameIsOver)
		{
			return;
		}

		if (numBases == 4) 
		{
			advanceRunners(true); // helper method
			advanceRunners(false);
			advanceRunners(false);
			advanceRunners(false);

		}

		else if (numBases == 3)
		{
			advanceRunners(true);
			advanceRunners(false);
			advanceRunners(false);

		} else if (numBases == 2) 
		{
			advanceRunners(true);
			advanceRunners(false);

		}

		else if (numBases == 1) 
		{
			advanceRunners(true);

		}
		if(getOuts >= MAX_OUTS)
		{
			changeInning();
		}
	}

	/**
	 * 
	 * @param numBases
	 * @param whichBaseFielded
	 */

	/**
	 * Pitch resulting in a hit and a possible out. Advancement of runners is
	 * the same as pitchWithHit() except that it might cause a player to be out.
	 * The argument for whichBaseFielded indicates the base to which the ball is
	 * fielded (1, 2, 3, or 4). If the batter or one of the players on base
	 * arrives at the base whichBaseFielded after advancing exactly numBases,
	 * then that player is out. That is, we assume that the time it takes to
	 * field the ball to the indicated base is the same as the time it takes the
	 * runners to advance numBase bases. If a player is put out when advancing
	 * to home, that run is not counted. (Note that at most one out is possible,
	 * i.e. there are no double or triple plays. Also, there are no special
	 * exceptions for counting runs when it's the third out.) This method does
	 * nothing if the game is over or if either argument is not one of the
	 * values 1, 2, 3, or 4.
	 * @param numBases number of bases that the batter and all players on base will advance
	 * @param whichBaseFielded the base to which the defense fields the ball
	 */
	public void pitchWithHitAndOut(int numBases, int whichBaseFielded) 
	{
		getStrikes=0;
		getBalls=0;

		if(whichBaseFielded==4)
		{
			if(numBases == 4)
			{
				getOuts+=1;
				advanceRunners(false); //helper method
				advanceRunners(false);
				advanceRunners(false);
				if(getOuts >= MAX_OUTS)
				{
					changeInning();
				}
				return;
		}
			else if(playerOnFirst && numBases > 2)
			{
				playerOnFirst=false;
				getOuts+=1;
			}
			else if(playerOnSecond && numBases > 1)
			{
				playerOnSecond=false;
				getOuts+=1;
			}
			else if(playerOnThird)
			{
				playerOnThird=false;
				getOuts+=1;
			}	
		}
		pitchWithHit(numBases);
		if (playerOnFirst && whichBaseFielded == 1) 
		{
			playerOnFirst = false;
			getOuts += 1;
			// Player_On_Second = false;
		} else if (playerOnSecond && whichBaseFielded == 2) 
		{
			playerOnSecond = false;
			getOuts += 1;
		}

		else if (playerOnThird && whichBaseFielded == 3) 
		{
			playerOnThird = false;
			getOuts += 1;
		}

		if(getOuts >= MAX_OUTS)
		{
			changeInning();
		}
	}

	/**
	 * This is helper method that I made. I made it to reduce my codes. The changeInning method is alot 
	 * using in my code. 
	 */
	private void changeInning()
	{
		if(isTop())			// if it is top, then reset out, strikes, ball counts, and change the team0 to team1. Also, all bases are empty.
		{			
			getOuts = 0;
			getStrikes = 0;
			getBalls = 0;
			isTop=false;
			playerOnFirst=false;
			playerOnSecond=false;
			playerOnThird=false;
		}
		else
		{
			getOuts = 0;
			getStrikes = 0;
			getBalls = 0;
			currentInnings+=1;
			isTop=true;
			playerOnFirst=false;
			playerOnSecond=false;
			playerOnThird=false;
			
			if (currentInnings > numberInnings)
			{
				
				gameIsOver = true;

			}

		}
	}
	/**
	 * Advances all players on base by one base, updating the score if there is
	 * currently a player on third. If the argument newPlayerOnFirst is true, a
	 * player (i.e. the batter) is placed on first base.
	 * @param newPlayerOnFirst if true, there will be a runner on first after this method
	 *            executes
	 */
	public void advanceRunners(boolean newPlayerOnFirst) 
	{

		if (playerOnThird == true) // if runner on the third base, and advanceRunners method is true, then check the T or B case
		{
			playerOnThird = false;
			if (isTop) 
				getTeam0Scored += 1;
			else
				getTeam1Scored += 1;
		}
		if (playerOnSecond == true) // if the player on the second base, then the runner will move to third base. Such that second base will be empty.
		{
			playerOnSecond = false;
			playerOnThird = true;
		}
		if (playerOnFirst == true) //Same concept of upper logic.
		{
			playerOnSecond = true;
			playerOnFirst = false;
		}
		if (newPlayerOnFirst == true) 
		{
			playerOnFirst = true;
		}

	}

	/**
	 * Returns a three-character string representing the players on base, in the
	 * order first, second, and third, where 'X' indicates a player is present
	 * and 'o' indicates no player. For example, the string "XXo" means that
	 * there are players on first and second but not on third.
	 * @return three-character string showing players on base
	 */
	public String getBases()
	{
		return (playerOnFirst() ? "X" : "o") + (playerOnSecond() ? "X" : "o")
				+ (playerOnThird() ? "X" : "o");
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is: ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs: The first
	 * three characters represent the players on base as returned by the
	 * <code>getBases()</code> method. The 'T' after the inning number indicates
	 * it's the top of the inning, and a 'B' would indicate the bottom
	 * @return one-line string representation of the game state
	 */
	public String toString() 
	{
		String bases = getBases();
		String topOrBottom = (isTop() ? "T" : "B");
		String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, getInning(), topOrBottom,
				getScore(true), getScore(false), getBalls(), getStrikes(),
				getOuts());
	}

	/**
	 * Returns a multi-line string representation of the current game state. The
	 * format is: o - o Inning:1 (T) | | Score:0-0 o - H Balls:0 Strikes:0
	 * Outs:0 The 'T' after the inning number indicates it's the top of the
	 * inning, and a 'B' would indicate the bottom.
	 * @return multi-line string representation of current game state
	 */
	public String toDisplayString() {
		String firstChar = (playerOnFirst() ? "X" : "o");
		String secondChar = (playerOnSecond() ? "X" : "o");
		String thirdChar = (playerOnThird() ? "X" : "o");
		String topOrBottom = (isTop() ? "T" : "B");
		String firstLine = String.format("%s - %s    Inning:%d (%s)\n",
				secondChar, firstChar, getInning(), topOrBottom);
		String secondLine = String.format("|   |    Score:%d-%d\n",
				getScore(true), getScore(false));
		String thirdLine = String.format(
				"%s - H    Balls:%d Strikes:%d Outs:%d\n", thirdChar,
				getBalls(), getStrikes(), getOuts());

		return firstLine + secondLine + thirdLine;
	}

}