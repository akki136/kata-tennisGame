package com.kata.tennis.ruleImpl;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisRule;
import com.kata.tennis.rule.AbstractTennisSetRule;

/**
 * {@link MatchStatusValidationRule} class used  to validate valid match and pipe to next phase of calculation
 * 
 *
 */
public class MatchValidationRule extends AbstractTennisRule {

	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId) throws TennisException {
		if(applyValidRunningMatchRule(runningMatches, matchId,currentPlayer,currentSetId)!=null)
		{
		return	applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
	
	  throw new TennisException("Match don't exist in System", null);
	}

	private  LinkedHashMap<Integer, MatchSet> applyValidRunningMatchRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId) throws TennisException
	{
		if(runningMatches.containsKey(matchId))
		{
			AbstractTennisSetRule abstractTennisSetRule=new  MatchSetScoreCalculationRule();
			abstractTennisSetRule.setNextRule(new MatchSetScoreCalculationRule());
			abstractTennisSetRule.setNextRule(new TieBreakerRule());
			abstractTennisSetRule.setNextRule(new SetWinValidationRule());
			abstractTennisSetRule.setNextRule(new SetThresholdReachedRule());
			abstractTennisSetRule.setNextRule(new SetWinWithThreshHoldRule());
			abstractTennisSetRule.applyRule(runningMatches, matchId, currentPlayer, currentSetId);
			return runningMatches;
		}
		
		return null;
	}

}
