package com.kata.tennis.ruleImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.DeuceGame;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.rule.AbstractTennisSetRule;


/**
 * {@link MatchSetScoreCalculationRule} class used  to Calculate Set Score
 * 
 *
 */
public class MatchSetScoreCalculationRule extends AbstractTennisSetRule {

	@Override
	public LinkedHashMap<Integer, MatchSet> applyRule(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId,Player currentPlayer,Integer currentSetId)
			throws TennisException {
		
		if(calculateMatchSetScore(runningMatches, matchId)!=null)
		{
			return applynextRuleIfExist(runningMatches, matchId,currentPlayer,currentSetId);
		}
		return runningMatches;
	}
	
	private  LinkedHashMap<Integer, MatchSet> calculateMatchSetScore(LinkedHashMap<Integer, MatchSet> runningMatches, Integer matchId)
	{
	MatchSet matchSet=runningMatches.get(matchId);
	List<String> player1WonSet=new ArrayList<>();
	List<String> player2WonSet=new ArrayList<>();
		for(Integer key:matchSet.getSetStats().keySet())
		{
			GameStats stats=matchSet.getSetStats().get(key);
			switch(stats.getWinner())
			{
			case PLAYER1:
				player1WonSet.add(stats.getSetId()+" ");
				break;
			case PLAYER2 :
				player2WonSet.add(stats.getSetId()+" ");
				break;
			}
		}
		matchSet.setPlayer1WonSet(player1WonSet);
		matchSet.setPlayer2WonSet(player2WonSet);
		matchSet.setLastSetNo(matchSet.getSetStats().keySet().size());
		return runningMatches;
	}

}
