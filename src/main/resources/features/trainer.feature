Feature: Lingo Word Trainer
  As a User,
  I want to guess "5", "6" and "7" letter words
  In order to train for the tv show

  Scenario: Start new game
    When I start a new game
    Then I want to be able to guess a "5" letter word
    And See the letter it begins with