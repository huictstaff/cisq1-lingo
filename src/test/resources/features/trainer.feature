Feature: Displaying first letter
  As a Player,
  I want to see the first letter of a random word,
  In order to start guessing.

Scenario: Starting a new game
  When I click the start button
  Then A new game should start

Scenario: Starting a new round
  Given the game has been started
  When I see a new letter
  Then I can guess the word