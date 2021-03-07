package com.kata.tennis.managescore;

import com.kata.tennis.enums.Player;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TennisSetInfo {
	Player p1;
	Player p2;
	int matchId;
	int no;
}
