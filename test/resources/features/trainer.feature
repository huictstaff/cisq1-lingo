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