Feature: See the first letter of a random word
  As a player,
  I want to be able to see the first letter of a random word,
  In order to start with guessing.

  Scenario: Start a new game
    When I start a new game
    Then the word to guess has "5" letters
    And I should see the first letter of a random word
    And my score is "0"

  Scenario: Start a new round
    Given I am playing a game
    And the last round was won
    When I see a new first letter of a random word
    Then I could start with guessing

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
  Scenario: End game
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: Guessing a word
    Given a game started
    And a letter is displayed from an random <word>
    When I make a <guess>
    Then the game provides <feedback>

    Examples:
    | word | guess | feedback |
    | games | bruut | absent, absent, absent, absent, absent |
    | games | gaming | invalid, invalid, invalid, invalid, invalid, invalid |
    | games | broek | absent, absent, absent, correct, absent |
    | games | gapen | correct, correct, absent, correct, absent |
    | games | surft | present, absent, absent, absent, absent   |
    | games | games | correct, correct, correct, correct, correct |


  Scenario Outline: Score increases based on number of attempts
    Given I am playing a game
    And the score is "<current score>"
    And the word to guess is "games"
    When I guess "games" in "<attempts>" attempts
    Then the score is "<new score>"

    Examples:
      | current score | attempts | new score |
      | 0             | 1        | 25        |
      | 5             | 1        | 30        |
      | 0             | 2        | 20        |
      | 5             | 2        | 25        |
      | 0             | 3        | 15        |
      | 5             | 3        | 20        |
      | 0             | 4        | 10        |
      | 5             | 4        | 15        |
      | 0             | 5        | 5         |
      | 5             | 5        | 10        |

  Scenario: Cannot start a round if still guessing
    Given I am playing a game
    And I am still guessing a word
    Then I cannot start a new round

  Scenario: Cannot start a round if eliminated
    Given I am playing a game
    And I have been eliminated
    Then I cannot start a new round

  Scenario: Cannot guess word if round not started
    Given I am playing a game
    And the round was won
    Then I cannot guess the word