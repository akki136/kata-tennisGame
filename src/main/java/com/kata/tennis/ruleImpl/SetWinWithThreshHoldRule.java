package com.kata.tennis.ruleImpl;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisSetRule;

/**
 * {@link SetWinWithThreshHoldRule} class used  to Validate Win after Threshold Condition where Player 1 or player 2 2 win a Game and reach the Set score of 6 and the Player2 has a Set score of 4
 * 
 *
 */
public class SetWinWithThreshHoldRule extends AbstractTennisSetRule {

	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId)
			throws TennisException {
		if(validateSetWinWithThreshHold(runningMatches, matchId,currentPlayer)!=null)
		{
			return applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
		return runningMatches;
	}

	private LinkedHashMap<Integer, MatchSet> validateSetWinWithThreshHold(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer)
	{
		MatchSet matchSet=runningMatches.get(matchId);
		int p1Score = matchSet.getPlayer1WonSet().size();
		int p2Score = matchSet.getPlayer2WonSet().size();
		if(checkIfSetWonWithThreshHold(p1Score, p2Score)&&!matchSet.getIsTieBreak())
		{
			matchSet.setBoard(ScoreBoard.WINSET);
			matchSet.setWinner(currentPlayer);
			System.out.println(this.getScore(matchSet.getPlayer1WonSet(), matchSet.getPlayer2WonSet()));
		}
			return runningMatches;
		
	}
	
}
