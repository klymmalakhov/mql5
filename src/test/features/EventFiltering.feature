@active
Feature: Event filtering

  Scenario Outline: As USER I want be able to filter the events
  THEN open details page with corresponding data

    Given I open the Economic calendar page page in "<browser for test>" browser
    When I select Current month in Period filter block
    And I select Medium in Importance filter block
    And I select CHF in Currencies filter block

    And I open event number "1" in the event list
    Then I validate that the following elements have a values
      | ELEMENT'S NAME   | ELEMENT'S VALUE          |
      | Label Importance | <label importance value> |
      | Label Country    | <label country value>    |
    Then I save the events for the last "12" months

    Examples:
      | browser for test | label importance value | label country value |
      | Chrome           | MEDIUM                 | CHF, Swiss frank    |