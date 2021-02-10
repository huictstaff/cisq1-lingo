Feature: Starting a game
  As a player,
  I want to start a game,
  So i can play the game

  Rule: A player can have multiple games

  Scenario: Player starts a game
    When the player starts a new game
    Then the score is "0" for that game
    And a new round is started

    Scenario Outline: Start a new round
      Given the player is playing a game
      And the round was won
      And the last word had "<previous length>" letters
      When I start a new round
      Then the next word to guess has "<next length>" letters

      Examples:
        | previous length | next length |
        | 5               | 6           |
        | 6               | 7           |
        | 7               | 5           |