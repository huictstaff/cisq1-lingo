Feature: Lingo Word Trainer
  As a User,
  I want to guess "5", "6" and "7" letter words
  In order to train for the tv show

  Scenario: Start new game
    When I start a new game
    Then I want to start the first round
    And  I want to be able to guess a "5" letter word
    And  My score is set to "0"
    And  See the first letter

  Scenario: Losing a round
    Given I am playing a round
    And   I had "5" attempts at the word
    And   I can't move on to a next round
    Then  I can start a new game

  Scenario Outline: Winning a round
    Given I am playing a round
    And   I guessed all the letters in the word
    And   the round was won
    Then  move on from <current round> to <next round> I want my score to go from <current score> to <winning score> and the current word length is <word length>
    And   my score to be shown on a leaderboard

    Examples:
      | current round | next round  | current score | winning score | word length |
      | 1             | 2           | 0             | 100           | 5           |
      | 2             | 3           | 100           | 300           | 6           |
      | 3             | 4           | 300           | 600           | 7           |
      | 4             | 5           | 600           | 700           | 5           |
      | 5             | 6           | 700           | 900           | 6           |
      | 6             | 7           | 900           | 1200          | 7           |
      | 7             | 8           | 1200          | 1300          | 5           |

  Scenario Outline: Playing a round
    Given An active round
    When  The given hidden word is "<word>"
    And   I guess "<attempt>"
    Then  I want to receive "<feedback>" as feedback

    Examples:
      | word   | attempt | feedback                                                    |
      | Muesli | Meazly  | Correct, Misplaced, Incorrect, Incorrect, Correct, Incorrect|
      | Muesli | Muagle  | Correct, Correct, Incorrect, Incorrect, Correct, Misplaced  |
      | Muesli | Mueglu  | Correct, Correct, Correct, Incorrect, Correct, Incorrect    |
      | Muesli | Muesli  | Correct, Correct, Correct, Correct, Correct, Correct        |

  Scenario: Player submits an empty "word" attempt
    Given A "word" was attempted
    When  I try to guess the "word"
    Then  I should receive a warning that the word has no characters in it
    And   I should try again

  Scenario: Player submits a word that is too short
    Given A word was attempted
    When  I try to guess the word
    Then  I should lose "1" of my attempts and receive feedback for the word
    And   I should attempt to guess again

  Scenario: Player submits a word that is too long
    Given A word was attempted
    When  I try to guess the word
    Then  I should lose "1" of my attempts and receive feedback for the word
    And   I should attempt to guess again

  Scenario: Player attempts to guess a word that already has been guessed
    Given A word was guessed
    When  I try to guess the word
    Then  I should receive a notification that the word has already been guessed
    And   I should try again

  Scenario: Player attempts to guess while the game is already over
    Given The was game finished
    When  I try to guess the word
    Then  I should receive a notification that I already lost