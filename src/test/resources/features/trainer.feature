# language: en

Feature: Trainer for Lingo
  As a User,
  I want to train my Lingo skills,
  In order to improve my word guessing skills.

  Scenario: Start a new game
    When  I start a new game
    Then  the word to guess has "5" letters
    And   I should see the first letter
    And   my score is "0"

  Scenario Outline: Start a new round
    Given I am playing a game
    And   the round was won
    And   the last word was "previous length" letters long
    When  I start a new round
    Then  I should see a "next length" letter word

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

    Given I am playing a game
    And   the round was lost
    Then  I cannot start a new round

  Scenario Outline: Guessing a word
    Given the word is "word"
    When  I guess "guess"
    Then  the feedback should be "feedback"

    Examples:
    Between * means correct, letter is present in the to be guessed word and at the right index
    Between + means present in the "word" but at the wrong index
    Between - means absent. Letter is not present in "word"
      | Word to be guessed | Guess   | Result
      | panic              | plasti  | *p*-l-+a+-s--t-+i+
      | puzzle             | muzzle  | -m-*u**z**z**l**e*
      | jacuzzi            | jazzman | *j**a*+z++z++a+-n-
