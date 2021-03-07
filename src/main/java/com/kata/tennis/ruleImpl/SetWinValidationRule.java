package com.kata.tennis.ruleImpl;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisSetRule;

/**
 * {@link SetWinValidationRule} class used  to Validate If Any player won the test
 * 
 *
 */
public class SetWinValidationRule extends AbstractTennisSetRule {

	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId)
			throws TennisException {
		if(validateSetWin(runningMatches, matchId,currentPlayer)!=null)
		{
			return applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
		return runningMatches;
	}

	private LinkedHashMap<Integer, MatchSet> validateSetWin(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer)
	{
		MatchSet matchSet=runningMatches.get(matchId);
		int p1Score = matchSet.getPlayer1WonSet().size();
		int p2Score = matchSet.getPlayer2WonSet().size();
		if(checkIfSetWon(p1Score, p2Score))
		{
			matchSet.setBoard(ScoreBoard.WINSET);
			matchSet.setWinner(currentPlayer);
			System.out.println(this.getScore(matchSet.getPlayer1WonSet(), matchSet.getPlayer2WonSet()));
			matchSet.setIsTieBreak(true);
			return null;
		}
			return runningMatches;
		
	}
	
}
