package se.lexicon.sequencers;

public class TodoItemIdSequencer {
    private int currentId;

    private static TodoItemIdSequencer instance;

    private TodoItemIdSequencer() {
        currentId = 100;
    }

    public static TodoItemIdSequencer getInstance() {
        if (instance == null)
            instance = new TodoItemIdSequencer();
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
