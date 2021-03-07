package com.kata.tennis.kataApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kata.tennis.enums.Player;
import com.kata.tennis.enums.ScoreBoard;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.TennisGameController;
import com.kata.tennis.kataApp.testdataset.TennisGameTestDataSet;
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
	private TennisGameController gamePlay=null;

	@BeforeEach
	public void init()
	{
		gamePlay=TennisGameController.startPlay(Player.PLAYER1,Player.PLAYER2,1,1);
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
		List<Player>  players=	TennisGameTestDataSet.getPlayerScoreDeucePlayer1Wins();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
			matchResult=	gamePlay.playTennis(player,1,1);
		}
		LinkedHashMap<Integer, GameStats> gameStats = dataValidationAsserts(matchResult);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER1, winner);
	}


	private LinkedHashMap<Integer, GameStats> dataValidationAsserts(LinkedHashMap<Integer, MatchSet> matchResult) {
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		return gameStats;
	}

	@Test
	@DisplayName("Player1 Wins the Match without Deuce")
	public void player1WinsTheMatchTest() throws TennisException
	{
		List<Player>  players=	TennisGameTestDataSet.getPlayer1WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
			matchResult=	gamePlay.playTennis(player,1,1);
		}
		LinkedHashMap<Integer, GameStats> gameStats = dataValidationAsserts(matchResult);
		GameStats  stats= gameStats.get(1);
		assertNotEquals(stats.getHasDeuce(), true);
		Player winner=  stats.getWinner();
		assertEquals(Player.PLAYER1, winner);
	}

	@Test
	@DisplayName("Player2  Wins the Match without Deuce")
	public void player2WinsTheMatchTest() throws TennisException
	{
		List<Player>  players=	TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
			matchResult=	gamePlay.playTennis(player,1,1);
		}
		LinkedHashMap<Integer, GameStats> gameStats = dataValidationAsserts(matchResult);
		GameStats  stats= gameStats.get(1);
		assertNotEquals(stats.getHasDeuce(), true);
		Player winner=  stats.getWinner();
		assertEquals(Player.PLAYER2, winner);
	}


	@Test
	@DisplayName("Player2 Wins the Match (Deuce)")
	public void player2WinsTheMatchWithDeuceTest() throws TennisException
	{
		List<Player>  players=	TennisGameTestDataSet.getPlayerScoreDeucePlayer2Wins();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		for(Player player:players)
		{
			matchResult=	gamePlay.playTennis(player,1,1);
		}
		LinkedHashMap<Integer, GameStats> gameStats = dataValidationAsserts(matchResult);
		GameStats  stats= gameStats.get(1);
		Player winner=  stats.getWinner();
		assertEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}

	@Test
	@DisplayName("Player1 Wins the Set")
	public void player1WinsTheSet() throws TennisException
	{
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		List<Player>  player1Winset=	TennisGameTestDataSet.getPlayer1WinDataSet();
		matchResult = tennisPlayResult(player1Winset, matchResult, 8,1);
		GameStats stats = matchDataValidationAsserts(matchResult,Player.PLAYER1);
		Player winner=  stats.getWinner();
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER1, winner);
	}

	@Test
	@DisplayName("Player2 Wins the Set")
	public void player2WinsTheSet() throws TennisException
	{
		List<Player>  player2Winset=	TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		matchResult = tennisPlayResult(player2Winset, matchResult, 8,1);
		GameStats  stats= matchDataValidationAsserts(matchResult,Player.PLAYER2);
		Player winner=  stats.getWinner();
		assertNotEquals(stats.getHasDeuce(), true);
		assertEquals(Player.PLAYER2, winner);
	}

	@Test
	@DisplayName(" Player 1 win a Game and reach the Set score of 6 and the Player2 has a Set score of 4")
	public void player1WinsThresholdSet() throws TennisException
	{

		List<Player>  player1Winset=	TennisGameTestDataSet.getPlayer1WinDataSet();
		List<Player>  player2Winset=	TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		matchResult = tennisPlayResult(player2Winset, matchResult, 5,1);
		matchResult = tennisPlayResult(player1Winset, matchResult, 15,5);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertEquals(Player.PLAYER1, matchSet.getWinner());
	}

	@Test
	@DisplayName(" Player 2 win a Game and reach the Set score of 6 and the Player2 has a Set score of 4")
	public void player2WinsThresholdSet() throws TennisException
	{

		List<Player>  player1Winset=TennisGameTestDataSet.getPlayer1WinDataSet();
		List<Player>  player2Winset=TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		matchResult = tennisPlayResult(player1Winset, matchResult, 5,1);
		matchResult = tennisPlayResult(player2Winset, matchResult, 15,5);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(Player.PLAYER2, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		GameStats  stats= gameStats.get(10);
		Player winner=  stats.getWinner();
		assertEquals(matchSet.getThreshholdActivated(), true);
		assertEquals(Player.PLAYER2, winner);
	}

	@Test
	@DisplayName(" Player 1 and player 2 reaches to Tie Break")
	public void player1and2TieBreak() throws TennisException
	{

		List<Player>  player1Winset=TennisGameTestDataSet.getPlayer1WinDataSet();
		List<Player>  player2Winset=TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		matchResult = tennisPlayResult(player1Winset, matchResult, 4,1);
		matchResult = tennisPlayResult(player2Winset, matchResult, 8,5);
		matchResult = tennisPlayResult(player1Winset, matchResult, 9,9);
		matchResult = tennisPlayResult(player2Winset, matchResult, 10,10);
		matchResult = tennisPlayResult(player1Winset, matchResult, 11,11);
		matchResult = tennisPlayResult(player2Winset, matchResult, 12,12);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(matchSet.getIsTieBreak(), true);
		assertEquals(Player.UNKNOWN, matchSet.getWinner());
	}

	@Test
	@DisplayName(" Player 1 and player 2 reaches to Tie Break as soon as a player1 gets a least 6 points and gets 2 points more than his opponent")
	public void player1and2TieBreakANdplayer2Won() throws TennisException
	{


		List<Player>  player1Winset=TennisGameTestDataSet.getPlayer1WinDataSet();
		List<Player>  player2Winset=TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		matchResult = tennisPlayResult(player1Winset, matchResult, 4,1);
		matchResult = tennisPlayResult(player2Winset, matchResult, 8,5);
		matchResult = tennisPlayResult(player1Winset, matchResult, 9,9);
		matchResult = tennisPlayResult(player2Winset, matchResult, 10,10);
		matchResult = tennisPlayResult(player1Winset, matchResult, 11,11);
		matchResult = tennisPlayResult(player2Winset, matchResult, 12,12);
		matchResult = tennisPlayResult(player2Winset, matchResult, 15,14);
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
		assertEquals(Player.PLAYER2, winner);
	}



	@Test
	@DisplayName(" Player 1 and player 2 reaches to Tie Break as soon as a player1 gets a least 6 points and gets 2 points more than his opponent")
	public void player1and2TieBreakANdplayer1Won() throws TennisException
	{
		List<Player>  player1Winset=TennisGameTestDataSet.getPlayer1WinDataSet();
		List<Player>  player2Winset=TennisGameTestDataSet.getPlayer2WinDataSet();
		LinkedHashMap<Integer, MatchSet> matchResult=null;
		matchResult = tennisPlayResult(player1Winset, matchResult, 4,1);
		matchResult = tennisPlayResult(player2Winset, matchResult, 8,5);
		matchResult = tennisPlayResult(player1Winset, matchResult, 9,9);
		matchResult = tennisPlayResult(player2Winset, matchResult, 10,10);
		matchResult = tennisPlayResult(player1Winset, matchResult, 11,11);
		matchResult = tennisPlayResult(player2Winset, matchResult, 12,12);
		matchResult = tennisPlayResult(player1Winset, matchResult, 15,14);
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
		assertEquals(Player.PLAYER1, winner);
	}


	private GameStats matchDataValidationAsserts(LinkedHashMap<Integer, MatchSet> matchResult,Player expectedWinner) {
		assertNotNull(matchResult);
		assertNotEquals(matchResult.size(), 0);
		MatchSet matchSet=    matchResult.get(1);
		assertEquals(expectedWinner, matchSet.getWinner());
		assertNotNull(matchSet);
		LinkedHashMap<Integer,GameStats> gameStats=   matchSet.getSetStats();
		assertNotNull(gameStats);
		assertNotEquals(gameStats.size(), 0);
		GameStats  stats= gameStats.get(1);
		return stats;
	}


	private LinkedHashMap<Integer, MatchSet> tennisPlayResult(List<Player> players,
			LinkedHashMap<Integer, MatchSet> matchResult,int n,int nextGameNo) throws TennisException {
		int i=nextGameNo;
		while(i<=n)
		{
			gamePlay=TennisGameController.startSet(Player.PLAYER1,Player.PLAYER2,1,i);
			for(Player player:players)
			{
				matchResult=	gamePlay.playTennis(player,1,i);
			}
			i++;
		}
		return matchResult;
	}

}
