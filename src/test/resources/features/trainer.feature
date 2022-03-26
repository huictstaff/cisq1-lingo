Feature: Training for Lingo
  As a player
  I want to practice guessing 5, 6 and 7 letter words
  To become better at playing the game of Lingo

Scenario:
  When I start a new game
  Then the first word to guess has a length of "5" letters
  And The first letter of the word is given at the start
  And I start with a score of "0"

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
    Given I am playing a round of a game
    And I can enter a "<word>"
    When I guess a word "<guess>"
    Then I expect "<feedback>"
    And I expect to receive a "<hint>"
    Examples:
      | word  | hint                         | guess |            feedback                             |
      | teams | ‘T’, ‘.’, ‘.’, ‘.’, ‘.’      | truck | CORRECT, ABSENT, ABSENT, ABSENT, PRESENT        |
      | teams | ‘T’, ‘.’, ‘.’, ‘.’, ‘.’      | team  | INVALID, INVALID, INVALID, INVALID              |
      | teams | ‘T’, ‘E’, ‘m’, ‘.’, ‘.’      | tempo | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT       |
      | teams | ‘T’, ‘E’, ‘A’, ‘.’, ‘.’      | teach | CORRECT, CORRECT, CORRECT, ABSENT, ABSENT       |
      | teams | ‘T’, ‘E’, ‘A’, ‘M’, ‘S’      | teams | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT     |

    #Failure path: Wrong length
    Given I made a "<guess>"
    And the length of the guess was not of the correct size
    Then I receive an error message

    #Failure path: Unknown word
    Given I made a "<guess>"
    And the "<guess>" is not a known word in the words list
    Then I receive an error message

    #Failure path: Too many wrong guesses
    Given I made my fifth "<guess>"
    And the guess was wrong
    Then The game should end

    #Failure path: Cant guess the same word twice in a round
    Given A round has started
    And I have made at least one "<guess>"
    And I can make my next guess
    And I enter a guess with the same "<word>" as in a previous guess in the same round
    Then I receive an error message that I cant enter the same word twice in a given round

  Scenario Outline: Successfully guessed the word
    Given A round has started
    And I made a "<guess>"
    And the guess was the correct word
    Then I expect my "<score>" to increase with an amount of points conditional to the amount of guesses
    Examples:
      | guess | score |
      | 1     | +25   |
      | 2     | +20   |
      | 3     | +15   |
      | 4     | +10   |
      | 5     | +5    |