@active
Feature: Event filtering

  Scenario Outline: As USER I want be able to filter the events
  THEN open details page with corresponding data

    Given the "<browser for test>" browser is opened
    When I open the "Economic calendar" page
    And I filter economic events by
      | FILTER NAME | FILTER VALUE       |
      | Period      | <period value>     |
      | Importance  | <importance value> |
      | Currency    | <currency value>   |
    When I open event number "1" in the event list
    Then I validate that the following elements have a values
      | ELEMENT'S NAME   | ELEMENT'S VALUE          |
      | Label Importance | <label importance value> |
      | Label Country    | <label country value>    |
    Then I save the events for the last "12" months
    Then close browser

    Examples:
      | browser for test | period value  | importance value | currency value    | label importance value | label country value |
      | Chrome           | Current month | Medium           | CHF - Swiss frank | MEDIUM                 | CHF, Swiss frank    |