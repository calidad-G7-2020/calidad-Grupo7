Feature: Have a select bar to change the background color
  As Other games
  I want to a select bar
  Because I want to change the background color

  @backGround-feature
  Scenario: Create a select bar
    Given I'm in the menu of the game and I want to change background color
    When I select the background in select bar
    Then I change the background color