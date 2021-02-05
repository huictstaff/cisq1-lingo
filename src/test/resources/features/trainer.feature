Feature: Getting words
  As a User,
  I want to get a word in a range from 5 to 7 letters
  In order guess that word.

  Scenario: A word with the right amount of letters is given.
    Given I request a certain amount of letter word,
    When I do a request for a "6" letter word
    Then I should get a "6" letter word.

    Scenario: Start new game
      When I do a request for a new game.
      Then I get the first letter of a 5 letter word to guess.

      Scenario: Starting a new Round
        Given the game has been started
        When I do a request for a new round.
        Then I can see a letter
        Then I can guess the word.