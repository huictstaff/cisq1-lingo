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
    And add "<score>" to my score

    Examples:
      | previous length | next length | score |
      | 5               | 6           | 10    |
      | 6               | 7           | 20    |
      | 7               | 5           | 30    |

    #Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: Guessing a word
    Given An active round
    When The word is "<word>"
    And I guess "<guess>"
    Then I want "<feedback>" as feedback

    Examples:
      | word  | guess | feedback                              |
      | Baard | Brood | CORRECT,FALSE,FALSE,POSITION,CORRECT  |
      | Baard | Beard | CORRECT,FALSE,CORRECT,CORRECT,CORRECT |

  Scenario: Guessing a word wrong
    Given An active round
    When I guess the word wrong for the 5th time
    Then I should lose the active game

  Scenario: Player tries to guess a word that already has been guessed
    Given A word was guessed
    When I try to guess the word
    Then I should get a warning that the word has already been guessed
    And I should start a new round

  Scenario: Player tries to guess while the game is already over
    Given A game has been lost
    When I try to guess the word
    Then I should get a warning that I already lost

  Scenario: Player want to make a new round while a round is already active
    Given A active round
    When I try to start a new round
    Then I should get a warning that I already have an active round

  Scenario: Player tries to start a round without an active game
    Given There is no active game
    When I try to start a new round
    Then I should get a warning that I need to start a game first