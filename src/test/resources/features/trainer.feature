Feature: Training for Lingo
  As a player,
  I want to guess 5,6,7 letter words
  In order to get more points

Scenario: Start new game
  When I start a new game
  Then The word to guess must has "5" letters
  And  The first letter is visible
  And  my score is "0"

  

