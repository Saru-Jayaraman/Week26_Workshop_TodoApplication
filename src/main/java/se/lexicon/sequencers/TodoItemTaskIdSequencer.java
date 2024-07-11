package se.lexicon.sequencers;

public class TodoItemTaskIdSequencer {
    private int currentId;

    private static TodoItemTaskIdSequencer instance;

    private TodoItemTaskIdSequencer() {
        currentId = 200;
    }

    public static TodoItemTaskIdSequencer getInstance() {
        if (instance == null)
            instance = new TodoItemTaskIdSequencer();
        return instance;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public int nextId() {
        currentId++;
        setCurrentId(currentId);
        return currentId;
    }
}