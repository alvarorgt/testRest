Feature: Authentication

  @CreateToken
  Scenario: Generate authentication token
    Given the user wants to authenticate
    When the user sends valid credentials
    Then the user should receive a valid token

