Feature: Lingo trainer
  As a Player,
  I want to be able to play an unlimited amount of lingo games,
  So that I can train for competing in the television show Lingo.

  Scenario: Start new game
    When I press the start new game button
    Then I should see the first row of the hidden word and my score

  Scenario: A hidden word is not guessed correctly
    Given I'm playing a currently active round
    When I did not fill in all the right letters in the right order
    Then I should see which of the letters I filled in are in the hidden word
    And which of the letters are in the correct position

  Scenario: A hidden word is guessed correctly
    Given I'm playing a currently active round
    When I fill in all the right letters in the right order
    Then I should get a confirmation that the filled in word is correct
    And The points I earned this round should be added to my total score

  Scenario: Start new round
    Given The previous round has ended and the game is not finished
    When I guessed the hidden word of the previous round within the amount of tries
    Then I should be able to press the go to next round button