Feature: Read Tasks

  Scenario: User views the list of tasks
    Given the user wants to see their tasks
    When the user requests to view the list of tasks
    Then the system displays a list of tasks
    And each task is shown with its title and description

  Scenario: User views a specific task
    Given the user knows the task they want to view
    When the user requests to view a specific task by its ID
    Then the system displays the task's details

  Scenario: No tasks available
    Given there are no tasks in the system
    When the user requests to view the list of tasks
    Then the system displays a message indicating no tasks are available

  Scenario: User provides an invalid task ID
    Given the user wants to view a specific task
    When the user provides an invalid task ID
    Then the system displays an error message
