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