Feature: restart ranking best scores
  As a player
  I want to restart the ranking
  Because I don´t want to see the best scores of the players

  @restartRanking-feature
  Scenario: restart ranking list
    Given I´m in the ranking page
    When I press the restart ranking button
    Then The ranking list is empty