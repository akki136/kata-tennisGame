package com.kata.tennis.exception;


import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.managescore.TennisGame;

import lombok.Getter;

/**
 * TennisException class used  to throw Generic Tennis Exceptions
 * 
 *
 */
@Getter
public class TennisException extends Exception {
	private ScoreBoard scoreBoard;

	public TennisException(String errMsg,ScoreBoard scoreBoard)
	{
		super(errMsg);
		this.scoreBoard=scoreBoard;
	}
}
