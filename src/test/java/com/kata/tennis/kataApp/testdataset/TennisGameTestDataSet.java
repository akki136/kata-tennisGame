package com.kata.tennis.kataApp.testdataset;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.kata.tennis.enums.Player;
import com.kata.tennis.exception.TennisException;
import com.kata.tennis.game.TennisGameController;
import com.kata.tennis.managescore.MatchSet;

public class TennisGameTestDataSet {

	
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
