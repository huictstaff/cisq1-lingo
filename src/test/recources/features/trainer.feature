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

  Scenario Outline: Guessing a word
    Given a game has been started
    And the first letter of the <word> is present
    When i submit the word <guess>
    And the <guess> was wrong
    Then i will get <feedback>

    Examples:
      | word    | guess   | feedback                                                     |
      | bread   | breed   | correct, correct, correct, present, correct                  |
      | ape     | api     | correct , correct, absent                                    |
      | Isogram | Pangram | absent, absent, absent, correct, correct , correct , correct |

  Scenario: The guess was correct
    Given the game has been started
    And i guessed the word
    Then the round has been ended
    And my score increses by 5*(5-number of "rounds")+5

  Scenario: Lost a game
    Given a round has started
    And i used 4 chances
    When i try to guess a word
    And the guess was wrong
    Then the round is over
    And the player lost a game

  Scenario: Using a Winning word from a earlier round
    Given a round has been won
    And a new one has started
    When i try to guess the word with the winnig word from an earlier round
    Then i can not subbmit the guess with that word

  Scenario: player can not guess a word when he lost
    Given the player hav lost a game
    Then the player cannot guess a word

  Scenario: player can not start a new round when the player is still playing
    Given a round is active
    When the player is trying to start a new one
    Then the new game wil not start
