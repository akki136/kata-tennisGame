package com.kata.tennis.managescore;

import com.kata.tennis.enums.Player;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link GameStats} class used  to manage game and players
 * 
 *
 */
@Setter
@Getter
@EqualsAndHashCode
public class GameStats {
	
	private PlayerMatchStat player1 ;
	private PlayerMatchStat player2 ;
	private int setId;
	private Boolean hasDeuce=false;
	private String gameId;
	private Player winner=Player.UNKNOWN;
	/**
	 * @param player1
	 * @param player2
	 */
	public GameStats(PlayerMatchStat player1, PlayerMatchStat player2,String gameId) {
		this.player1 = player1;
		this.player2 = player2;
		this.gameId=gameId;
	}
	@Override
	public String toString() {
		return "GameStats [player1=" + player1 + ", player2=" + player2 + ", hasDeuce=" + hasDeuce
				+ ", threshholdActivated=" + true + ", gameId=" + gameId + ", winner=" + winner + "]";
	}
	
	
	
}
