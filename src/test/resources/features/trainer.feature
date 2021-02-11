Feature: Training for Lingo
  As a player,
  I want to guess 5,6,7 letter words
  In order to become the best at Lingo

  Scenario: Start new game
    When I start a new game
    Then the word to guess has "5" letters
    And I should see the first letter
    And my score is "0"