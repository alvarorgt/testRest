Feature: Authentication

  @CreateToken
  Scenario: Generate authentication token
    Given the user wants to authenticate
    When the user sends valid credentials
    Then the user should receive a valid token

  @CreateBooking
  Scenario Outline: Create a booking and verify the response
    Given the booking details are:
      | firstname     | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | <firstname>   | <lastname>| <totalprice>| <depositpaid>| <checkin>   | <checkout>  | <additionalneeds> |
    When the user creates the bookings
    Then the response status should be 200
    And the response firstname should match the request firstname

    Examples:
      | firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | Pedro     | Gutierrez | 100        | true        | 2024-03-01  | 2024-04-01  | Comics          |
      | Javier    | Jaramillo | 356        | true        | 2024-03-15  | 2024-04-15  | Terror          |