name: Task
description: Creating a task
title:
labels:
- task
assignees:
body:
- type: markdown
  attributes:
  value: "## Task\nPlease fill out the following details to help us effectively plan and execute the task."

- type: textarea
  id: task-description
  attributes:
  label: Task Description
  description: "Provide a detailed description of the task."
  placeholder: "I need ..."
  render: markdown
  validations:
    required: true

- type: dropdown
  id: priority
  attributes:
  label: Priority
  description: "Select the priority of this task."
  options:
    - label: High
    - label: Medium
    - label: Low
  validations:
    required: true

footer: "Thank you for documenting this task. Please double-check all information before submitting."
