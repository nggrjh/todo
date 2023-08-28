Feature: Delete a Task

  Scenario: User deletes a task
    Given the user wants to delete a task
    And the user knows the task's ID
    When the user confirms the task deletion
    Then the task is deleted from the system
    And the user receives a success message

  Scenario: User confirms deletion but task doesn't exist
    Given the user wants to delete a task
    And the user knows the task's ID
    And the task with the given ID does not exist
    When the user confirms the task deletion
    Then the system displays an error message

  Scenario: User cancels task deletion
    Given the user wants to delete a task
    And the user knows the task's ID
    When the user cancels the task deletion
    Then the task remains in the system
