package com.kata.tennis.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.executionengine.GameFactory;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.managescore.PlayerMatchStat;
import com.kata.tennis.managescore.TennisGame;
import com.kata.tennis.rule.AbstractTennisRule;
import com.kata.tennis.rule.AbstractTennisSetRule;
import com.kata.tennis.ruleImpl.MatchSetScoreCalculationRule;
import com.kata.tennis.ruleImpl.MatchStatusValidationRule;
import com.kata.tennis.ruleImpl.MatchValidationRule;
import com.kata.tennis.ruleImpl.SetThresholdReachedRule;
import com.kata.tennis.ruleImpl.SetWinValidationRule;
import com.kata.tennis.ruleImpl.SetWinWithThreshHoldRule;
import com.kata.tennis.ruleImpl.TieBreakerRule;
import com.kata.tennis.utility.Utility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akki1
 * Game Play is Used for processing Tennis Match Set Logics like Tie-Break
 *
 */

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TennisGameController  {

	static Utility utility=Utility.getInstance();
	private GameFactory game;
	static LinkedHashMap<Integer ,MatchSet> runningGames=new LinkedHashMap<>();
	public static TennisGameController startPlay(Player p1, Player p2,int set,int game)
	{
		runningGames=new LinkedHashMap<>();

		return startSet(p1, p2,set,game);

	}
	
	/**
	 * startSet() is used to create/start new set in Tennis Match
	 * 
	 *
	 */
	
	public static TennisGameController startSet(Player p1, Player p2,int matchId, int no) {
		
		MatchSet matchStats=runningGames.get(matchId);
		if(matchStats==null)
		{
			matchStats=new MatchSet();
			matchStats.setBoard(ScoreBoard.ZERO);
			GameStats gameStats=  Utility.initializeMatch(p1, p2, no);
			gameStats.setSetId(no);
			matchStats.getSetStats().put(no,gameStats);
			runningGames.put(matchId, matchStats);
		}
		GameStats gameStats=	matchStats.getSetStats().get(no);
		if(gameStats==null)
		{
			gameStats=  Utility.initializeMatch(p1, p2, no);
			gameStats.setSetId(no);
			matchStats.getSetStats().put(no,gameStats);
		}
		return new TennisGameController(new TennisGame(matchStats.getSetStats().get(no)));
	}
	

	/**
	 * playTennis() is used to start play of Tennis set
	 * 
	 *
	 */
	public  LinkedHashMap<Integer ,MatchSet> playTennis(Player player,int matchId,int setId) throws TennisException 
	{
		
		MatchSet matchSet=runningGames.get(matchId);
		if(matchSet.getWinner()!=Player.UNKNOWN)
		{
			System.out.println("Set Already won by "+matchSet.getWinner());
			return runningGames;
		}
		try
		{
			game=game.process(player);
		}catch(TennisException ex)
		{
			System.out.println(ex.getMessage());
		}
		AbstractTennisRule abstractTennisRule=new MatchStatusValidationRule();
		abstractTennisRule.setNextRule(new MatchValidationRule());
		abstractTennisRule.applyRule(runningGames, matchId,player,setId);
		return runningGames;

	}

	@Override
	public String toString() {
		return "GamePlay [game=" + game + "]";
	}


}
