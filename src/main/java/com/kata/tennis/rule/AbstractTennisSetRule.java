package com.kata.tennis.rule;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.TennisGameController;
import com.kata.tennis.managescore.MatchSet;

public abstract class AbstractTennisSetRule implements TennisSetRuleI {


	protected LinkedList<TennisSetRuleI> rules=new LinkedList<>();
	protected TennisSetRuleI currentRule;
	protected TennisSetRuleI nextRule;
	
	
	public void setNextRule(TennisSetRuleI tennisRuleI)
	{
		this.rules.add(tennisRuleI);
	}

	@Override
	public void setNextRules(LinkedList<TennisSetRuleI> tennisRuleIs) {
		this.rules=new LinkedList<>(tennisRuleIs);
		
	}

	public LinkedHashMap<Integer, MatchSet> applynextRuleIfExist(LinkedHashMap<Integer, MatchSet> runningMatches,Integer matchId,Player currentPlayer,Integer currentSetId) throws TennisException {
		this.nextRule=this.rules.poll();
		LinkedHashMap<Integer, MatchSet> out=new LinkedHashMap<>(runningMatches);
		if(this.nextRule!=null&&out!=null)
		{
	   this.nextRule.setNextRules(this.rules);
		out= this.nextRule.applyRule(runningMatches,matchId,currentPlayer,currentSetId);
		}
		return runningMatches;
	}




	public String getScore(List<String> l1,List<String> l2) {
		String player1Result=l1.stream().collect(Collectors.joining("-> "));
		String player2Result=l2.stream().collect(Collectors.joining("-> "));
		return String.format("Set  ::"+Player.PLAYER1.toString()+": %s "+Player.PLAYER2+": %s", player1Result, player2Result);
	}

	public boolean checkIfSetWon(int p1,int p2) {
		return 	 scoreDifference(p1,p2,2) && (p1 == 6 ||p2 == 6);
	}

	boolean scoreDifference(int p1,int p2,int difference) {
		return Math.max(p1, p2) - Math.min(p1, p2) >= difference;
	}

	public boolean checkIfTieBreak(int p1,int p2) {
		return (p1 == 6 &&p2 == 6);
	}

	public boolean checkIfSetWonWithThreshHold(int p1,int p2) {
		return 	(p1 == 7 ||p2 == 7);
	}

}
