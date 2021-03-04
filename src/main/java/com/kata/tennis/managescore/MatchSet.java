package com.kata.tennis.managescore;

import java.util.LinkedHashMap;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
/**
 * MatchSet class used  to manage set of games
 * 
 *
 */

@Setter
@Getter
@EqualsAndHashCode
public class MatchSet {
private ScoreBoard board;
private LinkedHashMap<Integer,GameStats> setStats=new LinkedHashMap<>();
private Boolean threshholdActivated=false;
private Boolean isTieBreak=false;
private Player winner=Player.UNKNOWN;
	
}
