Feature: Training for Lingo
  As a Player,
  I want to practice guessing 5, 6 and 7 letter words
  In order to prepare for Lingo

  Scenario: Start a new game
    When I request to start a new game
    Then the word to guess has "5" letters
    And I should see the first letter
    And my score is "0"

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

  Scenario: Gain score
    Given I am playing a game
    When my guess is correct
    When i want to gain score



  Scenario Outline: Guess a word
    Given I am playing a game
    And the word to guess is "<word-to-guess>"

    Examples:




  # Failure Path
  Given I am playing a game
  And the round was lost
  Then i cannot start a new round


Feature: User Registration
  As a Player,
  I want to log onto the trainer
  So i can track my games and score

  Scenario: Login
    When i request to login
    And my credentials are correct
    Then i want to see a summary of my games

