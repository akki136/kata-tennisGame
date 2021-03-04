package com.kata.tennis.game.executionengine;


import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;


/**
 * TennisScoreProcessor() interface has method implemented to process tennis game logics 
 * 
 *
 */
@FunctionalInterface
public interface TennisScoreProcessor {
	GameFactory process(Player player) throws TennisException;
}
