package se.lexicon.sequencers;

public class TodoItemTaskIdSequencer {
    public static int currentId = 200;

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        TodoItemTaskIdSequencer.currentId = currentId;
    }

    public static int nextId() {
        currentId++;
        setCurrentId(currentId);
        return currentId;
    }
}