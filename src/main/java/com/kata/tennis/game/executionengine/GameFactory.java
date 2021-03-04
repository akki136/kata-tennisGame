package com.kata.tennis.game.executionengine;

import com.kata.tennis.enums.Player;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.PlayerMatchStat;
import com.kata.tennis.utility.Utility;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * GameFactory class used  to process tennis game logics 
 * 
 *
 */
@Setter
@Getter
@EqualsAndHashCode
public abstract class GameFactory implements TennisScoreProcessor {
	protected GameStats gameStats=null ;
	protected Utility utility=Utility.getInstance();
	public abstract String getScore();
	public abstract boolean checkIfGameWon();
//	public abstract boolean checkIfSetWon();
	public abstract boolean checkIfDeuce();
	//public abstract boolean checkIfThreshHoldReached();
	//public abstract boolean checkIfTieBreak();
	public GameFactory(GameStats gameStats) {
		this.gameStats=gameStats;
	}

	public  PlayerMatchStat getPlayerLoc(Player player) {
		PlayerMatchStat plaStats=null;	   
		switch(player)
		{
		case PLAYER1 :
			plaStats=this.gameStats.getPlayer1();
			break;
		case PLAYER2:
			plaStats= this.gameStats.getPlayer2();
			break;
		}
		return plaStats;
	}

	public  PlayerMatchStat calculateGameScore(PlayerMatchStat playerStat) {
		playerStat.setScore(playerStat.getScore()+1);
		playerStat.addScoreToList(utility.getValue(playerStat.getScore()));
		playerStat.setPlayerPoints(utility.getEnum(playerStat.getScore()));
		return playerStat;
	}

	public  PlayerMatchStat calculateDeuceScore(PlayerMatchStat playerStat) {
		playerStat.setDeuceScore(playerStat.getDeuceScore()+1);
		playerStat.addScoreToList(utility.getValue(playerStat.getDeuceScore()));
		playerStat.setPlayerPoints(utility.getEnum(playerStat.getDeuceScore()));
		return playerStat;
	}
//	public  PlayerMatchStat calculateSetScore(PlayerMatchStat playerStat) {
//		playerStat.setSetScore(playerStat.getScore()+1);
//		playerStat.addScoreToList(utility.getValue(5+playerStat.getSetScore()));
//		playerStat.setPlayerPoints(utility.getEnum(5+playerStat.getSetScore()));
//		return playerStat;
//	}

	

	protected boolean scoreDifference(int difference) {
		return Math.max(this.gameStats.getPlayer1().getScore(), this.gameStats.getPlayer2().getScore()) - Math.min(this.gameStats.getPlayer1().getScore(), this.gameStats.getPlayer2().getScore()) >= difference;
	}
	
	protected boolean scoreDeuceDifference(int difference) {
		return Math.max(this.gameStats.getPlayer1().getDeuceScore(), this.gameStats.getPlayer2().getDeuceScore()) - Math.min(this.gameStats.getPlayer1().getDeuceScore(), this.gameStats.getPlayer2().getDeuceScore()) >= difference;
	}

	protected Player winningPlayer() {
		return this.gameStats.getPlayer1().getScore() > this.gameStats.getPlayer2().getScore() ? Player.PLAYER1 : Player.PLAYER2;
	}

	

	protected int player1Score() {
		return this.gameStats.getPlayer1().getScore();
	}

	protected int player2Score() {
		return this.gameStats.getPlayer2().getScore();
	}

	@Override
	public String toString() {
		return "GameFactory [player1=" + this.gameStats.getPlayer1() + ", player2=" + this.gameStats.getPlayer1()  + "]";
	}


}
