package se.lexicon.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class TodoItem {
    private final int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    public TodoItem(String title, String taskDescription, LocalDate deadLine, Person creator, boolean done) {
        Random random = new Random();
        this.id = random.nextInt(100);
        setTitle(title);
        setTaskDescription(taskDescription);
        setDeadLine(deadLine);
        setCreator(creator);
        setDone(done);
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

    public Person getCreator() {
        return creator;
    }

    public void setTitle(String title) {
        if(title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title should not be null or empty...");
        }
        this.title = title;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = Objects.requireNonNull(deadLine, "DeadLine cannot be null...");
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setCreator(Person creator) {
        if(creator == null)
            throw new IllegalArgumentException("Creator detail is mandatory...");
        this.creator = creator;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadLine);
    }

    public String getSummary() {
        return "TodoItem ID: " + getId() + " Title: " + getTitle() + " Description: " + getTaskDescription() +
                " Deadline: " + getDeadLine() + "\nCreator Details: " + getCreator().getSummary() + "\n";
    }
}