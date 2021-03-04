package com.kata.tennis.utility;

import com.kata.tennis.enums.ScoreBoard;

/**
 * Utility  class contains generic methods used for data conversion
 * 
 *
 */

public final class Utility {
	private Utility() {
	}
	private static Utility utility=null;
	public static Utility getInstance()
	{
		if(utility==null)
		{
			utility=new Utility();
		}
		return utility;
	}
	
	
	public String getValue(int index)
	{
		
	switch(index)
	{

	case 0:
	return	ScoreBoard.ZERO.score;
	case 1:
		return	ScoreBoard.FIFTEEN.score;
	case 2:
		return	ScoreBoard.THIRTY.score;
	case 3:
		return	ScoreBoard.FORTY.score;
	case 4:
		return	ScoreBoard.ADVANTAGE.score;

	default :
		return ScoreBoard.WIN.score;
		
	}
	}
	
	public String getSetValue(int index)
	{
		
	switch(index)
	{
	case 0:
		return	ScoreBoard.ZERO.score;
	case 6:
	return	ScoreBoard.ONE.score;
	case 7:
		return	ScoreBoard.TWO.score;
	case 8:
		return	ScoreBoard.THREE.score;
	case 9:
		return	ScoreBoard.FOUR.score;
	case 10:
		return	ScoreBoard.FIVE.score;
	case 11:
		return	ScoreBoard.SIX.score;
	

	default :
		return ScoreBoard.SEVEN.score;
		}
	}
	public ScoreBoard getEnum(int index)
	{
		
	switch(index)
	{

	case 0:
	return	ScoreBoard.ZERO;
	case 1:
		return	ScoreBoard.FIFTEEN;
	case 2:
		return	ScoreBoard.THIRTY;
	case 3:
		return	ScoreBoard.FORTY;
	case 4:
		return	ScoreBoard.ADVANTAGE;

	default :
		return ScoreBoard.WIN;
		
	}
	}	

	public ScoreBoard getSetEnum(int index)
	{
		
	switch(index)
	{
	case 0:
		return	ScoreBoard.ZERO;
	case 6:
	return	ScoreBoard.ONE;
	case 7:
		return	ScoreBoard.TWO;
	case 8:
		return	ScoreBoard.THREE;
	case 9:
		return	ScoreBoard.FOUR;
	case 10:
		return	ScoreBoard.FIVE;
	case 11:
		return	ScoreBoard.SIX;
	

	default :
		return ScoreBoard.SEVEN;
		
	}
	}
}
