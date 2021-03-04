package com.kata.tennis.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.executionengine.GameFactory;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.managescore.PlayerMatchStat;
import com.kata.tennis.managescore.TennisGame;
import com.kata.tennis.utility.Utility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akki1
 * Game Play is Used for processing Tennis Match Set Logics like Tie-Break
 *
 */

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GamePlay  {

	static Utility utility=Utility.getInstance();
	private GameFactory game;
	static LinkedHashMap<Integer ,MatchSet> runningGames=new LinkedHashMap<>();
	public static GamePlay startPlay(Player p1, Player p2,int set,int game)
	{
		runningGames=new LinkedHashMap<>();

		return startSet(p1, p2,set,game);

	}
	
	/**
	 * startSet() is used to create/start new set in Tennis Match
	 * 
	 *
	 */
	
	public static GamePlay startSet(Player p1, Player p2,int matchId, int no) {
		
		MatchSet matchStats=runningGames.get(matchId);
		if(matchStats==null)
		{
			matchStats=new MatchSet();
			matchStats.setBoard(ScoreBoard.ZERO);
			GameStats gameStats=  initializeMatch(p1, p2, no);
			gameStats.setSetId(no);
			matchStats.getSetStats().put(no,gameStats);
			runningGames.put(matchId, matchStats);
		}
		GameStats gameStats=	matchStats.getSetStats().get(no);
		if(gameStats==null)
		{
			gameStats=  initializeMatch(p1, p2, no);
			gameStats.setSetId(no);
			matchStats.getSetStats().put(no,gameStats);
		}
		return new GamePlay(new TennisGame(matchStats.getSetStats().get(no)));
	}
	
	/**
	 * initializeMatch() is used to initialize Game in a Set
	 * 
	 *
	 */
	private static GameStats initializeMatch(Player p1, Player p2, int no) {
		PlayerMatchStat player1=new PlayerMatchStat();
		player1.setPlayer(p1);
		player1.setPlayerPoints(ScoreBoard.ZERO);
		player1.setScore(0);
		PlayerMatchStat player2=new PlayerMatchStat();
		player2.setPlayerPoints(ScoreBoard.ZERO);
		player2.setPlayer(p2);
		player2.setScore(0);
		player1.addScoreToList(utility.getValue(player1.getScore()));
		player2.addScoreToList(utility.getValue(player2.getScore()));
		GameStats gameStats=new GameStats(player1,player2,UUID.randomUUID().toString());
		return gameStats;
	}


	/**
	 * playTennis() is used to start play of Tennis set
	 * 
	 *
	 */
	public  LinkedHashMap<Integer ,MatchSet> playTennis(Player player,int matchId,int setId) throws TennisException 
	{
		
		MatchSet matchSet=runningGames.get(matchId);
		if(matchSet.getWinner()!=Player.UNKNOWN)
		{
			System.out.println("Set Already won by "+matchSet.getWinner());
			return runningGames;
		}
		try
		{
			game=game.process(player);
		}catch(TennisException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
		int p1score=0,p2score=0;
		Player player1=null;
		Player player2=null;
		int lastSetNo=0;
		List<String> player1WonSet=new ArrayList<>();
		List<String> player2WonSet=new ArrayList<>();
		Boolean isThreshHoldActivated=matchSet.getThreshholdActivated();
		Boolean isTieBreak=matchSet.getIsTieBreak();
		for(Integer key:matchSet.getSetStats().keySet())
		{
			GameStats stats=matchSet.getSetStats().get(key);
			
			player1=stats.getPlayer1().getPlayer();
			player2=stats.getPlayer2().getPlayer();
			
			switch(stats.getWinner())
			{
			case PLAYER1:
				p1score++;
				player1WonSet.add(stats.getSetId()+" ");
				break;
			case PLAYER2 :
				player2WonSet.add(stats.getSetId()+" ");
				p2score++;
				break;
			}
			lastSetNo++;
		}
		
		if(checkIfTieBreak(p1score,p2score))
		{
			matchSet.setBoard(ScoreBoard.TIEBREAK);
			System.out.println("Tie-Brack Occured "+matchSet.getWinner());
			System.out.println(this.getScore(player1WonSet, player2WonSet));
			matchSet.setIsTieBreak(true);
		}
		else if(checkIfSetWon(p1score,p2score))
		{
			matchSet.setBoard(ScoreBoard.WINSET);
			matchSet.setWinner(player);
			System.out.println(this.getScore(player1WonSet, player2WonSet));

		}
		else if(checkIfSetWonWithThreshHold(p1score,p2score)&&!isTieBreak)
		{
			matchSet.setBoard(ScoreBoard.WINSET);
			matchSet.setWinner(player);
			System.out.println(this.getScore(player1WonSet, player2WonSet));

		}
		if(checkIfThreshHoldReached(p1score, p2score)&&!isThreshHoldActivated)

		{
			System.out.println(this.getScore(player1WonSet, player2WonSet));
			GameStats gameStats=initializeMatch(player1, player2, lastSetNo);
			gameStats.setSetId(setId+1);
			matchSet.setThreshholdActivated(true);
			matchSet.getSetStats().put(setId+1, gameStats);
		}
		 
		return runningGames;

	}

	@Override
	public String toString() {
		return "GamePlay [game=" + game + "]";
	}






	public boolean checkIfTieBreak(int p1,int p2) {
		return (p1 == 6 &&p2 == 6);
	}









	public boolean checkIfSetWon(int p1,int p2) {
		return 	 scoreDifference(p1,p2,2) && (p1 == 6 ||p2 == 6);
	}

	public boolean checkIfSetWonWithThreshHold(int p1,int p2) {
		return 	(p1 == 7 ||p2 == 7);
	}


	boolean scoreDifference(int p1,int p2,int difference) {
		return Math.max(p1, p2) - Math.min(p1, p2) >= difference;
	}




	public boolean checkIfThreshHoldReached(int p1,int p2) {
		return  (p1 == 6 ||p2== 6)&&(p1 == 5 ||p2 == 5);
	}





	public String getScore(List<String> l1,List<String> l2) {
		String player1Result=l1.stream().collect(Collectors.joining("-> "));
		String player2Result=l2.stream().collect(Collectors.joining("-> "));
		return String.format("Set  ::"+Player.PLAYER1.toString()+": %s "+Player.PLAYER2+": %s", player1Result, player2Result);
	}









}
