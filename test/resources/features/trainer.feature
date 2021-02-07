Feature: Lingo trainer
  As a Player,
  I want to be able to play an unlimited amount of lingo games,
  So that I can train for competing in the television show Lingo.

  Scenario: Start new game
    When I press the start new game button
    Then I should see the first row of the hidden word with the first letter displayed
    And I should see my score which is 0

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

  # Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: Guessing a word
    Given I'm playing a currently active round
    And the <word> is not yet guessed correctly
    When My <guess> is not exactly the same as the <word>
    Then I should receive <feedback>

    # + means the letter is in the word and at the correct position
    # / means the letter is in the word but not at the correct position
    # - means the letter is not in the word
    Examples:
      | word            | guess       | feedback |
      | blond           | brand       | +--++    |
      | pilsje          | plagen      | +/--/-   |
      | biertje         | biertje     | +++++++  |

  # Failure path
    Given I am playing a game
    And the word is guessed correctly
    Then I cannot guess again, because the round has ended

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

