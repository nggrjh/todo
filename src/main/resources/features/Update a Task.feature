Feature: Update a Task

  Scenario: User updates a task's details
    Given the user wants to update a task
    And the user knows the task's ID
    When the user provides new details for the task, such as a new title or description
    And the user submits the changes
    Then the task's details are updated
    And the user receives a success message

  Scenario: User provides invalid task details for update
    Given the user wants to update a task
    And the user knows the task's ID
    When the user provides invalid details for the task update
    And the user submits the changes
    Then the system displays an error message
    And the task's details are not updated

  Scenario: User attempts to update a non-existent task
    Given the user wants to update a task
    And the user provides a task ID that doesn't exist
    When the user submits the changes
    Then the system displays an error message
    And the task's details are not updated
