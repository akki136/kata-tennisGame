package com.kata.tennis.managescore;

import java.util.stream.Collectors;


import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.executionengine.GameFactory;

import lombok.Getter;
import lombok.Setter;


/**
 * {@link TennisGame} class used  to manage Tennis Game play without deuce
 * 
 *
 */
@Setter
@Getter
public class TennisGame extends GameFactory {

	

	public TennisGame(GameStats gameStats) {
		super(gameStats);
	}


	@Override
	public GameFactory process(Player player) throws TennisException {
		
	
			return gamePlay(player);
		

		//		 int index=getPlayerLoc(player);
		//		 scoreCard[index]=+1;
		//		 firstPlayerPoints.getEnum(scoreCard[index]);

		
	}



	private GameFactory gamePlay(Player player) throws TennisException {
		PlayerMatchStat player1=this.gameStats.getPlayer1();
		PlayerMatchStat player2=this.gameStats.getPlayer2();
		PlayerMatchStat playerStat=getPlayerLoc(player);
		if(player1.getPlayerPoints()==ScoreBoard.WIN)
		{
			System.out.println(this.getScore());
			throw new TennisException(player1.getPlayer()+"  Already won the match", ScoreBoard.WIN);
		}
		else if(player2.getPlayerPoints()==ScoreBoard.WIN)
		{
			System.out.println(this.getScore());
			throw new TennisException(player2.getPlayer()+"  Already won the match", ScoreBoard.WIN);
		}
		 playerStat=	calculateGameScore(playerStat);
			if(checkIfGameWon())
		{
			playerStat.setPlayerPoints(utility.getEnum(5));
			System.out.println(this.getScore());
			gameStats.setWinner(player);
		
			System.out.println(player+ " has won the Game");
		}
		  else if(checkIfDeuce())
		{
			System.out.println(this.getScore());
			System.out.println("Deuce Rule Activated");
			this.gameStats.setHasDeuce(true);
			player1.setDeuceScore(3);
			player2.setDeuceScore(3);
		
			return new DeuceGame(this.gameStats);
		}else
		{
			System.out.println(this.getScore());
		}
		return this;
	}
	
	
	
	
	@Override
	public String getScore() {
		String player1Result=this.gameStats.getPlayer1().getScores().stream().collect(Collectors.joining("-> "));
		String player2Result=this.gameStats.getPlayer2().getScores().stream().collect(Collectors.joining("-> "));
		return String.format(Player.PLAYER1.toString()+": %s "+Player.PLAYER2+": %s", player1Result, player2Result);
	}

	@Override
	public String toString() {
		return "TennisGame [firstPlayerScore=" + this.gameStats.getPlayer1() + ", secondPlayerScore=" + this.gameStats.getPlayer2() + "]";
	}



	@Override
	public boolean checkIfGameWon() {
		return scoreDifference(2) && (this.gameStats.getPlayer1().getScore() == 4 ||this.gameStats.getPlayer2().getScore() == 4);
	}



	@Override
	public boolean checkIfDeuce() {
		return (this.gameStats.getPlayer1().getScore() == 3 && this.gameStats.getPlayer2().getScore() == 3);
	}















	
	
}
