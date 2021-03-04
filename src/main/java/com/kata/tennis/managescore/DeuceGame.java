package com.kata.tennis.managescore;

import java.util.stream.Collectors;
import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.executionengine.GameFactory;


/**
 * {@link DeuceGame} class used  to process business logic if deuce occured
 * 
 *
 */
public class DeuceGame extends GameFactory  {



	public DeuceGame(GameStats  gameStats) {
		super(gameStats);
	}

	
	@Override
	public GameFactory process(Player player) throws   TennisException{
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
		 playerStat=	calculateDeuceScore(playerStat);
		
		Boolean hasAdvantage=true;
		
		if(checkIfGameWon())
		{
			System.out.println(this.getScore());
			playerStat.setPlayerPoints(utility.getEnum(5));
			hasAdvantage=false;
			this.gameStats.setWinner(player);
			System.out.println(player+ " has won the Game");
		}else if(checkIfDeuce())
		{
			System.out.println(this.getScore());
			
			System.out.println("Deuce Rule Activated");
			player1.setDeuceScore(3);
			player2.setDeuceScore(3);
			player1.addScoreToList(utility.getValue(player1.getScore()));
			player2.addScoreToList(utility.getValue(player2.getScore()));
			
			return new DeuceGame(this.getGameStats());
		}
		if(hasAdvantage)
		{
			System.out.println(this.getScore());
		System.out.println(player+" has Advantage");
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
		return "DeuceGame []";
	}

	@Override
	public boolean checkIfGameWon() {
		return scoreDeuceDifference(2);
	}

	@Override
	public boolean checkIfDeuce() {
		return (this.gameStats.getPlayer1().getDeuceScore() == this.gameStats.getPlayer2().getDeuceScore() );
	}


	
}
