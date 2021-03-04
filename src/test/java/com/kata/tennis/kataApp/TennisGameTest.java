package com.kata.tennis.kataApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.GamePlay;
import com.kata.tennis.managescore.GameStats;
import com.kata.tennis.managescore.MatchSet;
import com.kata.tennis.managescore.PlayerMatchStat;

/**
 * Unit test for simple App.
 */
@DisplayName("Kata Tennis Game")
public class TennisGameTest 
{
	/**
	 * Rigorous Test :-)
	 */
	private GamePlay gamePlay=null;

	@BeforeEach
	public void init()
	{
		gamePlay=GamePlay.startPlay(Player.PLAYER1,Player.PLAYER2,1,1);
	}


	@Test
	@DisplayName("Game and Set Start Test Case")
	public void tennisGameStartTest()
	{
		PlayerMatchStat player1= gamePlay.getGame().getGameStats().getPlayer1();
		PlayerMatchStat player2= gamePlay.getGame().getGameStats().getPlayer1();
		assertEquals(ScoreBoard.ZERO, player1.getPlayerPoints());
		assertEquals(ScoreBoard.ZERO,  player2.getPlayerPoints());
	}

	@Test
	@DisplayName("Player1 Wins the Match(Deuce)")
	public void player1WinsTheMatchWithDeuceTest() throws TennisException
	{
		List<Player>  players=	getPlayerScoreDeucePlayer1Wins();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,1);
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER1, winner);
	}
	
	@Test
	@DisplayName("Player1 Wins the Match without Deuce")
	public void player1WinsTheMatchTest() throws TennisException
	{
		List<Player>  players=	getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,1);
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		assertNotEquals(stats.getHasDeuce(), true);
		Player winner=  stats.getWinner();
		assertEquals(Player.PLAYER1, winner);
	}
	
	@Test
	@DisplayName("Player2  Wins the Match without Deuce")
	public void player2WinsTheMatchTest() throws TennisException
	{
		List<Player>  players=	getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,1);
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		assertNotEquals(stats.getHasDeuce(), true);
		Player winner=  stats.getWinner();
		assertEquals(Player.PLAYER2, winner);
	}


	@Test
	@DisplayName("Player2 Wins the Match (Deuce)")
	public void player2WinsTheMatchWithDeuceTest() throws TennisException
	{
		List<Player>  players=	getPlayerScoreDeucePlayer2Wins();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,1);
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}
	
	@Test
	@DisplayName("Player1 Wins the Set")
	public void player1WinsTheSet() throws TennisException
	{
		List<Player>  players=	getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=8)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(Player.PLAYER1, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER1, winner);
	}

	
	@Test
	@DisplayName("Player2 Wins the Set")
	public void player2WinsTheSet() throws TennisException
	{
		List<Player>  players=	getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=8)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(Player.PLAYER2, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}
	
	@Test
	@DisplayName(" Player 1 win a Game and reach the Set score of 6 and the Player2 has a Set score of 4")
	public void player1WinsThreshHoldSet() throws TennisException
	{
		List<Player>  players=	getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=5)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=5;
		 players=	getPlayer1WinDataSet();
		while(i<=15)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(Player.PLAYER1, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(10);
		Player winner=  stats.getWinner();
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER1, winner);
	}
	
	@Test
	@DisplayName(" Player 2 win a Game and reach the Set score of 6 and the Player2 has a Set score of 4")
	public void player2WinsThreshHoldSet() throws TennisException
	{
		List<Player>  players=	getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=5)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=5;
		 players=	getPlayer2WinDataSet();
		while(i<=15)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(Player.PLAYER2, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(10);
		Player winner=  stats.getWinner();
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}
	
	@Test
	@DisplayName(" Player 1 and player 2 reaches to Tie Break")
	public void player1and2TieBreak() throws TennisException
	{
		
		List<Player>  players=	getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=4)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=5;
		 players=	getPlayer2WinDataSet();
		while(i<=8)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=9;
		 players=	getPlayer1WinDataSet();
		while(i<=9)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=10;
		 players=	getPlayer2WinDataSet();
		while(i<=10)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=11;
		 players=	getPlayer1WinDataSet();
		while(i<=11)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=12;
		 players=	getPlayer2WinDataSet();
		while(i<=12)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(matchSet.getIsTieBreak(), true);
		assertEquals(Player.UNKNOWN, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(10);
		Player winner=  stats.getWinner();
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}
	
	
	@Test
	@DisplayName(" Player 1 and player 2 reaches to Tie Break as soon as a player1 gets a least 6 points and gets 2 points more than his opponent")
	public void player1and2TieBreakANdplayer2Won() throws TennisException
	{
		List<Player>  players=	getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=4)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=5;
		 players=	getPlayer2WinDataSet();
		while(i<=8)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=9;
		 players=	getPlayer1WinDataSet();
		while(i<=9)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=10;
		 players=	getPlayer2WinDataSet();
		while(i<=10)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=11;
		 players=	getPlayer1WinDataSet();
		while(i<=11)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=12;
		 players=	getPlayer2WinDataSet();
		while(i<=12)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=14;
		 players=	getPlayer2WinDataSet();
		while(i<=15)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(matchSet.getIsTieBreak(), true);
		assertEquals(matchSet.getBoard(), ScoreBoard.WINSET);
		assertEquals(Player.PLAYER2, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(10);
		Player winner=  stats.getWinner();
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}
	
	
	
	@Test
	@DisplayName(" Player 1 and player 2 reaches to Tie Break as soon as a player1 gets a least 6 points and gets 2 points more than his opponent")
	public void player1and2TieBreakANdplayer1Won() throws TennisException
	{
		List<Player>  players=	getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		int i=1;
		while(i<=4)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=5;
		 players=	getPlayer2WinDataSet();
		while(i<=8)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=9;
		 players=	getPlayer1WinDataSet();
		while(i<=9)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=10;
		 players=	getPlayer2WinDataSet();
		while(i<=10)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=11;
		 players=	getPlayer1WinDataSet();
		while(i<=11)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=12;
		 players=	getPlayer2WinDataSet();
		while(i<=12)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		i=14;
		 players=	getPlayer1WinDataSet();
		while(i<=15)
		{
		gamePlay=GamePlay.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
		for(Player player:players)
		{
		 matchResult=	gamePlay.playTennis(player,1,i);
		}
		i++;
		}
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(matchSet.getIsTieBreak(), true);
		assertEquals(matchSet.getBoard(), ScoreBoard.WINSET);
		assertEquals(Player.PLAYER1, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER1, winner);
	}
	public static List<Player> getPlayer1WinDataSet()
	{
		return Arrays.asList(Player.PLAYER1,Player.PLAYER1,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER1);
	}
	public static List<Player> getPlayer2WinDataSet()
	{
		return Arrays.asList(Player.PLAYER1,Player.PLAYER2,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER2,Player.PLAYER2);
	}
	
	public static List<Player> getPlayerScoreDeucePlayer1Wins()
	{
		return Arrays.asList(Player.PLAYER1,Player.PLAYER1,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER2,Player.PLAYER2,Player.PLAYER1,Player.PLAYER1,Player.PLAYER1,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER2);
	}
	
	public static List<Player> getPlayerScoreDeucePlayer2Wins()
	{
		return Arrays.asList(Player.PLAYER1,Player.PLAYER1,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER2,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER1,Player.PLAYER2,Player.PLAYER2);
	}
}
