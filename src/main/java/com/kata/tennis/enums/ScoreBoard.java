package com.kata.tennis.enums;

import lombok.Getter;

/**
 * {@link ScoreBoard} enum used  to manage scores
 * 
 *
 */
@Getter
public enum ScoreBoard {
	
	
	 WIN("WIN GAME", "WIN"),
	    ADVANTAGE("ADVANTAGE", "ADVANTAGE"),
	    FORTY("40", "FORTY"),
	    THIRTY("30", "THIRTY"),
	    FIFTEEN("15", "FIFTEEN"),
	    ZERO("0", "ZERO"),	
	    ONE("0", "ONE"),
		TWO("0", "TWO"),
		THREE("0", "THREE"),
		FOUR("0", "FOUR"),
		FIVE("0", "FIVE"),
		SIX("0", "SIX"),
		SEVEN("0", "SEVEN"),
	 TIEBREAK("Tie-Break","Tie-Break"),
	 WINSET("WIN SET", "WIN");
	
	
	public String score;
	public String status;
	private ScoreBoard(String score,String status) {
	this.score=score;
	this.status=status;
	}
	
	

}
