package com.kata.tennis.rule;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;

public abstract class AbstractTennisRule implements TennisGameRuleI {
	
	protected TennisGameRuleI nextRule;
	
	public void setNextRule(TennisGameRuleI tennisRuleI)
	{
		this.nextRule=tennisRuleI;
	}


	public LinkedHashMap<Integer, MatchSet> applynextRuleIfExist(LinkedHashMap<Integer, MatchSet> runningMatches,Integer matchId, Player currentPlayer, Integer currentSetId) throws TennisException {
	if(this.nextRule!=null)
	{
		return this.nextRule.applyRule(runningMatches,matchId,currentPlayer,currentSetId);
	}
	return runningMatches;
	}

}
