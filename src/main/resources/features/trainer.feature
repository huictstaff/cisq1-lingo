Feature: Start a new round
  Given I am playing a game
  And the round was won
  And the last round had "5" letters
  When I start a new round
  Then the word to guess has "6" letters

  Given I am playing a game
  And the round was won
  And the last round had "6" letters
  When I start a new round
  Then the word to guess has "7" letters

  Given I am playing a game
  And the round was won
  And the last round had "7" letters
  When I start a new round
  Then the word to guess has "5" letters
