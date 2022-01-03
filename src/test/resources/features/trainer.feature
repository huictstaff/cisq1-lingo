Feature: starting a game
  As a user,
  I want to start a game.
  In order to start playing the game.

Scenario: a new game with a round is started
  Given the application is running
  When i start a new game
  Then a new game is started
  And a new round is started with a 5 letter word.