Feature: Lingo Word Trainer
  As a User,
  I want to guess "5", "6" and "7" letter words
  In order to train for the tv show

  Scenario: Start new game
    When I start a new game
    Then I want to be able to guess a "5" letter word
    And See the letter it begins with

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

    #Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round