package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.Entity;

 public enum GameStatus
{
    GAME_STARTED_WAITING_FOR_NEW_ROUND,
    ROUND_STARTED_WAITING_FOR_NEW_GUESS,
    ENDED_GAME_WON,
    ENDED_GAME_LOST
}
