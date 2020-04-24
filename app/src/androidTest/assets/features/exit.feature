Feature: Exit game
  As a User  
  I want to have a button 
  Because I want exit the current game

  @exit-feature
  Scenario: Go to main menu
    Given I'm in the middle of a game and I want to go out
    When I press the exit button
    Then I go back to the main menu

