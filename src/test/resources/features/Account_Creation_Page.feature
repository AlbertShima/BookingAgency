Feature: Creating new account

  @Test2
  Scenario: Creating an account for the new user
    Given I am in a Sign Up page
    When I enter valid user email
    And click on "Continue with email" button
    And I enter valid password
    #I am failing to create NEW account because website is secure
    And click on "Create account" button
    #So I am putting this step intentionally so I can verify others
    And I have account created
    And main page is opened
    And click on "Manage Accounts" button
    And click on "Personal Details" button
    Then "Personal Details" page is opened
    And correct value is prefilled in email verification placeholder

