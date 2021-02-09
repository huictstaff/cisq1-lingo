Feature: See the first letter of a random word
  As a player,
  I want to be able to see the first letter of a random word,
  In order to start with guessing.

  Scenario: Start new game
    When click on the button: "Start new game"
    Then a new game should start
    And the first letter of a random five letter word should be displayed

    Scenario: Start new round
      Given a game is active
      And the last round was won
      When I see a new first letter of a random word
      Then I could start with guessing