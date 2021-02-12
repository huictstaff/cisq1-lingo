Feature: Start a Game
  As a User,
  I want to start a game
  In order to start using lingo trainer

Scenario: User starts a game
  When User clicks on the start game button
  Then A game should be started at round 1

Feature: User starts a new round
  When User has won last round
  Then A new round should be started with the correct word length

Scenario Outline: User starts a new round
  Given User is playing a game
  And the round was won
  And the last word had "<previous length>" letters
  When User starts a new round
  Then the word to guess has "<next length>" letters

  Examples:
    | previous length | next length |
    | 5               | 6           |
    | 6               | 7           |
    | 7               | 5           |

  #Failure path
  Given User is playing a game
  And the round was lost
  Then User cannot start a new round

Feature: Guessing a word
  As a User,
  I want to guess a word
  In order to know if my guessed word is correct

Scenario Outline: Guessing a word
  Given User is playing a game
  When User makes a "<guess>"
  And User his "<guess>" is compared with the "<word>"
  Then "<feedback>" is given

  Examples:
    | word | guess | feedback |
    | test | test  | [correct,correct,correct,correct] |
    | test | tell  | [correct,correct,absent,absent]   |
    | test | tits  | [correct,absent,present,present]  |
