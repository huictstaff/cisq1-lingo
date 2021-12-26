Feature: Specify word length.
  As a User,
  I want to be able to choose word length for a specific practice session,
  In order to practice for a specific gametype.


Feature: Start new game
  As a User,
  I want to be able to start a new game,
  In order to begin a game of lingo.

Scenario:
  Given I am a user
  When I start the Trainer
  Then I should be able to start a new game
