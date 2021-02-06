Feature: Training for Lingo
  As a player,
  I want to guess 5,6,7 letter words
  In order to become the best at Lingo

  Scenario: Start a new game
    When i start a new game
    Then i should get a first letter
    And the word should consist of "5" letters
    And i should start with "0" points

  Scenario: Start a new round
    Given i started a game
    And i won the last one
    Then i should get a first letter
    And i can guess the word

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

  # Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round
