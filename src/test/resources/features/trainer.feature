Feature: Training for Lingo
  As a player,
  I want to guess 5,6,7 letter words
  In order to become the best at Lingo

  Scenario: Start new game
    When I start a new game
    Then the word to guess has "5" letters
    And I should see the first letter
    And my score is "0"
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

    Given I am playing a game
    And the round is still going
    Then I cannot start a new round

    Given I am not playing a game
    Then I cannot start a new round
  Scenario Outline: Guessing a word
    Given I have started a round
    And the round is not over
    And the word is "<word>"
    When I guess the word "<guess>"
    Then The feedback should be "<feedback>"

    Examples:
    | word  | guess  | feedback                                             |
    | BAARD | BERGEN | INVALID, INVALID, INVALID, INVALID, INVALID, INVALID |
    | BAARD | BONJE  | CORRECT, ABSENT, ABSENT, ABSENT, ABSENT              |
    | BAARD | BARST  | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT            |
    | BAARD | DRAAD  | ABSENT, PRESENT, CORRECT, PRESENT, CORRECT           |
    | BAARD | BAARD  | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT          |

    Given I have started a round
    And the round is not over
    When I guess correct
    And I have guessed "<attempts>" times
    Then my score should increase by "<score>"

    Examples:
    | attempts | score |
    | 1        | 25    |
    | 2        | 20    |
    | 3        | 15    |
    | 4        | 10    |
    | 5        | 5     |

    Given I have started a round
    And the round is not over
    And I have guessed 4 times
    When I guess incorrect
    Then the game should be over

    Given I have started a round
    And the word has already been guessed
    Then I should get another word
