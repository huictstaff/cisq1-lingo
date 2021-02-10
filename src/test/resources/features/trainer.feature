Feature: Starting a game
  As a player,
  I want to start a game,
  So i can play the game

  Scenario: Player starts a game
    When the player starts a new game
    Then the score is "0" for that game