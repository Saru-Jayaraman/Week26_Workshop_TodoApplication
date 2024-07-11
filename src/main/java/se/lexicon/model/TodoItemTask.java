package se.lexicon.model;

import java.util.Objects;
import se.lexicon.sequencers.*;

public class TodoItemTask {
    private final int id;
    private boolean assigned;
    private Person assignee;
    private TodoItem todoItem;

    public TodoItemTask(Person assignee, TodoItem todoItem) {
        setTodoItem(todoItem);
        TodoItemTaskIdSequencer sequencerObject = TodoItemTaskIdSequencer.getInstance();
        this.id = sequencerObject.nextId();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todoItem, that.todoItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todoItem);
    }

    @Override
    public String toString() {
        String todoItemTaskSummary = "TodoItemTask{" + "id=" + getId() + ", assigned=" + isAssigned() +
                ", todoItem=" + getTodoItem() + '}';
        if(isAssigned()) {
            String isOverdue;
            if(todoItem.isDone()) {
                isOverdue = "Todo Item was FINISHED";
            } else if(todoItem.isOverdue()) {
                isOverdue = "Todo Item is OVERDUE";
            } else {
                isOverdue = "Todo Item is NOT OVERDUE and needs to be completed before deadline";
            }
            todoItemTaskSummary += "\n" + isOverdue;
        } else {
            todoItemTaskSummary += "\nNot assigned to anyone";
        }
        return  todoItemTaskSummary;
    }
}