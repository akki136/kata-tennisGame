package com.kata.tennis.managescore;

import java.util.LinkedList;
import java.util.List;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link PlayerMatchStat} class used  to manage players states
 * 
 *
 */
@Setter
@Getter
@EqualsAndHashCode
public class PlayerMatchStat {
	
	private ScoreBoard playerPoints ;
	Player player;
	private int score;
	private int deuceScore;
	private List<String> scores=new LinkedList<>();

	public void addScoreToList(String score)
	{
		scores.add(score);
	}

	

	@Override
	public String toString() {
		return "PlayerMatchStat [playerPoints=" + playerPoints + ", player=" + player + ", score=" + score + ", scores="
				+ scores + "]";
	}

	
	
	

	

	
	
	
}
