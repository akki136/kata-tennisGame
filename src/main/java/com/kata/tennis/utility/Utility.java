package com.kata.tennis.utility;

import java.util.UUID;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.PlayerMatchStat;

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
	
	
	/**
	 * initializeMatch() is used to initialize Game in a Set
	 * 
	 *
	 */
	public static GameStats initializeMatch(Player p1, Player p2, int no) {
		PlayerMatchStat player1 = createPlayerStat(p1);
		PlayerMatchStat player2= createPlayerStat(p2);
		GameStats gameStats=new GameStats(player1,player2,UUID.randomUUID().toString());
		return gameStats;
	}

	public static PlayerMatchStat createPlayerStat(Player p1) {
		PlayerMatchStat player1=new PlayerMatchStat();
		player1.setPlayer(p1);
		player1.setPlayerPoints(ScoreBoard.ZERO);
		player1.setScore(0);
		player1.addScoreToList(utility.getValue(player1.getScore()));
		return player1;
	}
}
