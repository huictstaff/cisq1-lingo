Feature: Training for Lingo
  As a player,
  I want to guess 5,6,7 letter words
  In order to become the best at Lingo

  Scenario: Start a new game
    When i start a new game
    Then i should get a first letter
    And the word should consist of "5" letters
    And i should start with "0" points

