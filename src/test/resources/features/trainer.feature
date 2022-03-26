Feature: Training for Lingo
  As a player
  I want to practice guessing 5, 6 and 7 letter words
  To become better at playing the game of Lingo

Scenario:
  When I start a new game
  Then the first word to guess has a length of "5" letters
  And The first letter of the word is given at the start
  And I start with a score of "0"
  