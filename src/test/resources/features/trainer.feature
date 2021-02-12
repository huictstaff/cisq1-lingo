Feature: Training for Lingo
  As a player
  I want to play Lingo games
  so that I can improve my Lingo skills
  
Scenario: Start new game
  When I want to start a new Lingo game
  Then I should see a new Lingo game being started
  
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
  
  Given I am guessing a word
  Then I cannot start a new round
  
  Given I am not playing a game
  Then I cannot start a new round
  
Scenario Outline: Guessing a word
  Given I am playing a game
  And a "<word>" has been chosen
  When I try a "<guess>"
  Then I should see "<feedback>"
  
  Examples:
  | word  | guess  | feedback 											  |
  | BAARD | BERGEN | INVALID, INVALID, INVALID, INVALID, INVALID, INVALID |
  | BAARD | BONJE  | CORRECT, ABSENT, ABSENT, ABSENT, ABSENT 			  |
  | BAARD | BARST  | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT			  |
  | BAARD | DRAAD  | ABSENT, PRESENT, CORRECT, PRESENT, CORRECT 		  |
  | BAARD | BAARD  | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT 		  |
  
  Given I am playing a game
  When I successfully guessed the word
  Then the score should be increased according to the Lingo rules
  
  Given I am playing a game
  When I try to guess a word
  And I already guessed the word
  Then I should not be able to guess anymore
  
  Given I lost
  When I try to guess a word
  Then I should not be able to guess anymore
  
  Given I am playing a game
  When I unsuccessfully guessed the word "5" times
  Then I lost the game