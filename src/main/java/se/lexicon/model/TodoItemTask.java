package se.lexicon.model;

import java.util.Objects;

public class TodoItemTask {
    private final int id;
    private boolean assigned;
    private Person assignee;
    private TodoItem todoItem;

    public TodoItemTask(Person assignee, TodoItem todoItem) {
        setTodoItem(todoItem);
        this.id = Integer.parseInt((Integer.valueOf(todoItem.getId()).toString()).concat("1"));
        setAssignee(assignee);
    }

    public int getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public Person getAssignee() {
        return assignee;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        this.assigned = assignee != null;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = Objects.requireNonNull(todoItem, "TodoItem cannot be null... TodoItemTask should contain 1 TodoItem...");
    }

    public String getSummary() {
        String todoItemTaskSummary = "TodoItemTask ID: " + getId() + " Assigned: " + String.valueOf(isAssigned()).toUpperCase() + " " +
                getTodoItem().getSummary();
        if(isAssigned()) {
            String isOverdue;
            if(todoItem.isDone()) {
                isOverdue = "Todo Item was FINISHED by the ";
            } else if(todoItem.isOverdue()) {
                isOverdue = "Todo Item is OVERDUE on the ";
            } else {
                isOverdue = "Todo Item is NOT OVERDUE and needs to be completed before deadline by the ";
            }
            todoItemTaskSummary += isOverdue + "Assigned Person: " + getAssignee().getSummary();
        } else {
            todoItemTaskSummary += "Not assigned to anyone";
        }
        return todoItemTaskSummary;
    }
}