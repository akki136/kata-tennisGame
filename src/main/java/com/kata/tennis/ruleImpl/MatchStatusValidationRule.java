package com.kata.tennis.ruleImpl;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisRule;
import com.kata.tennis.rule.AbstractTennisSetRule;

/**
 * {@link MatchStatusValidationRule} class used  to Validate Match Status as its on or already completed
 * 
 *
 */
public class MatchStatusValidationRule extends AbstractTennisRule {

	Player player=Player.UNKNOWN;
	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId) throws TennisException {
		if( applyMatchStatusRule(runningMatches, matchId,currentPlayer,currentSetId)!=null)
		{
			return applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
		throw new TennisException("Set Already won by "+player, null);
	}

	private LinkedHashMap<Integer, MatchSet> applyMatchStatusRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId) throws TennisException
	{
		MatchSet matchSet=	runningMatches.get(matchId);
		if(matchSet.getWinner()!=Player.UNKNOWN)
		{
			System.out.println("Set Already won by "+matchSet.getWinner());
			player=matchSet.getWinner();
			return null;
		}
		return runningMatches;
	}

}
