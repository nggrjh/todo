Feature: Create a Task

  Scenario: User creates a new task
    Given the user is on the task creation page
    When the user provides task details such as title and description
    And the user submits the task
    Then the task is created
    And the user receives a success message

  Scenario: User provides incomplete task details
    Given the user is on the task creation page
    When the user provides incomplete task details, such as missing title or description
    And the user submits the task
    Then the system displays an error message
    And the task is not created

  Scenario: User provides invalid input for task creation
    Given the user is on the task creation page
    When the user provides invalid input for task details
    And the user submits the task
    Then the system displays an error message
    And the task is not created
