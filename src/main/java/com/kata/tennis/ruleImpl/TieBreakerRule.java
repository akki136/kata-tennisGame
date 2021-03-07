package com.kata.tennis.ruleImpl;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisSetRule;

/**
 * {@link TieBreakerRule} class used  to Validate Tie Breaker Condition
 * 
 *
 */

public class TieBreakerRule extends  AbstractTennisSetRule{

	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId)
			throws TennisException {
		
		if(validateTieBreak(runningMatches, matchId)!=null)
		{
			return applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
		return runningMatches;
	}
	
	
	private LinkedHashMap<Integer, MatchSet> validateTieBreak(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId)
	{
		MatchSet matchSet=runningMatches.get(matchId);
		int p1Score = matchSet.getPlayer1WonSet().size();
		int p2Score = matchSet.getPlayer2WonSet().size();
		if(checkIfTieBreak(p1Score, p2Score))
		{
			matchSet.setBoard(ScoreBoard.TIEBREAK);
			System.out.println("Tie-Brack Occured "+matchSet.getWinner());
			System.out.println(this.getScore(matchSet.getPlayer1WonSet(), matchSet.getPlayer2WonSet()));
			matchSet.setIsTieBreak(true);
			return null;
		}
			return runningMatches;
		
	}
}
