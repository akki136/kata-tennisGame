package com.kata.tennis.rule;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.managescore.MatchSet;

public interface TennisGameRuleI {

	LinkedHashMap<Integer, MatchSet>  applyRule(LinkedHashMap<Integer ,MatchSet> runningMatches,Integer matchId,Player currentPlayer,Integer currentSetId) throws TennisException;
}
