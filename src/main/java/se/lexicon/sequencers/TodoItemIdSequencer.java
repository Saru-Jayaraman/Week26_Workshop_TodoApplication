package se.lexicon.sequencers;

public class TodoItemIdSequencer {
    public static int currentId = 100;

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        TodoItemIdSequencer.currentId = currentId;
    }

    public static int nextId() {
        currentId++;
        setCurrentId(currentId);
        return currentId;
    }
}
