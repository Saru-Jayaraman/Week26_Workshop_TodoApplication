package se.lexicon.model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private int assigneeId;
    private boolean assigned;

    //Create a Todoitem object - With assignee
    public TodoItem(String title, String taskDescription, LocalDate deadLine, int assigneeId, boolean done) {
        setTitle(title);
        this.taskDescription = taskDescription;
        setDeadLine(deadLine);
        this.done = done;
        this.assigneeId = assigneeId;
        this.assigned = true;
    }

    //Create a Todoitem object - Without assignee
    public TodoItem(String title, String taskDescription, LocalDate deadLine, boolean done) {
        setTitle(title);
        this.taskDescription = taskDescription;
        setDeadLine(deadLine);
        this.done = done;
        this.assigned = false;
    }

    //Fetch Todoitem object
    public TodoItem(int id, String title, String taskDescription, LocalDate deadLine, boolean done, int assigneeId) {
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title should not be null or empty...");
        }
        this.title = title;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = Objects.requireNonNull(deadLine, "DeadLine cannot be null...");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && Objects.equals(title, todoItem.title) && Objects.equals(taskDescription, todoItem.taskDescription) &&
                Objects.equals(deadLine, todoItem.deadLine) && done == todoItem.done && assigneeId == todoItem.assigneeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, deadLine, done, assigneeId);
    }

    @Override
    public String toString() {
        return "TodoItem {" + "Id: " + getId() + ", Title: '" + getTitle() + '\'' +
                ", TaskDescription: '" + getTaskDescription() + '\'' + ", DeadLine: " + getDeadLine() +
                ", Done: " + isDone() + ", AssigneeId: " + getAssigneeId() + '}';
    }
}