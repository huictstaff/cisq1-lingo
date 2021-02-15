Feature: Starting the game
  As a User,
  I want to start the game,
  in order to begin training my skills

Feature: Starting another game
  As a User,
  I want to be able to start another game,
  in order to play concurrent games

Feature: Getting a word
  As a User,
  I want to get a 5-7 letter long word,
  in order to practice my Lingo skills

Feature: Guessing a word
  As a User,
  I want to guess a word,
  in order to get feedback

  Scenario Outline: Guessing a word
    Given I am playing a round
    And I haven't won or lost yet
    And the "<guessed word length>" is the "<actual word length>"
    And the word exists
    And I haven't guessed that word yet
    When I send the word
    Then I should get feedback

    Examples:
      | actual word length | guessed word length |
      | 5                  | 5                   |
      | 6                  | 6                   |
      | 7                  | 7                   |

#    Failure path for after the round is finished
    Given I am playing a round
    And the round was finished
    Then I cannot guess a word

#    Failure path for when the word has already been guessed
    Given I am playing a round
    And the word has already been guessed
    Then I cannot guess a word

Feature: Getting feedback
  As a User,
  I want to get feedback on my guess,
  in order to improve my next guess

  Scenario Outline: Getting feedback
    Given I am playing a round
    And I haven't won or lost yet
    And the word is "<word>"
    When I submit a "<guess>"
    Then I should get "<feedback>"

    Examples:
      | word | guess | feedback                           |
      | test | tien  | CORRECT, ABSENT, PRESENT, ABSENT   |
      | test | tent  | CORRECT, PRESENT, ABSENT, CORRECT  |
      | test | test  | CORRECT, CORRECT, CORRECT, CORRECT |

Feature: Getting right feedback
  As a User,
  I want the feedback to check for each letter if it is in the right spot, in the word but not that spot or not in the word at all,
  in order to decide my next guess better

Feature: Getting a score
  As a User,
  I want to have a score,
  in order to see how well I am doing

Feature: Starting a new round
  As a User,
  I want to be able to start a new round,
  in order to get a new word
  Scenario Outline: Start a new round
    Given I am playing a game
    And the round was finished
    And the last word had "<previous length>" letters
    And this is not my 5th round
    When I start the new round
    Then the next word to guess has "<next length>" letters

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

#    Failure path for losing a game
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round
#    Failure path for still being in a game
    Given I am playing a game
    And I haven't won or lost yet
    Then I cannot start a new round

Feature: Pausing the game
  As a User,
  I want to be able to pause the game,
  in order to continue it at another point in time