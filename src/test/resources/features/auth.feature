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

  @CreateBookingAndGet
  Scenario Outline: Create a booking and verify the response
    Given the booking details are:
      | firstname     | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | <firstname>   | <lastname>| <totalprice>| <depositpaid>| <checkin>   | <checkout>  | <additionalneeds> |
    And the user creates the bookings
    And the response status should be 200
    And the response firstname should match the request firstname
    When the user retrieves the bookings
    Then the response status should be 200

    Examples:
      | firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | Pedro     | Gutierrez | 100        | true        | 2024-03-01  | 2024-04-01  | Comics          |


  @UpdateBooking
  Scenario Outline: Update booking details
    Given the booking details are:
      | firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And the user creates the bookings
    And the response status should be 200
    And the response firstname should match the request firstname
    When the user updates the booking with the following details:
      | firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | <updatedFirstname> | <updatedLastname> | <updatedTotalprice> | <updatedDepositpaid> | <updatedCheckin> | <updatedCheckout> | <updatedAdditionalneeds> |
    Then the response status should be 200

    Examples:
      | firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds | updatedFirstname | updatedLastname | updatedTotalprice | updatedDepositpaid | updatedCheckin | updatedCheckout | updatedAdditionalneeds |
      | Pedro     | Gutierrez | 100        | true        | 2024-03-01  | 2024-04-01  | Comics          | Jose             | Gutierrez       | 100               | true               | 2023-05-12     | 2023-06-28      | Comics                |

  @DeleteBooking
  Scenario Outline: Delete booking
    Given the booking details are:
      | firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And the user sends valid credentials
    When the user creates the bookings
    Then the response status should be 200
    And the response firstname should match the request firstname
    When the user deletes the booking
    Then the delete response status should be 201
#    And the delete response message should be "Created"

    Examples:
      |firstname | lastname  | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | Pedro     | Gutierrez | 100        | true        | 2024-03-01  | 2024-04-01  | Comics          |
