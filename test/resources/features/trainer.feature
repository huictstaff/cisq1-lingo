Feature: Lingo Trainer
  As a Lingo player,
  I want to be able to train in the Lingo game,
  to participate in the Lingo television series

  Scenario: Start new game
    When I start the game
    Then A word of "5" letters will be generated
    And the first round should start
    And I will be presented with the first letter of the word
    And My score should be "0"

  Scenario Outline: Start a new round
    Given I am playing a game
    And the round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the word to guess has "<next length>" letters

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |
