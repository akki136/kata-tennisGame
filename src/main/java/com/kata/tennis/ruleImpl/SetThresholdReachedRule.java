package com.kata.tennis.ruleImpl;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisSetRule;
import com.kata.tennis.utility.Utility;

/**
 * {@link MatchSetScoreCalculationRule} class used  to Validate Threshold Condition where Player 1 or player 2 reach the Set score of 6 and the Player2 has a Set score of 4
 * 
 *
 */
public class SetThresholdReachedRule extends AbstractTennisSetRule {

	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId)
			throws TennisException {
		if(validateSetReachedThreshHold(runningMatches, matchId,currentPlayer,currentSetId)!=null)
		{
			return applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
		return runningMatches;
	}

	private LinkedHashMap<Integer, MatchSet> validateSetReachedThreshHold(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer, Integer currentSetId)
	{
		MatchSet matchSet=runningMatches.get(matchId);
		int p1Score = matchSet.getPlayer1WonSet().size();
		int p2Score = matchSet.getPlayer2WonSet().size();
		if(checkIfSetWonWithThreshHold(p1Score, p2Score)&&!matchSet.getThreshholdActivated())
		{
			System.out.println(this.getScore(matchSet.getPlayer1WonSet(), matchSet.getPlayer2WonSet()));
			GameStats gameStats=Utility.initializeMatch(Player.PLAYER1, Player.PLAYER2, matchSet.getLastSetNo());
			gameStats.setSetId(currentSetId+1);
			matchSet.setThreshholdActivated(true);
			matchSet.getSetStats().put(currentSetId+1, gameStats);
		}
			return runningMatches;
		
	}
	
}

